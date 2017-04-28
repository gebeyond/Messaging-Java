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
 * Channels represent a group of sender addresses. Channels are
 * created to create a mechanism to deliver messages over different
 * media (if sender addresses of different type are used in a
 * channel) or to increase throughput (by adding multiple sender
 * ids to a single channel).
 *
 * @author jaase
 */
public class Channel extends BaseData {

    // Read only
    @Expose(serialize = false) private String id = null;
    @Expose(serialize = false) private String ownership = null;
    @Expose(serialize = false) private Long created_date = null;
    @Expose(serialize = false) private Long last_update_date = null;
    @Expose(serialize = false) private Long application_id = null;

    // Special
    @Expose private Integer version_number = null;

    // Read / Write
    @Expose private String name = null;
    @Expose private String priority = null;
    @Expose private String description = null;

    public String getId() {
        return id;
    }


    public Long getApplicationId() {
        return application_id;
    }

    /**
     *
     * @return SHARED/PRIVATE/PUBLIC
     */
    public String getOwnership() {
        return ownership;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Determines the channel priority- high, normal or low. SMG
     * will deliver to target system first for messages in high
     * priority channels, then normal and at the end low priority.
     * Message size does not affect the message priority.
     *
     * @return  high, normal or low
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Determines the channel priority- high, normal or low. SMG
     * will deliver to target system first for messages in high
     * priority channels, then normal and at the end low priority.
     * Message size does not affect the message priority.
     *
     * @param priority  high, normal or low
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Update a Channel on the server
     *
     * The Channel instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void update() throws ScgException {
        ((Resource)getResource()).update(this);
    }

    /**
     *  Delete a Channel on the server
     *
     * The Channel instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * You can delete a Channel if you have the ID using
     * the Resource::Delete method.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(this);
    }

    /**
     * Resource class for Channel
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface ChannelApi {
            @GET("scg-external-api/api/v1/messaging/channels")
            Call<ListReturnMapper<Channel>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/messaging/channels")
            Call<CreateResponse> create(@Body Channel channel);

            @POST("scg-external-api/api/v1/messaging/channels/{id}")
            Call<Object> update(@Path("id") String id, @Body Channel channel);

            @GET("scg-external-api/api/v1/messaging/channels/{id}")
            Call<Channel> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/messaging/channels/{id}")
            Call<Object> delete(@Path("id") String id);
        }

        private ChannelApi channelApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            channelApi_ = session_.GetApi(ChannelApi.class, Channel.class, this);
        }

        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        /**
         * Create an instance of a Channel on the server.
         * @param channel Channel object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final Channel channel) throws ScgException {

            return ExecuteWithRetry(() -> {
                return Session.Execute(channelApi_.create(channel)).id;}, 0);
        }

        public boolean update(final Channel channel) throws ScgException {

            ExecuteWithRetry(() -> {
                return Session.Execute(channelApi_.update(
                        channel.getId(),
                        channel));
            }, 0);
            return true;
        }

        /**
         * Get a Channel object from the server.
         * @param id id of the Channel you want.
         * @return Instance
         * @throws ScgException  on error
         */
        public Channel get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(channelApi_.get(id));
            }, 0);
        }

        /**
         * List Channels
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - name
         *        - priority
         *        - ownership
         *        - created_date
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<Channel> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(channelApi_.list(opts));
                }, 0);
            });
        }

        /**
         * List Channels
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - name
         *        - priority
         *        - ownership
         *        - created_date
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<Channel> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {

            return ExecuteWithRetry(() -> {
                return genericList(options, parameters, opts -> {
                    return (ListReturnMapper)Session.Execute(channelApi_.list(opts));
                });
            }, 0);
        }

        /**
         * Delete a Channel
         * @param ch Instance to delete on server
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final Channel ch) throws ScgException {
            return delete(ch.getId());
        }

        /**
         * Delete a Channel
         * @param id Id of object to delete on server
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(channelApi_.delete(id));
            }, 0);
            return true;
        }
    }
}
