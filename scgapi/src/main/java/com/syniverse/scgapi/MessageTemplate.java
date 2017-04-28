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
 * Message Templates can be created and associated with a sender id.
 * A message can then be sent with the corresponding name-value
 * pairs to fill in the template. A sender id can be configured to
 * enforce template use so that only messages that use a template
 * can be sent on that sender id To use a template, the message
 * body will contain a special content type with a list of name
 * value pairs as the content.
 *
 * @author jaase
 */
public class MessageTemplate extends BaseData {

    // Read Only
    @Expose(serialize = false) private String id = null;
    @Expose(serialize = false) private Long application_id = null;
    @Expose(serialize = false) private Long created_date = null;
    @Expose(serialize = false) private Long last_update_date = null;

    // Read/Write
    @Expose private String designation = null;
    @Expose private String name = null;
    @Expose private String pattern = null;

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

    /**
     * The template is used either for VALIDATOR or as an actual TEMPLATE.
     *
     * @return VALIDATOR or TEMPLATE
     */
    public String getDesignation() {
        return designation;
    }

    /**
     *The template is used either for VALIDATOR or as an actual TEMPLATE.
     *
     * @param designation VALIDATOR or TEMPLATE
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * template pattern can be used to enforce compliancy of the
     * message body with the submitted program brief. The template
     * pattern allows also to use the templates to form the message
     * body by using the template pattern and replacing the keywords
     * in the pattern with the values of submitted keywords in the
     * message send from the sender id.
     *
     * @return pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * template pattern can be used to enforce compliancy of the
     * message body with the submitted program brief. The template
     * pattern allows also to use the templates to form the message
     * body by using the template pattern and replacing the keywords
     * in the pattern with the values of submitted keywords in the
     * message send from the sender id.
     *
     * @param pattern pattern
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Update a MessageTemplate on the server
     *
     * The MessageTemplate instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void update() throws ScgException {
        ((Resource)getResource()).update(this);
    }

    /**
     * Delete a MessageTemplate on the server
     *
     * The MessageTemplate instance must be received
     * from the server. You cannot delete an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(this);
    }

    /**
     * Resource class for MessageTemplate
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface MessageTemplateApi {
            @GET("scg-external-api/api/v1/messaging/message_templates")
            Call<ListReturnMapper<MessageTemplate>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/messaging/message_templates")
            Call<CreateResponse> create(@Body MessageTemplate messageTemplate);

            @POST("scg-external-api/api/v1/messaging/message_templates/{id}")
            Call<Object> update(@Path("id") String id, @Body MessageTemplate messageTemplate);

            @GET("scg-external-api/api/v1/messaging/message_templates/{id}")
            Call<MessageTemplate> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/messaging/message_templates/{id}")
            Call<Object> delete(@Path("id") String id);
        }

        private MessageTemplateApi messageTemplateApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            messageTemplateApi_ = session_.GetApi(
                    MessageTemplateApi.class, MessageTemplate.class, this);
        }

        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        /**
         * Create an instance of a MessageTemplate on the server.
         *
         * @param messageTemplate object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final MessageTemplate messageTemplate) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(messageTemplateApi_.create(messageTemplate)).id;
            }, 0);
        }

        public boolean update(final MessageTemplate messageTemplate) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(messageTemplateApi_.update(messageTemplate.getId(), messageTemplate));
            }, 0);
            return true;
        }

        /**
         * Get a MessageTemplate object from the server.
         * @param id id of the MessageTemplate you want.
         * @return MessageTemplate
         * @throws ScgException on error
         */
        public MessageTemplate get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(messageTemplateApi_.get(id));
            }, 0);
        }

        /**
         *
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *      - id
         *      - designation
         *      - name
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<MessageTemplate> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(messageTemplateApi_.list(opts));
                }, 0);
            });
        }

        /**
         *
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *      - id
         *      - designation
         *      - name
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<MessageTemplate> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(messageTemplateApi_.list(opts));
                }, 0);
            });
        }

        /**
         * Delete a MessageTemplate
         * @param mt MessageTemplate instance to delete
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final MessageTemplate mt) throws ScgException {
            return delete(mt.getId());
        }

        /**
         * Delete a MessageTemplate
         * @param id ID of MessageTemplate to delete
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(messageTemplateApi_.delete(id));
            }, 0);
            return true;
        }
    }
}
