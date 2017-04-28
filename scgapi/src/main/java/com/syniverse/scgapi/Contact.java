/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

import com.google.gson.annotations.Expose;
import static com.syniverse.scgapi.BaseData.genericList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * A Contact represents a person/application/entity which the SCG
 * communicates with on behalf of applications. Contacts do not send
 * or receive messages directly via the Syniverse Messaging Gateway,
 * but rather do so directly via the media channel. The distinction
 * between Applications and Contacts is thus based on how the
 * messaging is being performed. Applications (entities using the
 * APIs to initiate messaging) send/receive messages from Contacts
 * (entities which are not using the APIs to initiate messaging.)
 *
 * @author jaase
 */
public class Contact extends BaseData {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager
            .getLogger(Contact.class);


    public class ApplicationToken extends BaseData {
        @Expose(serialize = false) String id = null;
        @Expose(serialize = false) Long application_id = null;
        @Expose(serialize = false) Long created_date = null;
        @Expose(serialize = false) Long last_update_date = null;

        @Expose Long version_number = null;

        @Expose private String message_delivery_provider = null;
        @Expose private String sender_id_address = null;
        @Expose private String token = null;


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

        public String getMessageDeliveryProvider() {
            return message_delivery_provider;
        }

        public void setMessageDeliveryProvider(String message_delivery_provider) {
            this.message_delivery_provider = message_delivery_provider;
        }

        public String getSenderIdAddress() {
            return sender_id_address;
        }

        public void setSenderIdAddress(String sender_id_address) {
            this.sender_id_address = sender_id_address;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Long getVersionNumber() {
            return version_number;
        }

        public void setVersionNumber(Long version_number) {
            this.version_number = version_number;
        }
    }

    public class AccessToken extends BaseData {
        @Expose Integer duration = null;
        @Expose Long version_number = null;
        @Expose(serialize = false) String id = null;
        @Expose(serialize = false) Long expiry_time = null;
        @Expose(serialize = false) Long application_id = null;
        @Expose(serialize = false) Long created_date = null;
        @Expose(serialize = false) Long last_update_date = null;

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Long getVersionNumber() {
            return version_number;
        }

        public void setVersionNumber(Long version_number) {
            this.version_number = version_number;
        }

        public String getId() {
            return id;
        }

        public Long getExpiryTime() {
            return expiry_time;
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
    }
    /**
     * all known addresses of the customer
     */
    public static class Address {
        @Expose private Integer priority = null;
        @Expose private String designation = null;
        @Expose private String use = null;
        @Expose private String source = null;
        @Expose private String status = null;
        @Expose private String line1 = null;
        @Expose private String line2 = null;
        @Expose private String city = null;
        @Expose private String state = null;
        @Expose private String province = null;
        @Expose private String zip = null;
        @Expose private String country = null;

        /**
         *
         * @return priority of the address, 1 highest
         */
        public Integer getPriority() {
            return priority;
        }

        /**
         *
         * @param priority priority of the address, 1 highest
         */
        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        /**
         *
         * @return specifies type of the address- home, work
         */
        public String getDesignation() {
            return designation;
        }

        /**
         *
         * @param designation specifies type of the address- home, work
         */
        public void setDesignation(String designation) {
            this.designation = designation;
        }

        /**
         *
         * @return billing, shipping
         */
        public String getUse() {
            return use;
        }

        /**
         *
         * @param use billing, shipping
         */
        public void setUse(String use) {
            this.use = use;
        }

        /**
         *
         * @return specifies where the address comes from, company specific
         */
        public String getSource() {
            return source;
        }

        /**
         *
         * @param source specifies where the address comes from, company specific
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         *
         * @return status of the address- VALID_NEW, VALID_CONF, INVALID, EXPIRED
         */
        public String getStatus() {
            return status;
        }

        /**
         *
         * @param status status of the address- VALID_NEW, VALID_CONF, INVALID, EXPIRED
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         *
         * @return first address line
         */
        public String getLine1() {
            return line1;
        }

        /**
         *
         * @param line1 first address line
         */
        public void setLine1(String line1) {
            this.line1 = line1;
        }

        /**
         *
         * @return second address line
         */
        public String getLine2() {
            return line2;
        }

        /**
         *
         * @param line2 second address line
         */
        public void setLine2(String line2) {
            this.line2 = line2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    /**
     * all known accounts of the customer
     */
    class Account {
        @Expose private String priority;
        @Expose private String designation;
        @Expose private String source;
        @Expose private String state;
        @Expose private String username;
        @Expose private String domain;
        @Expose private String access_token;

        /**
         *
         * @return user defined account priority
         */
        public String getPriority() {
            return priority;
        }

        /**
         *
         * @param priority user defined account priority
         */
        public void setPriority(String priority) {
            this.priority = priority;
        }

        /**
         *
         * @return primary designation of the account- work, private
         */
        public String getDesignation() {
            return designation;
        }

        /**
         *
         * @param designation primary designation of the account- work, private
         */
        public void setDesignation(String designation) {
            this.designation = designation;
        }

        /**
         *
         * @return specifies where the address comes from, company specific
         */
        public String getSource() {
            return source;
        }

        /**
         *
         * @param source specifies where the address comes from, company specific
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         *
         * @return status of the address- VALID_NEW, VALID_CONF, INVALID, EXPIRED
         */
        public String getState() {
            return state;
        }

        /**
         *
         * @param state status of the address- VALID_NEW, VALID_CONF, INVALID, EXPIRED
         */
        public void setState(String state) {
            this.state = state;
        }

        /**
         *
         * @return email, social, web, loyalty
         */
        public String getUsername() {
            return username;
        }

        /**
         *
         * @param username email, social, web, loyalty
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         *
         * @return account domain. For example starwood.com
         */
        public String getDomain() {
            return domain;
        }

        /**
         *
         * @param domain account domain. For example starwood.com
         */
        public void setDomain(String domain) {
            this.domain = domain;
        }

        /**
         *
         * @return the access token to the account where the account requires
         * some oauth type authorization
         */
        public String getAccessToken() {
            return access_token;
        }

        /**
         *
         * @param access_token the access token to the account where the account requires
         * some oauth type authorization
         */
        public void setAccessToken(String access_token) {
            this.access_token = access_token;
        }
    }

    /**
     * all known accounts of the customer
     */
    class Device {
        @Expose private String priority;
        @Expose private String designation;
        @Expose private String source;
        @Expose private String state;
        @Expose private String msisdn;
        @Expose private String carrier;
        @Expose private String mac_address;
        @Expose private String uuid;
        @Expose private String manufacturer;
        @Expose private String model;
        @Expose private String os;

        /**
         *
         * @return user defined account priority
         */
        public String getPriority() {
            return priority;
        }

        /**
         *
         * @param priority user defined account priority
         */
        public void setPriority(String priority) {
            this.priority = priority;
        }

        /**
         *
         * @return primary designation of the account- work, private
         */
        public String getDesignation() {
            return designation;
        }

        /**
         *
         * @param designation primary designation of the account- work, private
         */
        public void setDesignation(String designation) {
            this.designation = designation;
        }

        /**
         *
         * @return specifies where the address comes from, company specific
         */
        public String getSource() {
            return source;
        }

        /**
         *
         * @param source specifies where the address comes from, company specific
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         *
         * @return status of the address- VALID_NEW, VALID_CONF, INVALID, EXPIRED
         */
        public String getState() {
            return state;
        }

        /**
         *
         * @param state status of the address- VALID_NEW, VALID_CONF, INVALID, EXPIRED
         */
        public void setState(String state) {
            this.state = state;
        }

        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public String getMac_address() {
            return mac_address;
        }

        public void setMac_address(String mac_address) {
            this.mac_address = mac_address;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        /**
         *
         * @return device manufacturer, for example Apple
         */
        public String getManufacturer() {
            return manufacturer;
        }

        /**
         *
         * @param manufacturer device manufacturer, for example Apple
         */
        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        /**
         *
         * @return model of the device, for example iPhone 5s
         */
        public String getModel() {
            return model;
        }

        /**
         *
         * @param model model of the device, for example iPhone 5s
         */
        public void setModel(String model) {
            this.model = model;
        }

        /**
         *
         * @return operating system of the device, for example iOS 8.3
         */
        public String getOs() {
            return os;
        }

        /**
         *
         * @param os operating system of the device, for example iOS 8.3
         */
        public void setOs(String os) {
            this.os = os;
        }
    }

    /**
     * all known customer demographic
     */
    class Demographic {
        @Expose private String name;
        @Expose private String source;
        @Expose private String score;

        /**
         *
         * @return demographic name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name demographic name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return where the information was obtained
         */
        public String getSource() {
            return source;
        }

        /**
         *
         * @param source where the information was obtained
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         *
         * @return how likely the information is, floating point value 0...1
         */
        public String getScore() {
            return score;
        }

        /**
         *
         * @param score how likely the information is, floating point value 0...1
         */
        public void setScore(String score) {
            this.score = score;
        }
    }

    /**
     * all known customer preferences
     */
    class Interest {
        @Expose private String code;
        @Expose private String name;
        @Expose private String source;
        @Expose private String score;

        /**
         *
         * @return interest code
         */
        public String getCode() {
            return code;
        }

        /**
         *
         * @param code interest code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         *
         * @return interest name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name interest name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return where the information was obtained
         */
        public String getSource() {
            return source;
        }

        /**
         *
         * @param source where the information was obtained
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         *
         * @return how likely the information is, floating point value 0...1
         */
        public String getScore() {
            return score;
        }

        /**
         *
         * @param score how likely the information is, floating point value 0...1
         */
        public void setScore(String score) {
            this.score = score;
        }
    }

    // Read only members
    @Expose(serialize = false)
    private String id = null;
    @Expose(serialize = false) Long application_id = null;

    // Special members
    @Expose private Long version_number = null;

    // Read/write members
    @Expose private String external_id = null;
    @Expose private String first_name = null;
    @Expose private String last_name = null;
    @Expose private String birth_date = null;
    @Expose private Date first_acquisition_date = null;
    @Expose private Date last_acquisition_date = null;
    @Expose private String primary_mdn = null;
    @Expose private String primary_addr_line1 = null;
    @Expose private String primary_addr_line2 = null;
    @Expose private String primary_addr_city = null;
    @Expose private String primary_addr_zip = null;
    @Expose private String primary_addr_state = null;
    @Expose private String primary_email_addr = null;
    @Expose private String primary_social_handle = null;
    @Expose private List<Address> address_list = null;
    @Expose private List<Account> account_list = null;
    @Expose private List<Device> device_list = null;
    @Expose private List<Interest> interest_list = null;
    @Expose private List<Demographic> demographic_list = null;
    @Expose private String extended_attributes = null;
    @Expose private String voice_preference = null;
    @Expose private String preferred_language = null;
    @Expose private List<String> social_handles = null;


    public Contact() {
    }

    /**
     * Unique identifier, allocated by SMG at the time
     * of contact creation
     * @return id
     */
    public String getId() {
        return id;
    }

    public Long getApplicationId() {
        return application_id;
    }

    public String getVoicePreference() {
        return voice_preference;
    }

    public void setVoicePreference(String voice_preference) {
        this.voice_preference = voice_preference;
    }

    public String getPreferredLanguage() {
        return preferred_language;
    }

    public void setPreferredLanguage(String preferred_language) {
        this.preferred_language = preferred_language;
    }

    public List<String> getSocialHandles() {
        return social_handles;
    }

    public void setSocialHandles(List<String> social_handles) {
        this.social_handles = social_handles;
    }

    public Long getVersionNumber() {
        return version_number;
    }

    /**
     * the id of the contact at the customer system. Can be used by
     * customer to update the contacts.
     *
     * @return external id
     */
    public String getExternalId() {
        return external_id;
    }

    /**
     * the id of the contact at the customer system. Can be used by
     * customer to update the contacts.
     *
     * @param external_id external id
     */
    public void setExternalId(String external_id) {
        this.external_id = external_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(String birth_date) {
        this.birth_date = birth_date;
    }

    /**
     * The timestamp of the first customer acquisition - i.e. the
     * date when the customer has been for the first time opted
     * in to any program.
     * @return timestamp
     */
    public Date getFirstAcquisitionDate() {
        return first_acquisition_date;
    }

    /**
     * TThe timestamp of the first customer acquisition - i.e. the
     * date when the customer has been for the first time opted
     * in to any program.
     *
     * @param firstAcquisitionDate timestamp
     */
    public void setFirstAcquisitionDate(Date firstAcquisitionDate) {
        this.first_acquisition_date = firstAcquisitionDate;
    }

    /**
     * The timestamp of the last customer acquisition i.e. the date
     * when the customer has for the last time sent an optin keyword
     * or performed a doubleoptin
     *
     * @return timestamp
     */
    public Date getLastAcquisitionDate() {
        return last_acquisition_date;
    }

    /**
     * The timestamp of the last customer acquisition i.e. the date
     * when the customer has for the last time sent an optin keyword
     * or performed a doubleoptin
     *
     * @param lastAcquisitionDate timestamp
     */
    public void setLastAcquisitionDate(Date lastAcquisitionDate) {
        this.last_acquisition_date = lastAcquisitionDate;
    }

    /**
     * The primary phone number of the customer. This is the number
     * used to track the double optin for the customer.
     *
     * @return mdn
     */
    public String getPrimaryMdn() {
        return primary_mdn;
    }

    /**
     * The primary phone number of the customer. This is the number
     * used to track the double optin for the customer.
     *<p>
     * Note: Tis fiels is required when creating new objects.
     *
     * @param primary_mdn mdn
     */
    public void setPrimaryMdn(String primary_mdn) {
        this.primary_mdn = primary_mdn;
    }

    public String getPrimaryAddrLine1() {
        return primary_addr_line1;
    }

    public void setPrimaryAddrLine1(String primary_addr_line1) {
        this.primary_addr_line1 = primary_addr_line1;
    }

    public String getPrimaryAddrLine2() {
        return primary_addr_line2;
    }

    public void setPrimaryAddrLine2(String primary_addr_line2) {
        this.primary_addr_line2 = primary_addr_line2;
    }

    public String getPrimaryAddrCity() {
        return primary_addr_city;
    }

    public void setPrimaryAddrCity(String primary_addr_city) {
        this.primary_addr_city = primary_addr_city;
    }

    public String getPrimaryAddrZip() {
        return primary_addr_zip;
    }

    public void setPrimaryAddrZip(String primary_addr_zip) {
        this.primary_addr_zip = primary_addr_zip;
    }

    public String getPrimaryAddrState() {
        return primary_addr_state;
    }

    public void setPrimaryAddrState(String primary_addr_state) {
        this.primary_addr_state = primary_addr_state;
    }

    public String getPrimaryEmailAddr() {
        return primary_email_addr;
    }

    public void setPrimaryEmailAddr(String primary_email_addr) {
        this.primary_email_addr = primary_email_addr;
    }

    public String getPrimarySocialHandle() {
        return primary_social_handle;
    }

    public void setPrimarySocialHandle(String primary_social_handle) {
        this.primary_social_handle = primary_social_handle;
    }

    public List<Address> getAddressList() {
        return address_list;
    }

    public void setAddressList(List<Address> address_list) {
        this.address_list = address_list;
    }

    public List<Account> getAccountList() {
        return account_list;
    }

    public void setAccountList(List<Account> account_list) {
        this.account_list = account_list;
    }

    public List<Device> getDeviceList() {
        return device_list;
    }

    public void setDeviceList(List<Device> device_list) {
        this.device_list = device_list;
    }

    public List<Interest> getInterestList() {
        return interest_list;
    }

    public void setInterestList(List<Interest> interest_list) {
        this.interest_list = interest_list;
    }

    public List<Demographic> getDemographicList() {
        return demographic_list;
    }

    public void setDemographicList(List<Demographic> demographic_list) {
        this.demographic_list = demographic_list;
    }

    /**
     * JSON string of all extended attributes of the customer. Any
     * list of key-value pairs is supported. Also composite attributes
     * can be stored.
     *
     * @return json string
     */
    public String getExtendedAttributes() {
        return extended_attributes;
    }

    /**
     * JSON string of all extended attributes of the customer. Any
     * list of key-value pairs is supported. Also composite attributes
     * can be stored.
     *
     * @param extended_attributes json string
     */
    public void setExtendedAttributes(String extended_attributes) {
        this.extended_attributes = extended_attributes;
    }

    /**
     * Update a Contact on the server
     *
     * The Contact instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void update() throws ScgException {
        ((Resource)getResource()).update(this);
    }

    ApplicationTokenResource getApplicationTokenResource() throws Error.NoResourceAttached {
        return new ApplicationTokenResource(
                ((Resource)getResource()).getSession(), getId());
    }

    AccessTokenResource getAccessTokenResource() throws Error.NoResourceAttached {
        return new AccessTokenResource(
                ((Resource)getResource()).getSession(), getId());
    }

    /**
     * Delete a Contact on the server
     *
     * The Contact instance must be received
     * from the server. You cannot delete an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(this);
    }

    /**
     * Resource class for Contact
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface ContactApi {
            @GET("scg-external-api/api/v1/contacts")
            Call<ListReturnMapper<Contact>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/contacts")
            Call<CreateResponse> create(@Body Contact contact);

            @POST("scg-external-api/api/v1/contacts/{id}")
            Call<Object> update(@Path("id") String id, @Body Contact contact);

            @GET("scg-external-api/api/v1/contacts/{id}")
            Call<Contact> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/contacts/{id}")
            Call<Object> delete(@Path("id") String id);
        }

        private ContactApi contactApi_;


        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            contactApi_ = session_.GetApi(ContactApi.class, Contact.class, this);
        }

        /**
         *
         * @param session Session instance to use
         */
        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        Session getSession() {
            return session_;
        }

        /**
         * Create an instance of a Contact on the server.
         * @param contact Contact object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final Contact contact) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(contactApi_.create(contact)).id;
            }, 0);
        }

        public boolean update(final Contact contact) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(contactApi_.update(contact.getId(), contact));
            }, 0);
            return true;
        }

        /**
         * Get a Contact object from the server.
         * @param id id of the Contact you want.
         * @return Instance
         * @throws ScgException on error
         */
        public Contact get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(contactApi_.get(id));
            }, 0);
        }

        /**
         * List Contacts
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - external_id
         *        - first_name
         *        - last_name
         *        - birth_date
         *        - first_acquisition_date
         *        - last_acquisition_date
         *        - primary_mdn
         *        - primary_addr_line1
         *        - primary_addr_line2
         *        - primary_addr_city
         *        - primary_addr_zip
         *        - primary_addr_state
         *        - primary_email_addr
         *        - primary_social_handle
         *        - created_date
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<Contact> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(contactApi_.list(opts));
                }, 0);
            });
        }

        /**
         * List Contacts
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - external_id
         *        - first_name
         *        - last_name
         *        - birth_date
         *        - first_acquisition_date
         *        - last_acquisition_date
         *        - primary_mdn
         *        - primary_addr_line1
         *        - primary_addr_line2
         *        - primary_addr_city
         *        - primary_addr_zip
         *        - primary_addr_state
         *        - primary_email_addr
         *        - primary_social_handle
         *        - created_date
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<Contact> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(contactApi_.list(opts));
                }, 0);
            });
        }

        public boolean delete(final Contact contact) throws ScgException {
            return delete(contact.getId());
        }

        /**
         * Delete a Contact
         * @param id  id of the Contact to delete on the server
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(contactApi_.delete(id));
            }, 0);
            return true;
        }
    }

    static class ApplicationTokenResource extends ResourceBase  {
        private final String contactId;

        public ApplicationTokenResource(final Session session,
                final String contactId) {
            session_ = session; // session_ from ResourceBase
            this.contactId = contactId;
            ResetApiChannel();
        }

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            api_ = session_.GetApi(ApplicationTokenResource.Api.class, ApplicationToken.class, this);
        }

        public interface Api {
            @GET("scg-external-api/api/v1/contacts/{contactId}/application_tokens")
            Call<ListReturnMapper<ApplicationToken>> list(
                    @Path("contactId") String contactId,
                    @QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/contacts/{contactId}/application_tokens")
            Call<Resource.CreateResponse> create(
                    @Path("contactId") String contactId,
                    @Body ApplicationToken at);

            @GET("scg-external-api/api/v1/contacts/{contactId}/application_tokens/{id}")
            Call<ApplicationToken> get(
                    @Path("contactId") String contactId,
                    @Path("id") String id);

            @DELETE("scg-external-api/api/v1/contacts/{contactId}/application_tokens/{id}")
            Call<Object> delete(
                    @Path("contactId") String contactId,
                    @Path("id") String id);
        }

        private Api api_;

        /**
         * Create an instance of a Contact on the server.
         * @param at ApplicationToken object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final ApplicationToken at) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(api_.create(contactId, at)).id;
            }, 0);
        }

        /**
         * Get a Contact object from the server.
         * @param id id of the Contact you want.
         * @return Instance
         * @throws ScgException on error
         */
        public ApplicationToken get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(api_.get(contactId, id));
            }, 0);
        }

        public ItemItetaror<ApplicationToken> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(api_.list(contactId, opts));
                }, 0);
            });
        }

        public boolean delete(final ApplicationToken at) throws ScgException {
            return delete(at.getId());
        }

        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(api_.delete(contactId, id));
            }, 0);
            return true;
        }
    }

    static class AccessTokenResource extends ResourceBase  {
        private final String contactId;

        public AccessTokenResource(final Session session,
                final String contactId) {
            session_ = session; // session_ from ResourceBase
            this.contactId = contactId;
            ResetApiChannel();
        }

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            api_ = session_.GetApi(AccessTokenResource.Api.class, AccessToken.class, this);
        }

        public interface Api {
            @GET("scg-external-api/api/v1/contacts/{contactId}/access_tokens")
            Call<ListReturnMapper<AccessToken>> list(
                    @Path("contactId") String contactId,
                    @QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/contacts/{contactId}/access_tokens")
            Call<Resource.CreateResponse> create(
                    @Path("contactId") String contactId,
                    @Body AccessToken at);

            @GET("scg-external-api/api/v1/contacts/{contactId}/access_tokens/{id}")
            Call<AccessToken> get(
                    @Path("contactId") String contactId,
                    @Path("id") String id);

            @DELETE("scg-external-api/api/v1/contacts/{contactId}/access_tokens/{id}")
            Call<Object> delete(
                    @Path("contactId") String contactId,
                    @Path("id") String id);
        }

        private Api api_;

        /**
         * Create an instance of a Contact on the server.
         * @param at ApplicationToken object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final AccessToken at) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(api_.create(contactId, at)).id;
            }, 0);
        }

        /**
         * Get a Contact object from the server.
         * @param id id of the Contact you want.
         * @return Instance
         * @throws ScgException on error
         */
        public AccessToken get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(api_.get(contactId, id));
            }, 0);
        }

        public ItemItetaror<AccessToken> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(api_.list(contactId, opts));
                }, 0);
            });
        }

        public boolean delete(final AccessToken at) throws ScgException {
            return delete(at.getId());
        }

        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(api_.delete(contactId, id));
            }, 0);
            return true;
        }
    }
}
