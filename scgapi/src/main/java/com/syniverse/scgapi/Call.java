/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

import com.google.gson.annotations.Expose;
import static com.syniverse.scgapi.BaseData.genericList;
import java.util.Map;
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
public class Call extends BaseData {

    @Expose(serialize = false) private String id;
    @Expose(serialize = false) private String external_id;
    @Expose(serialize = false) private Long start_time = null;
    @Expose(serialize = false) private Long answer_time = null;
    @Expose(serialize = false) private Long end_time = null;
    @Expose(serialize = false) private Integer chargeable_duration = null;
    @Expose(serialize = false) private String failure_code = null;
    @Expose(serialize = false) private String failure_details = null;
    @Expose(serialize = false) private Long created_date = null;
    @Expose(serialize = false) private Long last_updated_date = null;

    @Expose private Integer version_number = null;

    @Expose private String from = null;
    @Expose private String from_address = null;
    @Expose private String to = null;
    @Expose private Integer answer_timeout = null;
    @Expose private String state = null;
    @Expose private String direction = null;
    @Expose private String bridge_id = null;

    public String getId() {
        return id;
    }

    public String getExternalId() {
        return external_id;
    }

    public Long getStartTime() {
        return start_time;
    }

    public Long getAnswerTime() {
        return answer_time;
    }

    public Long getEndTime() {
        return end_time;
    }

    public Integer getChargeableDuration() {
        return chargeable_duration;
    }

    public String getFailureCode() {
        return failure_code;
    }

    public String getFailureDetails() {
        return failure_details;
    }

    public Long getCreatedDate() {
        return created_date;
    }

    public Long getLastUpdatedDate() {
        return last_updated_date;
    }

    public Integer getVersionNumber() {
        return version_number;
    }

    public void setVersionNumber(Integer version_number) {
        this.version_number = version_number;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromAddress() {
        return from_address;
    }

    public void setFromAddress(String from_address) {
        this.from_address = from_address;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getAnswerTimeout() {
        return answer_timeout;
    }

    public void setAnswerTimeout(Integer answer_timeout) {
        this.answer_timeout = answer_timeout;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBridgeId() {
        return bridge_id;
    }

    public void setBridgeId(String bridge_id) {
        this.bridge_id = bridge_id;
    }


    /**
     * Resource class for Call
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface CallApi {
            @GET("scg-external-api/api/v1/calling/calls")
            retrofit2.Call<BaseData.ListReturnMapper<Call>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/calling/calls")
            retrofit2.Call<CreateResponse> create(@Body Call call);

            @POST("scg-external-api/api/v1/calling/calls/{id}")
            retrofit2.Call<Object> update(@Path("id") String id, @Body Call call);

            @GET("scg-external-api/api/v1/calling/calls/{id}")
            retrofit2.Call<Call> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/calling/calls/{id}")
            retrofit2.Call<Object> delete(@Path("id") String id);
        }

        private CallApi callApi_;

        @Override
        void ResetApi() {
            ResetApiCall();
        }

        void ResetApiCall() {
            callApi_ = session_.GetApi(CallApi.class, Call.class, this);
        }

        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiCall();
        }

        /**
         * Create an instance of a Call on the server.
         * @param call Call object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final Call call) throws ScgException {

            return ExecuteWithRetry(() -> {
                return Session.Execute(callApi_.create(call)).id;}, 0);
        }

        public boolean update(final Call call) throws ScgException {

            ExecuteWithRetry(() -> {
                return Session.Execute(callApi_.update(
                        call.getId(),
                        call));
            }, 0);
            return true;
        }

        /**
         * Get a Call object from the server.
         * @param id id of the Call you want.
         * @return Instance
         * @throws ScgException  on error
         */
        public Call get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(callApi_.get(id));
            }, 0);
        }

        /**
         * List Calls
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - external_id
         *        - call_ids
         *        - state
         *        - call_audio
         *        - completed_time
         *        - activated_time
         *        - created_date
         *        - last_updated_date
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public BaseData.ItemItetaror<Call> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (BaseData.ListReturnMapper)Session.Execute(callApi_.list(opts));
                }, 0);
            });
        }

        /**
         * List Calls
         * @param options filter Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - external_id
         *        - call_ids
         *        - state
         *        - call_audio
         *        - completed_time
         *        - activated_time
         *        - created_date
         *        - last_updated_date
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public BaseData.ItemItetaror<Call> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {

            return ExecuteWithRetry(() -> {
                return genericList(options, parameters, opts -> {
                    return (BaseData.ListReturnMapper)Session.Execute(callApi_.list(opts));
                });
            }, 0);
        }

        /**
         * Delete a Call
         * @param ch Instance to delete on server
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final Call ch) throws ScgException {
            return delete(ch.getId());
        }

        /**
         * Delete a Call
         * @param id Id of object to delete on server
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(callApi_.delete(id));
            }, 0);
            return true;
        }
    }

}
