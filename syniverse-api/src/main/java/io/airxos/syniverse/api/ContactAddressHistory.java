/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import com.google.gson.annotations.Expose;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * The contact address status history resource is used to track the
 * received keywords from a given contact address and the changes
 * in the consent status of the contact address. The contact address
 * status is customer specific i.e. the same address may be tracked
 * in SCG separately for each customer.
 *
 * @author jaase
 */
public class ContactAddressHistory extends BaseData {

    @Expose(serialize = false) String id = null;
    @Expose(serialize = false) String msisdn = null;
    @Expose(serialize = false) String sender_id = null;
    @Expose(serialize = false) String source = null;
    @Expose(serialize = false) String status = null;
    @Expose(serialize = false) String timestamp = null;
    @Expose(serialize = false) String message = null;
    @Expose(serialize = false) String keyword = null;
    @Expose(serialize = false) Long application_id = null;
    @Expose(serialize = false) Long created_date = null;
    @Expose(serialize = false) Long last_update_date = null;
    @Expose(serialize = false) Integer version_number = null;

    /**
     * Unique identifier, allocated by SCG at the time of the history
     * record creation.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    public Long getApplicationId() {
        return application_id;
    }

    /**
     *
     * @return The value of the contact's address. For SMS/MMS this will be the MDN
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     *
     * @return The sender id that MO/MT message was sent to/from
     */
    public String getSenderId() {
        return sender_id;
    }

    /**
     * The source of the contact's consent status information:
     * <p>
     *   - MESSAGE - when the consent status has changed as a result of
     *          a MO message sent by the contact (SCG managed consent)
     * <p>
     *   - CARRIER  - when a blacklist file was provided by a
     *          carrier and loaded in
     * <p>
     *   - COMPANY - when the user of SCG has supplied the
     *          information (USER managed consent)
     *
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @return The new consent status of the contact
     */
    public String getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @return The body of the actual MO message that triggered the consent
     *      status change.
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return The keyword detected by the application
     */
    public String getKeyword() {
        return keyword;
    }

    public Long getCreatedDate() {
        return created_date;
    }

    public Long getLastUpdateDate() {
        return last_update_date;
    }


    /**
     * Resource class for ContactAddressHistory
     */
    static public class Resource extends ResourceBase {

        public interface ContactAddressHistoryApi {
            @GET("scg-external-api/api/v1/consent/contact_address_history")
            Call<ListReturnMapper<ContactAddressHistory>> list(@QueryMap Map<String, String> options);
        }

        private ContactAddressHistoryApi contactAddressHistoryApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            contactAddressHistoryApi_ = session_.GetApi(ContactAddressHistoryApi.class, ContactAddressHistory.class, this);
        }

        /**
         *
         * @param session Session instance to use
         */
        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        /**
         * List ContactAddressHistorys
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<ContactAddressHistory> list() throws ScgException {
            return genericList(
                null,
                null,
                opts -> ExecuteWithRetry(() -> Session.Execute(contactAddressHistoryApi_.list(opts)), 0));
        }

        /**
         * List ContactAddressHistorys
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<ContactAddressHistory> list(
                ListParameters parameters) throws ScgException {
            return genericList(
                null,
                parameters,
                opts -> ExecuteWithRetry(() -> Session.Execute(contactAddressHistoryApi_.list(opts)), 0));
        }
    }
}
