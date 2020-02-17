/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import com.google.gson.annotations.Expose;
import java.util.Map;
import java.util.Set;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * This is an immutable resource that represents the list of sender
 * classes that an be associated with a sender id.
 *
 * The sender class determines the type of messages
 * (notifications, alerts, commercial ….) as well as message
 * throughput and sending window restriction
 *
 * Instances of this class is read-only, as they cannot be
 * created or manipulated or changed (on the server) from
 * this API.
 *
 * @author jaase
 */
public class SenderIdClass extends BaseData {

    @Expose private String id = null;
    @Expose private String name = null;
    @Expose private String description = null;
    @Expose private String designation = null;
    @Expose private Set<String> applicable_countries = null;
    @Expose private Long country_peak_throughput = null;
    @Expose private Long country_peak_total_throughput = null;
    @Expose private Long country_daily_throughput = null;
    @Expose private String delivery_window = null;
    @Expose private Long created_date = null;
    @Expose private Long last_update_date = null;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     *
     * @return Primary channel designation: commercial, alerts, notifications
     */
    public String getDesignation() {
        return designation;
    }

    /**
     *
     * @return list of countries to which the sender class applies, coma
     *      delimited using iso country code. Allow a keyword of 'OTHER'
     *      for sender classes that are not country specific.
     */
    public Set<String> getApplicableCountries() {
        return applicable_countries;
    }

    /**
     *
     * @return Maximum outbound channel throughput for a given customer per
     *      destination country expressed in transactions per second
     */
    public Long getCountryPeakThroughput() {
        return country_peak_throughput;
    }

    /**
     *
     * @return Maximum outbound channel throughput for all customers using
     *      the short or long code per destination country expressed in
     *      transactions per second (this is the physical limit of the
     *      sender address)
     */
    public Long getCountryPeakTotalThroughput() {
        return country_peak_total_throughput;
    }

    /**
     *
     * @return Maximum outbound channel throughput for a given customer per
     *      destination country expressed in transactions per day
     */
    public Long getCountryDailyThroughput() {
        return country_daily_throughput;
    }

    /**
     *
     * @return Allowed delivery time for the messages. Applying the time
     * validation, the time is to be considered in the time zone of
     * the recipient. This is a JSON string with a list of allowed
     * times by destination country. Countries are represented by an
     * ISO country code. A special country code of 'DEFAULT' is to
     * be used for the default country. For each country a delivery
     * window can be specified. Each weekday can have different
     * delivery window times. As weekday names, use of
     * following days- MON, TUE, WED, THU, FRI, SAT, SUN as well as
     * two additional keywords- HOL (representing local holiday in
     * the country) and OTH (representing all other days of week).
     */
    public String getDeliveryWindow() {
        return delivery_window;
    }

    public Long getCreatedDate() {
        return created_date;
    }

    public Long getLastUpdateDate() {
        return last_update_date;
    }

    /**
     * Resource class for SenderIdClass
     */
    static public class Resource extends ResourceBase {

        public interface SenderIdClassApi {
            @GET("scg-external-api/api/v1/messaging/sender_id_classes")
            Call<ListReturnMapper<SenderIdClass>> list(@QueryMap Map<String, String> options);
        }

        private SenderIdClassApi senderIdClassApi_;

        /**
         * @internal
         */
        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        /**
         * @internal
         */
        void ResetApiChannel() {
            senderIdClassApi_ = session_.GetApi(SenderIdClassApi.class, Contact.class, this);
        }

        /**
         *
         * @param session Session to use.
         */
        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        /**
         *
         * @param options Filter for the result-set.
         *      - id
         *      - name
         *      - designation
         *      - applicable_countries
         *      - country_peak_throughput
         *      - country_daily_throughput
         * @return Iterator to the result-set.
         * @throws ScgException
         */
        public ItemItetaror<SenderIdClass> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> ExecuteWithRetry(() -> Session.Execute(senderIdClassApi_.list(opts)), 0));
        }

        /**
         *
         * @param options Filter for the result-set.
         *      - id
         *      - name
         *      - designation
         *      - applicable_countries
         *      - country_peak_throughput
         *      - country_daily_throughput
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on errors
         */
        public ItemItetaror<SenderIdClass> list(
            Map<String, String> options, ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> ExecuteWithRetry(() -> Session.Execute(senderIdClassApi_.list(opts)), 0));
        }
    }
}
