/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi;

import com.google.gson.annotations.Expose;
import static com.syniverse.scgapi.BaseData.genericList;
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
 * This resource can represent a collection of Contacts meant to
 * support bulk messaging operations (e.g. the ability to deliver
 * messages to multiple contacts via a single API call.) Contact
 * group can be represented as a fixed list of contacts or can be
 * represented as a specific criteria (filter) and each time the
 * group is referenced, SCG will compute the list of contacts that
 * match the criteria to define the contacts within the contact group.
 * Contact groups are owned by Clients and composed of Contacts.
 *
 * @author jaase
 */
public class ContactGroup extends BaseData {

    // Read only members
    @Expose(serialize = false) private String id = null;
    @Expose(serialize = false) private String status;
    @Expose(serialize = false) private Long member_count;
    @Expose(serialize = false) private Long created_date;
    @Expose(serialize = false) private Long last_update_date;
    @Expose(serialize = false) private String type;
    @Expose(serialize = false) private Long application_id = null;

    // Special members
    @Expose private String version_number;

    // Read/write members
    @Expose private String external_id;
    @Expose private String name;
    @Expose private String description;
    @Expose private String criteria;

    /**
     *
     * @return Customer supplied id of the contact group
     */
    public String getExternalId() {
        return external_id;
    }

    /**
     *
     * @param external_id Customer supplied id of the contact group
     */
    public void setExternalId(String external_id) {
        this.external_id = external_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Filter definition for creation of the group. Users can use
     * either criteria or members.
     *
     * @return criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * Filter definition for creation of the group. Users can use
     * either criteria or members.
     *
     * @param criteria criteria
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getId() {
        return id;
    }

    public Long getApplicationId() {
        return application_id;
    }


    /**
     *
     * @return Processing, Ready
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @return Count of members in the group.
     */
    public Long getMemberCount() {
        return member_count;
    }

    public Long getCreatedDate() {
        return created_date;
    }

    public Long getLastUpdateDate() {
        return last_update_date;
    }

    /**
     * The group type:
     * <p>
     *   - DYNAMIC meaning the list of members associated with the
     *          group is calculated dynamically whenever the group
     *          is referenced by applying the CRITERIA to the customer
     *          contact list
     * <p>
     *   - STATIC meaning the list of members is determined at the
     *          time of contact group creation and hence static.
     *          Customers will be also able to add or remove contacts
     *          from a STATIC group by accessing the contacts list
     *          associated with the group.
     *
     * @return DYNAMIC or STATIC
     */
    public String getType() {
        return type;
    }

    public String getVersionNumber() {
        return version_number;
    }

    public void setVersionNumber(String version_number) {
        this.version_number = version_number;
    }

    /**
     * Update a ContactGroup on the server.
     *
     * The ContactGroup instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void update() throws ScgException {
        ((Resource)getResource()).update(this);
    }

    /**
     * Delete a ContactGroup on the server
     *
     * The ContactGroup instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(this);
    }

    /**
     * Add a contact to a STATIC group
     *
     * The ContactGroup instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @param contact contact to add
     * @throws ScgException on error
     */
    public void AddContact(final Contact contact) throws ScgException {
        AddContact(contact.getId());
    }

    /**
     * Add a contact to a STATIC group
     *
     * The ContactGroup instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     * @param id id of the contact to add.
     * @throws ScgException on error
     */
    public void AddContact(final String id) throws ScgException {
        List<String> contacts = new ArrayList<>();
        contacts.add(id);
        ((Resource)getResource()).addContact(getId(), contacts);
        AddContact(contacts);
    }

    /**
     * Bulk add contacts.
     * @param contacts List of contact id's to add
     * @throws ScgException on error
     */
    public void AddContact(final List<String> contacts) throws ScgException {
        ((Resource)getResource()).addContact(getId(), contacts);
    }

    /**
     * Delete a contact to a STATIC group
     *
     * The Contact itself is not deleted, just it's
     * inclusion in this Contact Group.
     *
     * The ContactGroup instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @param contact contact to remove from the group
     * @throws ScgException on error
     */
    public void DeleteContact(final Contact contact) throws ScgException {
        ((Resource)getResource()).deleteContact(getId(), contact.getId());
    }

    /**
     * Delete a contact to a STATIC group
     *
     * The Contact itself is not deleted, just it's
     * inclusion in this Contact Group.
     *
     * The ContactGroup instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @param id id for the contact to remove from the group.
     * @throws ScgException on error
     */
    public void DeleteContact(final String id) throws ScgException {
        ((Resource)getResource()).deleteContact(getId(), id);
    }

    /**
     * List contacts from this group.
     *
     * The ContactGroup instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @param options Please see Contact.Resource.List()
     * @return Iterator to result set.
     * @throws ScgException on error
     */
    public ItemItetaror<Contact> listContacts(Map<String, String> options) throws ScgException {
        return ((Resource)getResource()).listContacts(getId(), options);
    }

    /**
     * List contacts from this group.
     *
     * The ContactGroup instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @param options Please see Contact.Resource.List()
     * @param parameter List Parameter
     * @return Iterator to result set.
     * @throws ScgException on error
     */
    public ItemItetaror<Contact> listContacts(Map<String, String> options,
            ListParameters parameter) throws ScgException {
        return ((Resource)getResource()).listContacts(getId(), options, parameter);
    }

    /**
     * Resource class for ContactGroup
     */
    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        static class AddContactsPayload {
            @Expose List<String> contacts;
        }

        public interface ContactGroupApi {
            @GET("scg-external-api/api/v1/contact_groups")
            Call<ListReturnMapper<ContactGroup>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/contact_groups")
            Call<CreateResponse> create(@Body ContactGroup contact);

            @POST("scg-external-api/api/v1/contact_groups/{id}")
            Call<Object> update(@Path("id") String id, @Body ContactGroup contact);

            @GET("scg-external-api/api/v1/contact_groups/{id}")
            Call<ContactGroup> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/contact_groups/{id}")
            Call<Object> delete(@Path("id") String id);

            @POST("scg-external-api/api/v1/contact_groups/{id}/contacts")
            Call<Object> addContact(@Path("id") String id,
                    @Body AddContactsPayload contacts);

            @DELETE("scg-external-api/api/v1/contact_groups/{gid}/contacts/{cid}")
            Call<Object> deleteContact(@Path("gid") String gid,
                    @Path("cid") String cid);

            @GET("scg-external-api/api/v1/contact_groups/{id}/contacts")
            Call<ListReturnMapper<Contact>> listContacts(@Path("id") String id,
                    @QueryMap Map<String, String> options);
        }

        private ContactGroupApi contactGroupApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            contactGroupApi_ = session_.GetApi(ContactGroupApi.class,
                    ContactGroup.class, this, builder -> {
                        // We need to serialize live Contacts.
                        builder.registerPostProcessor(Contact.class,
                                new BaseData.Deserialize(
                                        new Contact.Resource(session_),
                                        Contact.class));
                    });
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
         * Create an instance of a ContactGroup on the server.
         * @param contact object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final ContactGroup contact) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(contactGroupApi_.create(contact)).id;
            }, 0);
        }

        public boolean update(final ContactGroup contact) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(contactGroupApi_.update(contact.getId(), contact));
            }, 0);
            return true;
        }

        /**
         * Get a ContactGroup object from the server.
         * @param id id of the ContactGroup you want.
         * @return Instance
         * @throws ScgException on error
         */
        public ContactGroup get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> {
                return Session.Execute(contactGroupApi_.get(id));
            }, 0);
        }

        /**
         * List ContactGroups
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - external_id
         *        - name
         *        - type
         *        - status
         *        - created_date
         *        - last_update_date
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<ContactGroup> list(Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(contactGroupApi_.list(opts));
                }, 0);
            });
        }

        /**
         * List ContactGroups
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *        - id
         *        - external_id
         *        - name
         *        - type
         *        - status
         *        - created_date
         *        - last_update_date
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public ItemItetaror<ContactGroup> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(contactGroupApi_.list(opts));
                }, 0);
            });
        }


        public boolean delete(final ContactGroup contact) throws ScgException {
            return delete(contact.getId());
        }

        /**
         * Delete a ContactGroup
         * @param id id of the ContactGroups to delete on the server.
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(contactGroupApi_.delete(id));
            }, 0);
            return true;
        }

        public boolean addContact(final String groupId,
                final List<String> contacts) throws ScgException {
            if (contacts == null) {
                throw new RuntimeException("contacts can not be null");
            }
            AddContactsPayload payload = new AddContactsPayload();
            payload.contacts = contacts;

            ExecuteWithRetry(() -> {
                return Session.Execute(contactGroupApi_.addContact(groupId, payload));
            }, 0);
            return true;
        }

        public boolean deleteContact(final String gid, final String cid) throws ScgException {
            ExecuteWithRetry(() -> {
                return Session.Execute(contactGroupApi_.deleteContact(gid, cid));
            }, 0);
            return true;
        }

        public ItemItetaror<Contact> listContacts(final String id,
                Map<String, String> options) throws ScgException {
            return genericList(options, null, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(contactGroupApi_.listContacts(id, opts));
                }, 0);
            });
        }

        public ItemItetaror<Contact> listContacts(final String id,
                Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> {
                return ExecuteWithRetry(() -> {
                    return (ListReturnMapper)Session.Execute(contactGroupApi_.listContacts(id, opts));
                }, 0);
            });
        }
    }
}
