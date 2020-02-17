/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import com.google.gson.annotations.Expose;
import java.math.BigDecimal;
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
 * A Message resource is created for every MO or MT message that is
 * processed by SCG. For MT messages, there is an Message created for
 * every recipient of the corresponding message request. For MO
 * messages there is one message created. This resource is read only
 * to an application.
 *
 * @author jaase
 */
public class Message extends BaseData {

    // Read only
    @Expose(serialize = false) String id;

    // Read/Write
    @Expose private String message_request_id;
    @Expose private String external_transaction_ids;
    @Expose private String external_message_request_id;
    @Expose private String application_id;
    @Expose private String application_tracking_id;
    @Expose private String conversation_id;
    @Expose private String campaign_id;
    @Expose private String direction;
    @Expose private String customer_sender_id;
    @Expose private String from_address;
    @Expose private String to_address;
    @Expose private String state;
    @Expose private String failure_code;
    @Expose private String failure_details;
    @Expose private String subject;
    @Expose private String body;
    @Expose private Long sent_date;
    @Expose private Long delivered_date;
    @Expose private Long converted_date;
    @Expose private String conversion_info_source;
    @Expose private String reply_to;
    @Expose private List<String> attachments;
    @Expose private String type;
    @Expose private String message_delivery_provider;
    @Expose private String contact_id;
    @Expose private BigDecimal price;
    @Expose private String language;
    @Expose private String failed_translation;
    @Expose private String protocol_error;
    @Expose private String failed_origin_id;
    @Expose private String failover;
    @Expose private String scheduled_delivery_time;
    @Expose private String expiry_time;
    @Expose private Long created_date;
    @Expose private String last_update_date;

    /**
     *
     * @return Unique identifier of this Message
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return The unique id of the associated message request resource.
     */
    public String getMessageRequestId() {
        return message_request_id;
    }

    public String getExternalMessageRequestId() {
        return external_message_request_id;
    }

    public String getType() {
        return type;
    }

    public String getMessageDeliveryProvider() {
        return message_delivery_provider;
    }

    public String getContactId() {
        return contact_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getLanguage() {
        return language;
    }

    public String getFailedTranslation() {
        return failed_translation;
    }

    public String getProtocolError() {
        return protocol_error;
    }

    public String getFailedOriginId() {
        return failed_origin_id;
    }

    public String getFailover() {
        return failover;
    }

    /**
     *
     * @return This is list of id-s (one for every fragment) issued for this
     * message by the downstream messaging system.
     */
    public String getExternalTransactionIds() {
        return external_transaction_ids;
    }

    /**
     * ID of the application that has created the message. May not
     * be present on incoming messages when the received message
     * could not be associated with a previous MT message
     *
     * @return application id.
     */
    public String getApplicationId() {
        return application_id;
    }

    /**
     * Application provided unique tracking ID for the message.
     * Can be used as an alternative key in query methods.
     *
     * @return tracking ID
     */
    public String getApplicationTrackingId() {
        return application_tracking_id;
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
     * @return A unique identifier for a particular
     *      messaging campaign or program
     */
    public String getCampaignId() {
        return campaign_id;
    }

    /**
     * Message direction- MO for received messages and MT sent messages
     * @return MO or MT
     */
    public String getDirection() {
        return direction;
    }

    /**
     * The sender_id associated with the message delivery channel.
     * For MT messages this will be the sender_id used to deliver
     * the message. For MO messages this will be the sender_id
     * corresponding to the downstream system which generated the
     * message (determined by SMG based on the from/to address
     * accordingly)
     *
     * @return sender id
     */
    public String getCustomerSenderId() {
        return customer_sender_id;
    }

    /**
     *
     * @return The sender address.
     */
    public String getFromAddress() {
        return from_address;
    }

    /**
     *
     * @return The recipient address.
     */
    public String getToAddress() {
        return to_address;
    }

    /**
     * The status of the message to this recipient.
     * Valid values for MT messages are:
     * <p>
     *   - CREATED (message was created and is in the queue of SMG)
     * <p>
     *   - SENT (message was sent to the outbound delivery system)
     * <p>
     *   - DELIVERED (message was delivered to end-device of the user)
     * <p>
     *   - READ (message was read by the user)
     * <p>
     *   - CONVERTED (message was converted i.e. end user took action upon message)
     * <p>
     *   - FAILED (message delivery failed)
     * <p>
     *   - EXPIRED (message was too old to be sent)
     * <p>
     *   - SCHEDULED (message is created but not submitted for sending)
     * <p>
     *   - TEST (message was created but the request was a test request)
     * <p>
     *   - PAUSED (message was created but the request was to pause before delivery)
     * <p>
     *   - DELETED
     * <p>
     * <p>
     * SMG will automatically set the status of the message to
     * converted when the message contains a 'tracked' URL and the
     * link was clicked by the recipient.
     *<p>
     * Alternatively the customer also externally can track conversion
     * and could update the message status put applying the PUT method
     * and specifying status = CONVERTED and the conversion timestamp
     *<p>
     * Valid values for MO Messages are:
     * <p>
     *   - RECEIVED (received at SMG)
     * <p>
     *   - PROCESSED (when the customer has accessed the message and updated the status)
     * <p>
     *   - DELETED (when the message was deleted by the customer)
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @return INVALID_RECIPIENT, NO_CONSENT, OTHER
     */
    public String getFailureCode() {
        return failure_code;
    }

    /**
     *
     * @return text description that gives more detail on failed messages
     */
    public String getFailureDetails() {
        return failure_details;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public Long getSent_date() {
        return sent_date;
    }

    public Long getDeliveredDate() {
        return delivered_date;
    }

    public Long getConvertedDate() {
        return converted_date;
    }

    /**
     * Supplies information where the conversion information comes
     * from. SMG/URL means the message was marked as converted by the
     * link tracking service. For customer converted messages, the
     * content of this attribute is determined by the customer and
     * must be provided on the PUT request which marks the message as
     * 'converted'.
     *
     * @return source.
     */
    public String getConversionInfoSource() {
        return conversion_info_source;
    }

    /**
     *
     * @return The Message ID of a previously delivered message which the
     *      current message is a reply to.
     */
    public String getReplyTo() {
        return reply_to;
    }

    /**
     *
     * @return A list of attachment IDs which are associated with this message.
     */
    public List<String> getAttachments() {
        return attachments;
    }

    /**
     * This is the user specific time as calculated by the SCG based on
     * the destination time zone and requested time zone calculation.
     *
     * @return The date and time that this message should be delivered
     */
    public String getScheduledDeliveryTime() {
        return scheduled_delivery_time;
    }

    /**
     * This is the timestamp after which the messages will no longer
     * be sent to the downstream system for delivery
     *
     * @return expiry time.
     */
    public String getExpiryTime() {
        return expiry_time;
    }

    public Long getCreatedDate() {
        return created_date;
    }

    public String getLastUpdate_date() {
        return last_update_date;
    }

    /**
     * Delete a Message on the server
     *
     * The Message instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * You can delete a Message if you have the ID using
     * the Resource::Delete method.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(getId());
    }

    /**
     * Set the state of the message on the server to PROCESSED
     * @throws ScgException on error
     */
    public void setStateProcessed() throws ScgException {
        Resource.StateRequest stateReq = new Resource.StateRequest();
        stateReq.state = "PROCESSED";
        ((Resource)getResource()).setState(id, stateReq);
    }

    /**
     * Set the state of the message on the server to CONVERTED
     * @throws ScgException on error
     */
    public void setStateConverted() throws ScgException {
        Resource.StateRequest stateReq = new Resource.StateRequest();
        stateReq.state = "CONVERTED";
        ((Resource)getResource()).setState(id, stateReq);
    }

    /**
     * Resource class for Message
     */
    static public class Resource extends ResourceBase {

        public static class CreateResponse {
            @Expose String id = "<none>";
        }

        static class StateRequest {
            @Expose public String state = null;
        }

        public interface MessageApi {
            @GET("scg-external-api/api/v1/messaging/messages")
            Call<ListReturnMapper<Message>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/messaging/messages/{id}")
            Call<Object> setState(@Path("id") String id, @Body StateRequest stateRequest);

            @GET("scg-external-api/api/v1/messaging/messages/{id}")
            Call<Message> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/messaging/messages/{id}")
            Call<Object> delete(@Path("id") String id);
        }

        private MessageApi messageApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            messageApi_ = session_.GetApi(MessageApi.class, Message.class, this);
        }

        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        public boolean setState(final String id, final StateRequest stateRequest) throws ScgException {
            ExecuteWithRetry(() -> Session.Execute(messageApi_.setState(id, stateRequest)), 0);
            return true;
        }

        /**
         * Get a Message object from the server.
         * @param id id of the Message you want.
         * @return Instance
         * @throws ScgException on error
         */
        public Message get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> Session.Execute(messageApi_.get(id)), 0);
        }

        /***
         * List Messages
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - message_request_id
         *        - external_transaction_ids
         *        - application_id
         *        - application_tracking_id
         *        - conversation_id
         *        - campaign_id
         *        - direction
         *        - customer_sender_id
         *        - from_address
         *        - to_address
         *        - state
         *        - failure_code
         *        - subject
         *        - body
         *        - sent_date
         *        - delivered_date
         *        - converted_date
         *        - reply_to
         *        - scheduled_delivery_time
         *        - expiry_time
         * @return Iterator to the result-set.
         * @throws ScgException on errors
         */
        public ItemItetaror<Message> list(Map<String, String> options) throws ScgException {
            return genericList(
                options,
                null,
                opts -> ExecuteWithRetry(() -> Session.Execute(messageApi_.list(opts)), 0));
        }

        /***
         * List Messages
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - message_request_id
         *        - external_transaction_ids
         *        - application_id
         *        - application_tracking_id
         *        - conversation_id
         *        - campaign_id
         *        - direction
         *        - customer_sender_id
         *        - from_address
         *        - to_address
         *        - state
         *        - failure_code
         *        - subject
         *        - body
         *        - sent_date
         *        - delivered_date
         *        - converted_date
         *        - reply_to
         *        - scheduled_delivery_time
         *        - expiry_time
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on errors
         */
        public ItemItetaror<Message> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(
                options,
                parameters,
                opts -> ExecuteWithRetry(() -> Session.Execute(messageApi_.list(opts)), 0));
        }

        /**
         * Delete a Message
         * @return id of the Messages to delete on the server.
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> Session.Execute(messageApi_.delete(id)), 0);
            return true;
        }
    }

}
