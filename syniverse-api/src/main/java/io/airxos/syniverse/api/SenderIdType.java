/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import com.google.gson.annotations.Expose;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * This is an immutable resource that represents the list of
 * supported sender types that an be associated with a sender id.
 * @author jaase
 */
public class SenderIdType extends BaseData {

    @Expose private String id = null;
    @Expose private String name = null;
    @Expose private String description = null;
    @Expose private List<String> capabilities = null;
    @Expose private List<String> allowed_mime_types = null;
    @Expose private List<String> blocked_mime_types = null;
    @Expose private String gateway_id = null;
    @Expose private Long last_update_date = null;
    @Expose private List<String> credential_parameter_list = null;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @return human readable description of the sender address type
     */
    public String getDescription() {
        return description;
    }

    public List<String> getCredentialParameterList() {
        return credential_parameter_list;
    }

    /**
     *
     * @return Message types allowed on the sender ids from of this type-
     *  SMS, MMS, EMAIL, etc
     */
    public List<String> getCapabilities() {
        return capabilities;
    }

    /**
     *
     * @return Content type allowed on the sender id. This is a comma
     * delimited list of MIME types. If the list is empty, all
     * MIME types except for the blocked ones are allowed.
     */
    public List<String> getAllowedMimeTypes() {
        return allowed_mime_types;
    }

    /**
     *
     * @return Content type explicitly blocked on the sender id type.
     * This is a coma delimited list of content types on the
     * sender id.
     */
    public List<String> getBlockedMimeTypes() {
        return blocked_mime_types;
    }

    /**
     * this is the id of the gateway / connector to the downstream
     * system that will handle the message delivery. The known
     * gateways/connectors and the respective connection parameters
     * for the gateway are configured separately.
     *
     * @return Gateway id
     */
    public String getGatewayId() {
        return gateway_id;
    }

    public Long getLastUpdateDate() {
        return last_update_date;
    }

    /**
     * Resource class for SenderIdType
     */
    static public class Resource extends ResourceBase {

        public interface SenderIdTypeApi {
            @GET("scg-external-api/api/v1/messaging/sender_id_types")
            Call<ListReturnMapper<SenderIdType>> list(@QueryMap Map<String, String> options);
        }

        private SenderIdTypeApi senderIdClassApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            senderIdClassApi_ = session_.GetApi(SenderIdTypeApi.class, Contact.class, this);
        }

        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        /**
         *
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *          - id
         *          - name
         *          - capabilities
         *          - allowed_mime_types
         *          - blocked_mime_types
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<SenderIdType> list(Map<String, String> options) throws ScgException {
            return genericList(
                options,
                null,
                opts -> ExecuteWithRetry(() -> Session.Execute(senderIdClassApi_.list(opts)), 0));
        }

        /**
         *
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *          - id
         *          - name
         *          - capabilities
         *          - allowed_mime_types
         *          - blocked_mime_types
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<SenderIdType> list(
            Map<String, String> options, ListParameters parameters) throws ScgException {
            return genericList(
                options,
                parameters,
                opts -> ExecuteWithRetry(() -> Session.Execute(senderIdClassApi_.list(opts)), 0));
        }
    }
}
