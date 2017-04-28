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
import retrofit2.http.QueryMap;

/**
 *
 * @author jaase
 */
public class Bridge extends BaseData {
    @Expose(serialize = false) private String id;
    @Expose(serialize = false) private String external_id;
    @Expose(serialize = false) private Long completed_time = null;
    @Expose(serialize = false) private Long activated_time = null;
    @Expose(serialize = false) private Long created_date = null;
    @Expose(serialize = false) private Long last_updated_date = null;

    @Expose private Integer version_number = null;

    @Expose private String state = null;
    @Expose private Boolean bridge_audio = null;
    @Expose private List<String> call_ids;

    public String getId() {
        return id;
    }

    public String getExternalId() {
        return external_id;
    }

    public List<String> getCallIds() {
        return call_ids;
    }

    public void setCallIds(List<String> callIds) {
        call_ids = callIds;
    }

    public Long getCompletedTime() {
        return completed_time;
    }

    public Long getActivatedTime() {
        return activated_time;
    }

    public Long getLastUpdatedDate() {
        return last_updated_date;
    }

    public Long getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(Long created_date) {
        this.created_date = created_date;
    }

    public Integer getVersionNumber() {
        return version_number;
    }

    public void setVersionNumber(Integer version_number) {
        this.version_number = version_number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getBridge_audio() {
        return bridge_audio;
    }

    public void setBridgeAudio(Boolean bridge_audio) {
        this.bridge_audio = bridge_audio;
    }

    /**
     * Resource class for Bridge
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface BridgeApi {
            @GET("scg-external-api/api/v1/calling/bridges")
            Call<ListReturnMapper<Bridge>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/calling/bridges")
            Call<CreateResponse> create(@Body Bridge bridge);

            @POST("scg-external-api/api/v1/calling/bridges/{id}")
            Call<Object> update(@Path("id") String id, @Body Bridge bridge);

            @GET("scg-external-api/api/v1/calling/bridges/{id}")
            Call<Bridge> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/calling/bridges/{id}")
            Call<Object> delete(@Path("id") String id);
        }

        private BridgeApi bridgeApi_;

        @Override
        void ResetApi() {
            ResetApiBridge();
        }

        void ResetApiBridge() {
            bridgeApi_ = session_.GetApi(BridgeApi.class, Bridge.class, this);
        }

        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiBridge();
        }

        /**
         * Create an instance of a Bridge on the server.
         * @param bridge Bridge object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final Bridge bridge) throws ScgException {

            return ExecuteWithRetry(() -> {
                return Session.Execute(bridgeApi_.create(bridge)).id;}, 0);
        }

        /**
         * Create a new bridge without setting any properties on it.
         * @throws ScgException
         */
        public String create() throws ScgException {

            final Bridge bridge = new Bridge();

            return ExecuteWithRetry(() -> {
                return Session.Execute(bridgeApi_.create(bridge)).id;}, 0);
        }

        public boolean update(final Bridge bridge) throws ScgException {

            ExecuteWithRetry(() -> {
                return Session.Execute(bridgeApi_.update(
                        bridge.getId(),
                        bridge));
            }, 0);
            return true;
        }

        /**
         * Get a Bridge object from the server.
         * @param id id of the Bridge you want.
         * @return Instance
         * @throws ScgException  on error
         */
        public Bridge get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(bridgeApi_.get(id));
            }, 0);
        }

        /**
         * List Bridges
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - external_id
         *        - call_ids
         *        - state
         *        - bridge_audio
         *        - completed_time
         *        - activated_time
         *        - created_date
         *        - last_updated_date
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<Bridge> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(bridgeApi_.list(opts));
                }, 0);
            });
        }

        /**
         * List Bridges
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - external_id
         *        - call_ids
         *        - state
         *        - bridge_audio
         *        - completed_time
         *        - activated_time
         *        - created_date
         *        - last_updated_date
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<Bridge> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {

            return ExecuteWithRetry(() -> {
                return genericList(options, parameters, opts -> {
                    return (ListReturnMapper)Session.Execute(bridgeApi_.list(opts));
                });
            }, 0);
        }

        /**
         * Delete a Bridge
         * @param ch Instance to delete on server
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final Bridge ch) throws ScgException {
            return delete(ch.getId());
        }

        /**
         * Delete a Bridge
         * @param id Id of object to delete on server
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(bridgeApi_.delete(id));
            }, 0);
            return true;
        }
    }

}
