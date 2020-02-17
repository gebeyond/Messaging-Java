/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import com.google.gson.annotations.Expose;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Message Requests represent instances of messages which have been
 * sent by Applications to Contacts.
 *
 * @author jaase
 */
public class MessageRequest extends BaseData {

    // Read only
    @Expose(serialize = false) private String id;
    @Expose(serialize = false) private Long recipient_count;
    @Expose(serialize = false) private Long sent_count;
    @Expose(serialize = false) private Long delivered_count;
    @Expose(serialize = false) private Long read_count;
    @Expose(serialize = false) private Long converted_count;
    @Expose(serialize = false) private Long canceled_count;
    @Expose(serialize = false) private Long failed_count;
    @Expose(serialize = false) private Long created_date;
    @Expose(serialize = false) private Long last_update_date;
    @Expose(serialize = false) private String state;

    // Read/Write
    @Expose private String from;
    @Expose private String conversation_id;
    @Expose private List<String> to;
    @Expose private String campaign_id;
    @Expose private String program_id;
    @Expose private String subject;
    @Expose private String application_id;
    @Expose private String external_id;
    @Expose private List<String> attachments;
    @Expose private String body;
    @Expose private String consent_requirement;
    @Expose private String criteria;
    @Expose private String scheduled_delivery_time;
    @Expose private String scheduled_delivery_time_zone;
    @Expose private String expiry_time;
    @Expose private Boolean test_message_flag;
    @Expose private String pause_before_transmit;
    @Expose private String pause_expiry_time;

    @Expose private List<String> contact_delivery_address_priority;
    @Expose private String failover;
    @Expose private BigDecimal price_threshold;
    @Expose private List<String> sender_id_sort_criteria;
    @Expose private String src_language;
    @Expose private String dst_language;
    @Expose private Boolean translate;
    @Expose private Integer translations_count;
    @Expose private Integer translations_failed_count;
    @Expose private Integer translations_performed_count;

    public String getId() {
        return id;
    }

    /**
     * The number of contacts satisfying the criteria (or in the
     * contact group) who have an address on the channel the bulk
     * message is targeted for.
     *
     * @return recipient count
     */
    public Long getRecipientCount() {
        return recipient_count;
    }

    public void setContactDeliveryAddressPriority(List<String> contact_delivery_address_priority) {
        this.contact_delivery_address_priority = contact_delivery_address_priority;
    }

    public void setFailover(String failover) {
        this.failover = failover;
    }

    public void setPriceThreshold(BigDecimal price_threshold) {
        this.price_threshold = price_threshold;
    }

    public void setSenderIdSortCriteria(List<String> sender_id_sort_criteria) {
        this.sender_id_sort_criteria = sender_id_sort_criteria;
    }

    public void setSrcLanguage(String src_language) {
        this.src_language = src_language;
    }

    public void setDstLanguage(String dst_language) {
        this.dst_language = dst_language;
    }

    public void setTranslate(Boolean translate) {
        this.translate = translate;
    }

    public void setTranslationsCount(Integer translations_count) {
        this.translations_count = translations_count;
    }

    public void setTranslationsFailedCount(Integer translations_failed_count) {
        this.translations_failed_count = translations_failed_count;
    }

    public void setTranslationsPerformedCount(Integer translations_performed_count) {
        this.translations_performed_count = translations_performed_count;
    }

    public List<String> getContactDeliveryAddressPriority() {
        return contact_delivery_address_priority;
    }

    public String getFailover() {
        return failover;
    }

    public BigDecimal getPriceThreshold() {
        return price_threshold;
    }

    public List<String> getSenderIdSortCriteria() {
        return sender_id_sort_criteria;
    }

    public String getSrcLanguage() {
        return src_language;
    }

    public String getDstLanguage() {
        return dst_language;
    }

    public Boolean getTranslate() {
        return translate;
    }

    public Integer getTranslationsCount() {
        return translations_count;
    }

    public Integer getTranslationsFailedCount() {
        return translations_failed_count;
    }

    public Integer getTranslationsPerformedCount() {
        return translations_performed_count;
    }



    /**
     * The number of messages successfully sent via API to the
     * underlying channel. The counts are increased continuously as
     * additional messages satisfy criteria.
     *
     * @return sent count
     */
    public Long getSentCount() {
        return sent_count;
    }

    /**
     * The number of messages sent for which some form of delivery
     * receipt confirmation has been received. The number of messages
     * successfully sent via API to the underlying channel. The counts
     * are increased continuously as additional messages satisfy criteria.
     *
     * @return delivered count
     */
    public Long getDeliveredCount() {
        return delivered_count;
    }

    /**
     * The number of messages sent for which some form of delivery
     * receipt confirmation has been received. The number of messages
     * successfully sent via API to the underlying channel. The
     * counts are increased continuously as additional messages
     * satisfy criteria.
     *
     * @return read count
     */
    public Long getReadCount() {
        return read_count;
    }

    /**
     * The number of messages where conversion has been confirmed.
     * This will apply for messages with URL where the application
     * requested a link track. The number of messages successfully
     * sent via API to the underlying channel. The counts are
     * increased continuously as additional messages satisfy criteria.
     *
     * @return converted count
     */
    public Long getConvertedCount() {
        return converted_count;
    }

    /**
     * The number of messages that have been created but not sent
     * because the message request was canceled.
     *
     * @return canceled count
     */
    public Long getCanceledCount() {
        return canceled_count;
    }

    /**
     *
     * @return The number of messages that have expired or have failed delivery
     */
    public Long getFailedCount() {
        return failed_count;
    }

    /**
     * For a MT message (one which was sent via this API), this
     * indicates the time when the API was called to request message
     * delivery. For a MO message, this is the point in time when the
     * SCG was notified of the inbound message from the operator
     * network/mediate channel.
     *
     * @return created date
     */
    public Long getCreatedDate() {
        return created_date;
    }

    public Long getLastUpdateDate() {
        return last_update_date;
    }

    /**
     * The channel or Sender Id over/from which the message is to be sent.
     *
     * - To send from a sender ID, the ID should be prefixed with 'sender_id'.
     * - To send from a channel the ID should be prefixed with 'channel-'.
     *
     * @return from
     */
    public String getFrom() {
        return from;
    }

    /**
     *
     * - To send from a sender ID, the ID should be prefixed with 'sender_id'.
     * - To send from a channel the ID should be prefixed with 'channel-'.
     *
     * @param from The channel or Sender Id over/from which the message is to be sent.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     *
     * @return The identifier of the conversation for conversation threading.
     */
    public String getConversationId() {
        return conversation_id;
    }

    /**
     *
     * @param conversation_id The identifier of the conversation for conversation threading.
     */
    public void setConversationId(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    /**
     * For MT messages this will be a list of one or more recipient
     * addresses, contact ids, contact group ids.
     *
     * @return to
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * For MT messages this will be a list of one or more recipient
     * addresses, contact ids, contact group ids.
     * @param to recipient
     */
    public void setTo(String to) {
        ArrayList<String> toList = new ArrayList<>();
        toList.add(to);
        setTo(toList);
    }

    /**
     * For MT messages this will be a list of one or more recipient
     * addresses, contact ids, contact group ids.
     * @param to recipients
     */
    public void setTo(List<String> to) {
        this.to = to;
    }

    /**
     *
     * @return The customer provided campaign id.
     */
    public String getCampaignId() {
        return campaign_id;
    }

    /**
     *
     * @param campaign_id The customer provided campaign id.
     */
    public void setCampaignId(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getProgramId() {
        return program_id;
    }

    public void setProgramId(String program_id) {
        this.program_id = program_id;
    }

    /**
     *
     * @return  a short summary of the message’s purpose.
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject  a short summary of the message’s purpose.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @return ID of the application that has created the message request
     */
    public String getApplicationId() {
        return application_id;
    }

    /**
     *
     * @param application_id ID of the application that has created the message request
     */
    public void setApplicationId(String application_id) {
        this.application_id = application_id;
    }

    /**
     * Application provided unique tracking ID for the message. Can
     * be used as an alternative key in query methods.
     *
     * @return tracking ID
     */
    public String getExternalId() {
        return external_id;
    }

    /**
     * Application provided unique tracking ID for the message. Can
     * be used as an alternative key in query methods.
     *
     * @param external_id tracking ID
     */
    public void setExternalId(String external_id) {
        this.external_id = external_id;
    }

    /**
     * The status of the message request
     *<p>
     * - SUBMITTED
     * <p>
     * - ACCEPTED
     * <p>
     * - REJECTED
     * <p>
     * - PREPARING (while in the preparing state, the recipient cont
     *          may increase with time i.e. SCG will keep track of
     *          the preparing step to allow polling on the resource
     *          to have user see progress)
     * <p>
     * - TRANSMITTING (while in the transmission state, the sent count
     *          will be updates as the messages are being sent)
     * <p>
     * - COMPLETED
     * <p>
     * - PAUSED (SCG will go into this state after preparing if pause flag is set)
     * <p>
     * - CANCELED Canceled (cancel can be requested at any time)
     * <p>
     * <p>
     * To resume paused messages, the status should be updated
     * by customer from PAUSED to TRANSMITTING. You can use resume()
     * for this.
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * The status of the message request
     *<p>
     * - SUBMITTED
     * <p>
     * - ACCEPTED
     * <p>
     * - REJECTED
     * <p>
     * - PREPARING (while in the preparing state, the recipient cont
     *          may increase with time i.e. SCG will keep track of
     *          the preparing step to allow polling on the resource
     *          to have user see progress)
     * <p>
     * - TRANSMITTING (while in the transmission state, the sent count
     *          will be updates as the messages are being sent)
     * <p>
     * - COMPLETED
     * <p>
     * - PAUSED (SCG will go into this state after preparing if pause flag is set)
     * <p>
     * - CANCELED Canceled (cancel can be requested at any time)
     * <p>
     * <p>
     * To resume paused messages, the status should be updated
     * by customer from PAUSED to TRANSMITTING. You can use resume()
     * for this.
     *
     * @param state state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return  list of attachment IDs which are associated with this message
     */
    public List<String> getAttachments() {
        return attachments;
    }

    /**
     *
     * @param attachments  list of attachment IDs which are associated with this message
     */
    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    /**
     *
     * @param attachmentId  Id of an attachment to add to the message.
     */
    public void addAttachment(final String attachmentId) {
        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        attachments.add(attachmentId);
    }

    /**
     * This is the original message body as supplied by application,
     * prior to keyword processing.
     *
     * It can reference a message template and supply the variables
     * assignments for use with a template or contain the actual
     * message body.
     *
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * This is the original message body as supplied by application,
     * prior to keyword processing.
     *
     * It can reference a message template and supply the variables
     * assignments for use with a template or contain the actual
     * message body.
     *
     * @param body body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Field indicating if the message delivery is dependent on the
     * double optin for the recipient. Any contacts/destination MDNs
     * that should receive this message but do not have double optin
     * in associated with the sender id would not be delivered
     * (message will be created but will be in 'failed' status and
     * will not be sent to the recipient). Values are:
     * <p>
     * - OPT_IN (message will be sent only if user explicitly opted in)
     * <p>
     * - OPT_OUT (message will be sent unless user explicitly opted out)
     * <p>
     * - NONE (message will be sent irrespectively of optin status)
     *
     * @return OPT_IN, OPT_OUT or NONE
     */
    public String getConsentRequirement() {
        return consent_requirement;
    }

    /**
     * Field indicating if the message delivery is dependent on the
     * double optin for the recipient. Any contacts/destination MDNs
     * that should receive this message but do not have double optin
     * in associated with the sender id would not be delivered
     * (message will be created but will be in 'failed' status and
     * will not be sent to the recipient). Values are:
     * <p>
     * - OPT_IN (message will be sent only if user explicitly opted in)
     * <p>
     * - OPT_OUT (message will be sent unless user explicitly opted out)
     * <p>
     * - NONE (message will be sent irrespectively of optin status)
     *
     * @param consentRequirement OPT_IN, OPT_OUT or NONE
     */
    public void setConsentRequirement(String consentRequirement) {
        this.consent_requirement = consentRequirement;
    }

    /**
     *
     * @return The criteria string which was used in the bulk transmission, if any.
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     *
     * @param criteria The criteria string which was used in the bulk transmission, if any.
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     *
     * @return The date and time that this message should be delivered.
     */
    public String getScheduledDeliveryTime() {
        return scheduled_delivery_time;
    }

    /**
     *
     * @param scheduledDeliveryTime The date and time that this message should be delivered.
     */
    public void setScheduledDeliveryTime(String scheduledDeliveryTime) {
        this.scheduled_delivery_time = scheduledDeliveryTime;
    }

    /**
     * Determines what time zone to apply to the scheduled delivery time:
     * <p>
     * - UTC- means that the scheduled delivery time as supplied in the
     *          request is expressed in UTC time
     * <p>
     * - LOCAL- means that the scheduled delivery time as supplied in
     *          the request is expressed in LOCAL recipient time.
     *          The time zone of the recipient is determined by SCG
     *          automatically based on:
     * <p>
     *            (1) actual location of the user (if LBS active and location known)
     * <p>
     *            (2) zip code associated with the user
     * <p>
     *            (3) MDN of the user (based on country)
     *
     * @return scheduled delivery time zone
     */
    public String getScheduledDeliveryTimeZone() {
        return scheduled_delivery_time_zone;
    }

    /**
     * Determines what time zone to apply to the scheduled delivery time:
     * <p>
     * - UTC- means that the scheduled delivery time as supplied in the
     *          request is expressed in UTC time
     * <p>
     * - LOCAL- means that the scheduled delivery time as supplied in
     *          the request is expressed in LOCAL recipient time.
     *          The time zone of the recipient is determined by SCG
     *          automatically based on:
     * <p>
     *            (1) actual location of the user (if LBS active and location known)
     * <p>
     *            (2) zip code associated with the user
     * <p>
     *            (3) MDN of the user (based on country)
     *
     * @param scheduledDeliveryTimeZone scheduled delivery time zone
     */
    public void setScheduled_deliveryTimeZone(String scheduledDeliveryTimeZone) {
        this.scheduled_delivery_time_zone = scheduledDeliveryTimeZone;
    }

    /**
     *
     * @return The timestamp after which the messages resulting from
     *  the message request should no longer be sent.
     */
    public String getExpiryTime() {
        return expiry_time;
    }

    /**
     * @param expiry_time The timestamp after which the messages resulting from
     *  the message request should no longer be sent.
     */
    public void setExpiryTime(String expiry_time) {
        this.expiry_time = expiry_time;
    }

    /**
     * If this flag is set to true then this will be processed and
     * Out bound messages will be created but the messages will not
     * be delivered to the clients.
     *
     * @return test flag
     */
    public Boolean getTestMessageFlag() {
        return test_message_flag;
    }

    /**
     * If this flag is set to true then this will be processed and
     * Out bound messages will be created but the messages will not
     * be delivered to the clients.
     *
     * @param test_message_flag test flag
     */
    public void setTestMessageFlag(Boolean test_message_flag) {
        this.test_message_flag = test_message_flag;
    }

    /**
     *
     * @return Flag that tells SCG to generate the messages but
     *      not to deliver them.
     */
    public String getPauseBeforeTransmit() {
        return pause_before_transmit;
    }

    /**
     *
     * @param pause_before_transmit Flag that tells SCG to generate the
     *      messages but not to deliver them.
     */
    public void setPauseBeforeTransmit(String pause_before_transmit) {
        this.pause_before_transmit = pause_before_transmit;
    }

    /**
     * This is the timestamp after which all paused messages will
     * expire and will be removed from the outbound queue. The
     * maximum expiry time is 24h. If application creates a message
     * request with longer pause expiry time, the message request
     * will fail.
     *
     * @return timestamp
     */
    public String getPauseExpiryTime() {
        return pause_expiry_time;
    }

    /**
     * his is the timestamp after which all paused messages will
     * expire and will be removed from the outbound queue. The
     * maximum expiry time is 24h. If application creates a message
     * request with longer pause expiry time, the message request
     * will fail.
     *
     * @param pause_expiry_time timestamp
     */
    public void setPauseExpiryTime(String pause_expiry_time) {
        this.pause_expiry_time = pause_expiry_time;
    }

    /**
     * Change the state on the server to TRANSMITTING
     * @throws ScgException on error
     */
    public void resume() throws ScgException {
        setAndUpdateState("TRANSMITTING");
    }

    /**
     * Cancel pending processing
     * @throws ScgException on error
     */
    public void cancel() throws ScgException {
        setAndUpdateState("CANCELED");
    }

    private void setAndUpdateState(String state) throws ScgException {
        Resource.StateRequest sreq = new Resource.StateRequest();
        sreq.state = state;
        ((Resource)getResource()).setState(getId(), sreq);
    }

    /**
     * Delete a MessageRequest on the server
     *
     * The MessageRequest instance must be received
     * from the server. You cannot delete an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(getId());
    }

    /**
     * Get the list of messages associated with this MessageRequest
     * @param options Filters are the same as for Message.Resource.List()
     * @return Iterator to the result-set.
     * @throws ScgException on error
     */
    public ItemItetaror<Message> listMessages(Map<String, String> options) throws ScgException {
        return ((Resource)getResource()).listMessages(getId(), options);
    }

    /**
     * Get the list of messages associated with this MessageRequest
     * @param options Filters are the same as for Message.Resource.List()
     * @param parameters  List Parameters
     * @return Iterator to the result-set.
     * @throws ScgException on error
     */
    public ItemItetaror<Message> listMessages(Map<String, String> options,
            ListParameters parameters) throws ScgException {
        return ((Resource)getResource()).listMessages(getId(), options, parameters);
    }

    /**
     * Resource class for MessageRequest
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        static class StateRequest {
            @Expose String state;
        }

        public interface MessageRequestApi {
            @GET("scg-external-api/api/v1/messaging/message_requests")
            Call<ListReturnMapper<MessageRequest>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/messaging/message_requests")
            Call<CreateResponse> create(@Body MessageRequest messageRequest);

            @POST("scg-external-api/api/v1/messaging/message_requests/{id}")
            Call<Object> setState(@Path("id") String id, @Body StateRequest stateRequest);

            @GET("scg-external-api/api/v1/messaging/message_requests/{id}")
            Call<MessageRequest> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/messaging/message_requests/{id}")
            Call<Object> delete(@Path("id") String id);

            @GET("scg-external-api/api/v1/messaging/message_requests/{id}/messages")
            Call<ListReturnMapper<Message>> listMessages(@Path("id") String id,
                    @QueryMap Map<String, String> options);
        }

        private MessageRequestApi messageRequestApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            messageRequestApi_ = session_.GetApi(MessageRequestApi.class,
                    MessageRequest.class, this,  builder -> {
                        // We need to serialize live Messages.
                        builder.registerPostProcessor(Message.class,
                                new BaseData.Deserialize<>(
                                        new Message.Resource(session_),
                                        Message.class));
                    });
        }

        /**
         *
         * @param session Instance of Session to use
         */
        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        /**
         * Create an instance of a MessageRequest on the server.
         * @param messageRequest object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final MessageRequest messageRequest) throws ScgException {
            return ExecuteWithRetry(() -> Session.Execute(messageRequestApi_.create(messageRequest)).id, 0);
        }

        public boolean setState(final String id, final StateRequest stateRequest) throws ScgException {
            ExecuteWithRetry(() -> Session.Execute(messageRequestApi_.setState(id, stateRequest)), 0);
            return true;
        }

        /**
         * Get a MessageRequest object from the server.
         * @param id id of the MessageRequest you want.
         * @return Instance
         * @throws ScgException on error
         */
        public MessageRequest get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> Session.Execute(messageRequestApi_.get(id)), 0);
        }

        /**
         * List MessageRequests
         *
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *          - id
         *          - from
         *          - conversation_id
         *          - to
         *          - campaign_id
         *          - program_id
         *          - subject
         *          - application_id
         *          - external_id
         *          - state
         *          - scheduled_delivery_time
         *          - expiry_time
         *          - test_message_flag
         *          - pause_before_transmit
         *          - pause_expiry_time
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<MessageRequest> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> ExecuteWithRetry(() -> Session.Execute(messageRequestApi_.list(opts)), 0));
        }

        /**
         * List MessageRequests
         *
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *          - id
         *          - from
         *          - conversation_id
         *          - to
         *          - campaign_id
         *          - program_id
         *          - subject
         *          - application_id
         *          - external_id
         *          - state
         *          - scheduled_delivery_time
         *          - expiry_time
         *          - test_message_flag
         *          - pause_before_transmit
         *          - pause_expiry_time
         * @param parameters  List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<MessageRequest> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> ExecuteWithRetry(() -> Session.Execute(messageRequestApi_.list(opts)), 0));
        }

        /**
         * Delete a MessageRequest
         * @param id id of the MessageRequests to delete on the server.
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> Session.Execute(messageRequestApi_.delete(id)), 0);
            return true;
        }

        public ItemItetaror<Message> listMessages(final String rid,
                Map<String, String> options) {
            return genericList(
                options,
                null,
                opts -> ExecuteWithRetry(() -> Session.Execute(messageRequestApi_.listMessages(rid, opts)), 0));
        }

        public ItemItetaror<Message> listMessages(final String rid,
                Map<String, String> options,
                ListParameters parameters) {
            return genericList(
                options,
                parameters,
                opts -> ExecuteWithRetry(() -> Session.Execute(messageRequestApi_.listMessages(rid, opts)), 0));
        }
    }
}
