/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

import com.google.gson.annotations.Expose;
import static com.syniverse.scgapi.BaseData.genericList;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * A Sender Address is a specific address (SMS short code, long code,
 * email address, facebook account ID, etc.) that an Application can
 * send messages from or receive messages to. The Sender Address must
 * be provisioned with the SCG system before it can be used. Once a
 * Sender Address has been provisioned it can be added to a Channel
 * and messages can by sent using the Channel. Alternatively
 * messages can be sent from a sender id without a channel. Sender
 * addresses are customer specific - a customer will be able to
 * access only his own sender addresses
 *
 * @author jaase
 */
public class SenderId extends BaseData {

    static public class State {
        @Expose private String state = null;
        @Expose private String register_method = null;
        @Expose private String verification_code = null;
        @Expose private Integer version_number = null;
    };

    // Read only
    @Expose private String id = null;
    @Expose(serialize = false) private Long application_id = null;
    @Expose private Long created_date = null;
    @Expose private Long last_update_date = null;

    // Special
    @Expose private Integer version_number = null;

    // Read / write
    @Expose private String parent_id = null;
    @Expose private String name = null;
    @Expose private String ownership = null;
    @Expose private String class_id = null;
    @Expose private String type_id = null;
    @Expose private String state = null;
    @Expose private String address = null;
    @Expose private String content_type = null;
    @Expose private List<String> message_templates = null;
    @Expose private String country = null;
    @Expose private String operators = null;
    @Expose private String credentials = null;
    @Expose private String two_way_required = null;
    @Expose private String keep_sender_address = null;
    @Expose private String dr_required = null;
    @Expose private String consent_managed_by = null;
    @Expose private List<String> capabilities = null;
    @Expose private Boolean check_whitelist;

    @Expose private String foreign_id = null;
    @Expose private String register_method = null;
    @Expose private String verification_code = null;

    public String getId() {
        return id;
    }

    public Long getApplicationId() {
        return application_id;
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
     * For shared short codes, this is the sender id of the
     * preprovisioned short code that belongs to Syniverse. Whenever
     * a customer wants to use a preprovisioned, shared code, the
     * parent sender address id will indicate the sender id that was
     * used to create customer version of the sender id.
     *
     * @return parent id.
     */
    public String getParentId() {
        return parent_id;
    }

    /**
     * For shared short codes, this is the sender id of the
     * preprovisioned short code that belongs to Syniverse. Whenever
     * a customer wants to use a preprovisioned, shared code, the
     * parent sender address id will indicate the sender id that was
     * used to create customer version of the sender id.
     *
     * @param parent_id parent id.
     */
    public void setParentId(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This field determines the ownership category of a shortcode.
     * Applies only for SMS type of sender id's. Following values
     * are allowed:
     * <p>
     *  - PRIVATE: represents a short code that is private for use
     *           by one company only
     * <p>
     *  - SHARED: represents a short code that is owned by
     *          Syniverse and where multiple customers can use it.
     *          When a customer wants to use a SHARED sender id, a
     *          new PRIVATE sender address will be created with
     *          parent sender id set to the SHARED sender id.
     * <p>
     *  - PREPROVISIONED: represents an address that is owned by
     *          Syniverse and can be selected by a customer. Upon a
     *          customer selecting a preprovisioned sender address,
     *          new sender id document as a copy of the PREPROVISIONED
     *          document is created, with state PRIVATE and parent_id
     *          pointing the original PREPROVISIONED sender address.
     *          The PREPROVISIONED sender address changes ownership
     *          to PURCHASED. Once the PRIVATE sender address,
     *          pointing to the PURCHASED is deleted, the PURCHASED
     *          sender changes its ownership back to PREPROVISIONED.
     * <p>
     *  - PURCHASED: PREPROVISIONED sender address, currently
     *          occupied by a customer.
     * @return ownership
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * This field determines the ownership category of a shortcode.
     * Applies only for SMS type of sender id's. Following values
     * are allowed:
     * <p>
     *  - PRIVATE: represents a short code that is private for use
     *           by one company only
     * <p>
     *  - SHARED: represents a short code that is owned by
     *          Syniverse and where multiple customers can use it.
     *          When a customer wants to use a SHARED sender id, a
     *          new PRIVATE sender address will be created with
     *          parent sender id set to the SHARED sender id.
     * <p>
     *  - PREPROVISIONED: represents an address that is owned by
     *          Syniverse and can be selected by a customer. Upon a
     *          customer selecting a preprovisioned sender address,
     *          new sender id document as a copy of the PREPROVISIONED
     *          document is created, with state PRIVATE and parent_id
     *          pointing the original PREPROVISIONED sender address.
     *          The PREPROVISIONED sender address changes ownership
     *          to PURCHASED. Once the PRIVATE sender address,
     *          pointing to the PURCHASED is deleted, the PURCHASED
     *          sender changes its ownership back to PREPROVISIONED.
     * <p>
     *  - PURCHASED: PREPROVISIONED sender address, currently
     *          occupied by a customer.
     * @param ownership PRIVATE, SHARED, PREPROVISIONED or PURCHASED.
     */
    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    /**
     * reference to the sender address class that determines the
     * priority and throughput of the configured sender id.
     *
     * @return class id.
     */
    public String getClassId() {
        return class_id;
    }

    /**
     * reference to the sender address class that determines the
     * priority and throughput of the configured sender id.
     * @param class_id class id
     */
    public void setClassId(String class_id) {
        this.class_id = class_id;
    }

    /**
     * reference the sender address type that determines the data
     * types allowed on the sender id and identifies the gateway used
     * to deliver the messages
     *
     * @return type id.
     */
    public String getTypeId() {
        return type_id;
    }

    /**
     * reference the sender address type that determines the data
     * types allowed on the sender id and identifies the gateway used
     * to deliver the messages
     *
     * @param type_id type id.
     */
    public void setTypeId(String type_id) {
        this.type_id = type_id;
    }

    /**
     * represents the sender address status. Following values are
     * <p>
     *  - PENDING_IMPLEMENTATION
     * <p>
     *  - IMPLEMENTED
     * <p>
     *  - ACTIVE
     * <p>
     *  - INACTIVE
     * <p>
     *  - BROKEN
     * <p>
     *  - PENDING_DELETE allowed:
     *<p>
     * For each sender address that require provisioning, when a
     * sender address is requested, the initial status will be
     * PENDING_IMPLEMENTATION.
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * represents the sender address status. Following values are
     * <p>
     *  - PENDING_IMPLEMENTATION
     * <p>
     *  - IMPLEMENTED
     * <p>
     *  - ACTIVE
     * <p>
     *  - INACTIVE
     * <p>
     *  - BROKEN
     * <p>
     *  - PENDING_DELETE allowed:
     *<p>
     * For each sender address that require provisioning, when a
     * sender address is requested, the initial status will be
     * PENDING_IMPLEMENTATION.
     *
     * @param state state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * The sender address. The address must correspond to the sender
     * address type. For a2p sms this will be the shortcode or
     * longcode, for emails this will be the email address etc
     *
     * @return sender address
     */
    public String getAddress() {
        return address;
    }

    /**
     * The sender address. The address must correspond to the sender
     * address type. For a2p sms this will be the shortcode or
     * longcode, for emails this will be the email address etc
     *
     * @param address sender address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * The content of this field is specific the sender type /
     * gateway associated with this sender.
     *
     * @return content type.
     */
    public String getContentType() {
        return content_type;
    }

    /**
     * The content of this field is specific the sender type /
     * gateway associated with this sender.
     *
     * @param content_type content type
     */
    public void setContentType(String content_type) {
        this.content_type = content_type;
    }

    /**
     *
     * @return A list of template ids that apply to this sender address
     */
    public List<String> getMessageTemplates() {
        return message_templates;
    }

    /**
     *
     * @param message_templates A list of template ids that apply to this sender address
     */
    public void setMessageTemplates(List<String> message_templates) {
        this.message_templates = message_templates;
    }

    /**
     * The country that the sender address can be used to send
     * messages to. This field will contain ISO country code. The
     * value is managed by Syniverse and not editable by the customer.
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * The country that the sender address can be used to send
     * messages to. This field will contain ISO country code. The
     * value is managed by Syniverse and not editable by the customer.
     *
     * @param country country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * A list of carriers who are confirmed to have provisioned the
     * sender address. This will be a coma separate list of SPIDs.
     * The values are managed by Syniverse and not editable by the
     * customer
     *
     * @return operators
     */
    public String getOperators() {
        return operators;
    }

    /**
     * A list of carriers who are confirmed to have provisioned the
     * sender address. This will be a coma separate list of SPIDs.
     * The values are managed by Syniverse and not editable by the
     * customer
     *
     * @param operators operators
     */
    public void setOperators(String operators) {
        this.operators = operators;
    }

    /**
     *
     * @return A String containing the user’s credentials which is formatted
     * based on the Sender Address type.
     */
    public String getCredentials() {
        return credentials;
    }

    /**
     *
     * @param credentials A String containing the user’s credentials which is formatted
     * based on the Sender Address type.
     */
    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    /**
     * represents customer requested features of the sender id - if
     * set to YES, means customer expects the sender id to support
     * replies to an address.
     *
     * @return YES if true
     */
    public String getTwoWayRequired() {
        return two_way_required;
    }

    /**
     * represents customer requested features of the sender id - if
     * set to YES, means customer expects the sender id to support
     * replies to an address.
     *
     * @param two_way_required YES or NO
     */
    public void setTwoWayRequired(String two_way_required) {
        this.two_way_required = two_way_required;
    }

    /**
     * represents customer requested features of the sender id - if
     * set to YES, means customer expects the sender id to be
     * preserved
     *
     * @return YES if true
     */
    public String getKeepSenderAddress() {
        return keep_sender_address;
    }

    /**
     * represents customer requested features of the sender id - if
     * set to YES, means customer expects the sender id to be
     * preserved
     * @param keep_sender_address YES or NO
     */
    public void setKeepSenderAddress(String keep_sender_address) {
        this.keep_sender_address = keep_sender_address;
    }

    /**
     * represents customer requested features of the sender id - if set to YES,
     * means customer expects the sender id to support DR
     *
     * @return YES if true
     */
    public String getDrRequired() {
        return dr_required;
    }

    /**
     * represents customer requested features of the sender id - if set to YES,
     * means customer expects the sender id to support DR
     *
     * @param dr_required YES or NO
     */
    public void setDrRequired(String dr_required) {
        this.dr_required = dr_required;
    }

    /**
     *
     * @return Possible values- USER, SCG. Default- USER.
     */
    public String getConsentManagedBy() {
        return consent_managed_by;
    }

    /**
     *
     * @param consent_managed_by Possible values- USER, SCG. Default- USER.
     */
    public void setConsentManagedBy(String consent_managed_by) {
        this.consent_managed_by = consent_managed_by;
    }

    /**
     * Message types allowed on the sender ids from of this type-
     *  SMS, MMS, EMAIL, etc.
     *
     * @return capabilities
     */
    public List<String> getCapabilities() {
        return capabilities;
    }

    /**
     * Message types allowed on the sender ids from of this type-
     *  SMS, MMS, EMAIL, etc.
     *
     * @param capabilities capabilities
     */
    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    /**
     * If set, sending via this sender ID is limited to recipients
     * present in the customer's white list.
     *
     * @return true or false
     */
    public Boolean getCheckWhitelist() {
        return check_whitelist;
    }

    /**
     * If set, sending via this sender ID is limited to recipients
     * present in the customer's white list.
     *
     * @param checkWhitelist true or false
     */
    public void setCheckWhitelist(Boolean checkWhitelist) {
        this.check_whitelist = checkWhitelist;
    }

    public String getForeignId() {
        return foreign_id;
    }

    public void setForeignId(String foreign_id) {
        this.foreign_id = foreign_id;
    }

    public String getRegisterMethod() {
        return register_method;
    }

    public void setRegisterMethod(String register_method) {
        this.register_method = register_method;
    }

    public String getVerificationCode() {
        return verification_code;
    }

    public void setVerificationCode(String verification_code) {
        this.verification_code = verification_code;
    }

    /** Activate the sender id. */
    public void activate() throws ScgException {
        State state = new State();
        state.version_number = version_number;
        state.state = "ACTIVE";
        ((Resource)getResource()).setState(id, state);
    }

    /** Deactivate the sender id */
    public void deactivate() throws ScgException {
        State state = new State();
        state.version_number = version_number;
        state.state = "INACTIVE";
        ((Resource)getResource()).setState(id, state);
    }

    /** Initialize Whatsapp registration on a WHATSAPP sender-id */
    void initWhatsappRegistration(final String registerMethod) throws ScgException {
        State state = new State();
        state.version_number = version_number;
        state.state = "PENDING_CONFIRMATION";
        state.register_method = registerMethod;
        ((Resource)getResource()).setState(id, state);
    }

    /** Activate a Whatsapp sendder id with the verification code provided by Whatsapp */
    void activateWhatsappRegistration(final String verificationCode) throws ScgException {
        State state = new State();
        state.version_number = version_number;
        state.state = "ACTIVE";
        state.verification_code = verificationCode;
        ((Resource)getResource()).setState(id, state);
    }

    /**
     * Resource class for SenderId
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface SenderIdApi {
            @GET("scg-external-api/api/v1/messaging/sender_ids")
            Call<ListReturnMapper<SenderId>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/messaging/sender_ids")
            Call<CreateResponse> create(@Body SenderId id);

            @POST("scg-external-api/api/v1/messaging/sender_ids/{id}")
            Call<Object> update(@Path("id") String id, @Body SenderId payload);

            @GET("scg-external-api/api/v1/messaging/sender_ids/{id}")
            Call<SenderId> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/messaging/sender_ids/{id}")
            Call<Object> delete(@Path("id") String id);

            @POST("scg-external-api/api/v1/messaging/sender_ids/purchase")
            Call<CreateResponse> purchase(@Query("parent_id") String parentId);

            @POST("scg-external-api/api/v1/messaging/sender_ids/{id}")
            Call<Object> setState(@Path("id") String id, @Body State payload);
        }

        private SenderIdApi senderIdApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            senderIdApi_ = session_.GetApi(SenderIdApi.class, SenderId.class, this);
        }

        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        /**
         * Create an instance of a SenderId on the server.
         *
         * @param SenderId SenderId object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final SenderId SenderId) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(senderIdApi_.create(SenderId)).id;
            }, 0);
        }

        public boolean update(final SenderId SenderId) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(senderIdApi_.update(SenderId.getId(), SenderId));
            }, 0);
            return true;
        }

        /**
         * Get a SenderId object from the server.
         *
         * @param id id of the SenderId you want.
         * @return Instance
         * @throws ScgException on error
         */
        public SenderId get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(senderIdApi_.get(id));
            }, 0);
        }

        /**
         * List SenderIds
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *          - id
         *          - parent_id
         *          - name
         *          - ownership
         *          - class_id
         *          - type_id
         *          - state
         *          - country
         *          - operators
         *          - two_way_required
         *          - keep_sender_address
         *          - dr_required
         *          - consent_managed_by
         *          - capabilities
         *          - check_whitelist
         *          - created_date
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<SenderId> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(senderIdApi_.list(opts));
                }, 0);
            });
        }

        /**
         * List SenderIds
         * @param options ilter Key/Value pairs of attributes that can
         *      filter the result-set.
         *          - id
         *          - parent_id
         *          - name
         *          - ownership
         *          - class_id
         *          - type_id
         *          - state
         *          - country
         *          - operators
         *          - two_way_required
         *          - keep_sender_address
         *          - dr_required
         *          - consent_managed_by
         *          - capabilities
         *          - check_whitelist
         *          - created_date
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<SenderId> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(senderIdApi_.list(opts));
                }, 0);
            });
        }

        /**
         * Delete a SenderId
         * @param SenderId SenderId to delete
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final SenderId SenderId) throws ScgException {
            return delete(SenderId.getId());
        }

        /**
         * Delete a SenderId
         * @param id id of SenderId to delete
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(senderIdApi_.delete(id));
            }, 0);
            return true;
        }

        /**
         * Creates a new Sender ID Resource, copy of specified
         * PREPROVISIONED Sender ID, which becomes PURCHASED until
         * the private copy is deleted
         *
         * @param parentId The Sender Id resource associated with the
         *              provided ID
         * @return id of the purchased SenderId
         * @throws ScgException on error
         */
        public String purchase(final String parentId) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(senderIdApi_.purchase(parentId)).id;
            }, 0);
        }

        public void setState(final String id, final State state) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(senderIdApi_.setState(id, state));
            }, 0);
        }
    }
}
