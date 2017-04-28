/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import static com.syniverse.scgapi.BaseData.genericList;
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
 * Keywords are defined by customer and represent special words that
 * customers can send to a number belonging to a customer. Keywords
 * are detected by SCG and added to the customer profile. Certain
 * actions can be associated with a keyword received from a customer.
 *
 * @author jaase
 */
public class Keywords extends BaseData {

    // Read Only
    @Expose(serialize = false) private String id;
    @Expose(serialize = false) private Long last_update_date;
    @Expose(serialize = false) private Long application_id = null;

    // Special
    @Expose private String version_number;

    // Read/Write
    @Expose private String name;
    @Expose private String description;
    @Expose private String value;

    @Expose @SerializedName("case") private String caseValue;
    @Expose private String sender_id;
    @Expose private String valid_from;
    @Expose private String valid_to;
    @Expose private String associated_info;
    @Expose private String campaign_id;
    @Expose private String type;
    @Expose private List<String> actions;
    @Expose private String reply_template;

    /**
     *
     * @return unique keyword id
     */
    public String getId() {
        return id;
    }

    public Long getApplicationId() {
        return application_id;
    }

    public Long getLastUpdateDate() {
        return last_update_date;
    }

    public String getVersionNumber() {
        return version_number;
    }

    public void setVersionNumber(String version_number) {
        this.version_number = version_number;
    }

    /**
     *
     * @return name of the keyword
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name of the keyword
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return description of the keyword
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description description of the keyword
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the keyword. For example 'SPORTS'. The keyword can be expressed
     *      in form of a regular expression. POSIX Extended syntax is supported.
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value the keyword. For example 'SPORTS'. The keyword can be expressed
     *      in form of a regular expression. POSIX Extended syntax is supported.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return SENSITIVE/INSENSITIVE specifies whether the keyword is case
     *      sensitive or not. default is not case sensitive.
     */
    public String getCase() {
        return caseValue;
    }

    /**
     *
     * @param caseValue SENSITIVE/INSENSITIVE specifies whether the keyword is case
     *      sensitive or not. default is not case sensitive.
     */
    public void setCase(String caseValue) {
        this.caseValue = caseValue;
    }

    /**
     * The sender id on which the keyword is valid. When this is
     * empty, any sender id associated with the given customer will
     * be sensitive to this keyword.\
     *
     * @return sender id
     */
    public String getSenderId() {
        return sender_id;
    }

    /**
     * The sender id on which the keyword is valid. When this is
     * empty, any sender id associated with the given customer will
     * be sensitive to this keyword.
     *
     * @param sender_id sender id
     */
    public void setSenderId(String sender_id) {
        this.sender_id = sender_id;
    }

    /**
     *
     * @return the date range for which the keyword is valid
     */
    public String getValidFrom() {
        return valid_from;
    }

    /**
     *
     * @param valid_from the date range for which the keyword is valid
     */
    public void setValidFrom(String valid_from) {
        this.valid_from = valid_from;
    }

    /**
     *
     * @return the date range for which the keyword is valid
     */
    public String getValidTo() {
        return valid_to;
    }

    /**
     *
     * @param valid_to the date range for which the keyword is valid
     */
    public void setValidTo(String valid_to) {
        this.valid_to = valid_to;
    }

    /**
     * Additional information, value is at customer discretion, this
     * attribute is added to the keyword event whenever the keyword
     * is detected
     *
     * @return information
     */
    public String getAssociatedInfo() {
        return associated_info;
    }

    /**
     * Additional information, value is at customer discretion, this
     * attribute is added to the keyword event whenever the keyword
     * is detected
     *
     * @param associated_info information
     */
    public void setAssociatedInfo(String associated_info) {
        this.associated_info = associated_info;
    }

    /**
     *
     * @return The campaign id
     */
    public String getCampaignId() {
        return campaign_id;
    }

    /**
     *
     * @param campaign_id The campaign id
     */
    public void setCampaignId(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    /**
     * the type of the keyword:
     * <br>
     *   - RESERVED those are the platform specific keywords that can
     *          not be used by customer in any campaign. Those
     *          keywords will always exist, the company will never
     *          need to add them.
     * <br>
     *   - COMPANY- company specific keywords
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * the type of the keyword:
     * <br>
     *   - RESERVED those are the platform specific keywords that can
     *          not be used by customer in any campaign. Those
     *          keywords will always exist, the company will never
     *          need to add them.
     * <br>
     *   - COMPANY- company specific keywords
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Actions to be applied when the keyword has been received.
     * Multiple actions can be specified.
     *<br>
     * - OPTOUT: mark the user as explicitly opted out (and forward
     *          message to Consent Manager)
     * <br>
     * - OPTIN: mark the user as explicitly opted in (and forward
     *          message to Consent Manager)
     * <br>
     * - STORE: no action, just record in user profile
     *
     * @return actions actions
     */
    public List<String> getActions() {
        return actions;
    }

    /**
     * Actions to be applied when the keyword has been received.
     * Multiple actions can be specified.
     *<br>
     * - OPTOUT:  mark the user as explicitly opted out (and forward
     *          message to Consent Manager)
     * <br>
     * - OPTIN: mark the user as explicitly opted in (and forward
     *          message to Consent Manager)
     * <br>
     * - STORE: no action, just record in user profile
     *
     * @param actions actions
     */
    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    /**
     * If specified then SMG will send a predefined message back to
     * the user. This attribute will contain the name of the message
     * template to be used in the automatic reply.
     *
     * @return template
     */
    public String getReplyTemplate() {
        return reply_template;
    }

    /**
     * If specified then SMG will send a predefined message back to
     * the user. This attribute will contain the name of the message
     * template to be used in the automatic reply.
     *
     * @param reply_template template
     */
    public void setReplyTemplate(String reply_template) {
        this.reply_template = reply_template;
    }

    /**
     * Update a Keyword on the server
     *
     * The Keyword instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void update() throws ScgException {
        ((Resource)getResource()).update(this);
    }

    /**
     * Delete a Keyword on the server
     *
     * The Keyword instance must be received
     * from the server. You cannot delete an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(this);
    }

    /**
     *  Resource class for Keyword
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface KeywordsApi {
            @GET("scg-external-api/api/v1/messaging/keywords")
            Call<BaseData.ListReturnMapper<Keywords>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/messaging/keywords")
            Call<CreateResponse> create(@Body Keywords keywords);

            @POST("scg-external-api/api/v1/messaging/keywords/{id}")
            Call<Object> update(@Path("id") String id, @Body Keywords keywords);

            @GET("scg-external-api/api/v1/messaging/keywords/{id}")
            Call<Keywords> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/messaging/keywords/{id}")
            Call<Object> delete(@Path("id") String id);
        }

        private KeywordsApi keywordsApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            keywordsApi_ = session_.GetApi(KeywordsApi.class, Keywords.class, this);
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
         * Create an instance of a Keyword on the server.
         * @param keyword object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final Keywords keyword) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(keywordsApi_.create(keyword)).id;
            }, 0);
        }

        public boolean update(final Keywords keywords) throws ScgException {
             ExecuteWithRetry(() -> {
                return Session.Execute(keywordsApi_.update(keywords.getId(), keywords));
            }, 0);
            return true;
        }

        /**
         * Get a Keyword object from the server.
         * @param id id of the Keyword you want.
         * @return Instance
         * @throws ScgException on error
         */
        public Keywords get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(keywordsApi_.get(id));
            }, 0);
        }

        /**
         * List Keywords
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - value
         *        - sender_id
         *        - campaign_id
         *        - type
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public BaseData.ItemItetaror<Keywords> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (BaseData.ListReturnMapper)Session.Execute(keywordsApi_.list(opts));
                }, 0);
            });
        }

        /**
         * List Keywords
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - value
         *        - sender_id
         *        - campaign_id
         *        - type
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public BaseData.ItemItetaror<Keywords> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> {
                return ExecuteWithRetry(() -> {
                    return (BaseData.ListReturnMapper)Session.Execute(keywordsApi_.list(opts));
                }, 0);
            });
        }

        public boolean delete(final Keywords kw) throws ScgException {
            return delete(kw.getId());
        }

        /**
         * Delete a Keyword
         * @param id id of the Keywords to delete on the server.
         * @return true
         * @throws ScgException
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(keywordsApi_.delete(id));
            }, 0);
            return true;
        }
    }
}
