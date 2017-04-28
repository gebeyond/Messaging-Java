/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

import com.google.gson.annotations.Expose;
import static com.syniverse.scgapi.BaseData.genericList;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * The contact address status resource is used to keep the current
 * consent status of the contact address. The contact address status
 * is customer specific i.e. the same address may be tracked in
 * SCG separately for each customer.
 *
 * @author jaase
 */
public class ContactAddressStatus extends BaseData {
    // Read Only
    @Expose(serialize = false) String id;
    @Expose(serialize = false) String consent_status;
    @Expose(serialize = false) Long application_id = null;
    @Expose(serialize = false) Long created_date;
    @Expose(serialize = false) Long last_update_date;

    // Special
    @Expose(serialize = false) Integer version_number = null;

    // Read/Write
    @Expose private String address_type;
    @Expose private String address;
    @Expose private String sender_id;

    /**
     * Unique identifier, allocated by SCG at the time
     * of address creation. This is a combination of an address type/value
     * and sender id.
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
     * this is the current optin status for the specific address /
     * sender id. The possible values are:
     * <p>
     *   - NONE (no optin or optout message received from the address
     *          on the specific sender id)
     * <p>
     *   - OPTIN (user has explicitly opted in to the sender id)
     * <p>
     *   - OPTOUT (user has explicitly opted out of the sender id)
     * <p>
     *   - BLACKLIST (blacklisted address - will be treated as
     *          explicit OPTOUT)
     * <p>
     *   - WHITELIST (whitelisted address - will be treated as
     *          explicit OPTIN)
     * <p>
     * NOTE Whitelisted or blacklisted status can not be overwritten
     *          by the end-user by sending messages to the sender id.
     *          Such users can be only un-listed by changing the
     *          optin-status explicitly put POST to address status ID
     *
     * @return status
     */
    public String getConsentStatus() {
        return consent_status;
    }

    public Long getCreatedDate() {
        return created_date;
    }

    public Long getLastUpdateDate() {
        return last_update_date;
    }

    public Integer getVersionNumber() {
        return version_number;
    }

    public void setVersionNumber(Integer version_number) {
        this.version_number = version_number;
    }

    /**
     *
     * @return The address type- MDN (for phone numbers), EMAIL, SOCIAL, PUSH (…)
     */
    public String getAddressType() {
        return address_type;
    }

    /**
     *
     * @param address_type The address type- MDN (for phone numbers), EMAIL,
     *      SOCIAL, PUSH (…)
     */
    public void setAddressType(String address_type) {
        this.address_type = address_type;
    }

    /**
     *
     * @return The actual value of the address. For SMS/MMS this will be the MDN.
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address The actual value of the address. For SMS/MMS this will
     *      be the MDN.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return the sender id accociated with the address type/value
     */
    public String getSenderId() {
        return sender_id;
    }

    /**
     *
     * @param sender_id the sender id accociated with the address type/value
     */
    public void setSenderId(String sender_id) {
        this.sender_id = sender_id;
    }

    /**
     * Delete a ContactAddressStatus on the server
     *
     * The ContactAddressStatus instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(this);
    }

    /**
     * Resource class for ContactAddressStatus
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface ContactAddressStatusApi {
            @GET("scg-external-api/api/v1/consent/contact_address_statuses")
            Call<ListReturnMapper<ContactAddressStatus>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/consent/contact_address_statuses")
            Call<CreateResponse> create(@Body ContactAddressStatus contactAddressStatus);


            @GET("scg-external-api/api/v1/consent/contact_address_statuses/{id}")
            Call<ContactAddressStatus> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/consent/contact_address_statuses/{id}")
            Call<Object> delete(@Path("id") String id);
        }

        private ContactAddressStatusApi contactAddressStatusApi_;

         @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            contactAddressStatusApi_ = session_.GetApi(ContactAddressStatusApi.class, ContactAddressStatus.class, this);
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
         * Create an instance of a ContactAddressStatus on the server.
         * @param contactAddressStatus object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final ContactAddressStatus contactAddressStatus) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(contactAddressStatusApi_.create(contactAddressStatus)).id;
            }, 0);
        }

        /**
         * Get a ContactAddressStatus object from the server.
         * @param id id of the ContactAddressStatus you want.
         * @return Instance
         * @throws ScgException on error
         */
        public ContactAddressStatus get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(contactAddressStatusApi_.get(id));
            }, 0);
        }

        /**
         * List ContactAddressStatus
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - address_type
         *        - address
         *        - sender_id
         *        - consent_status
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<ContactAddressStatus> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(contactAddressStatusApi_.list(opts));
                }, 0);
            });
        }

        /**
         * List ContactAddressStatus
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - address_type
         *        - address
         *        - sender_id
         *        - consent_status
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<ContactAddressStatus> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(contactAddressStatusApi_.list(opts));
                }, 0);
            });
        }


        public boolean delete(final ContactAddressStatus cas) throws ScgException {
            return delete(cas.getId());
        }

        /**
         * Delete a ContactAddressStatus
         * @param id id of the ContactAddressStatuss to delete on the server.
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(contactAddressStatusApi_.delete(id));
            }, 0);
            return true;
        }
    }
}
