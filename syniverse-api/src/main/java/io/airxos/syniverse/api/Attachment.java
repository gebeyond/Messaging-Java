/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

import com.google.gson.annotations.Expose;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * The resource used to store attachment meta-data and the Attachment
 * ID that is used to upload and download the attachment content.
 *
 * @author jaase
 */
public class Attachment extends BaseData {

    // Read Only
    @Expose(serialize = false) private String id;
    @Expose(serialize = false) private Long application_id;
    @Expose(serialize = false) private String state;
    @Expose(serialize = false) private Long created_date;
    @Expose(serialize = false) private Long last_update_date;

    // Special
    @Expose private Long version_number;

    // Read / Write
    @Expose private String filename;
    @Expose private String name;
    @Expose private String type;
    @Expose private Long size;

    public String getId() {
        return id;
    }

    /**
     * ID of the application that has created the
     * attachment. May not be present on attachments on MO messages
     * @return application id
     */
    public Long getApplicationId() {
        return application_id;
    }

    /**
     *
     * @return CREATED or UPLOADED
     */
    public String getState() {
        return state;
    }

    /**
     * For a MT message (one which was sent via this API), this
     * indicates the time when the API was called to request message
     * delivery. For a MO message, this is the point in time when the
     * SMG was notified of the inbound message from the operator
     * network/mediation channel.
     *
     * @return date
     */
    public Long getCreatedDate() {
        return created_date;
    }

    public Long getLastUpdateDate() {
        return last_update_date;
    }

    public Long getVersionNumber() {
        return version_number;
    }

    public void setVersionNumber(Long _version_number) {
        this.version_number = _version_number;
    }

    /**
     *
     * @return Original file name of the attachment
     */
    public String getFilename() {
        return filename;
    }

    /**
     *
     * @param filename Original file name of the attachment
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * The name of the attachment (e.g. it’s filename or other
     * non-generated identifier) The value of this field should be
     * unique for a given message.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * The name of the attachment (e.g. it’s filename or other
     * non-generated identifier) The value of this field should be
     * unique for a given message.
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The MIME type of the attachment (if known)
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type The MIME type of the attachment (if known)
     */
    public void setType(String type) {
        this.type = type;
    }

    public void changeName(final String name) throws ScgException {
        Attachment att = new Attachment();
        att.id = getId();
        att.version_number = getVersionNumber();
        att.setName(name);
        ((Resource)getResource()).update(att);
    }

    /**
     *
     * @return The size, in bytes, of the attachment body
     */
    public Long getSize() {
        return size;
    }

    /**
     * The size, in bytes, of the attachment body
     *
     * This will be filled in automatically by the server whne the
     * attachment is uploaded.
     *
     * @param size The size, in bytes, of the attachment body
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * Delete a Attachment on the server
     *
     * The Attachment instance must be received
     * from the server. You cannot update an instance
     * you have instantiated yourself.
     *
     * @throws ScgException on error
     */
    public void delete() throws ScgException {
        ((Resource)getResource()).delete(this);
    }

    /**
     * Upload a file as the content of the attachment
     *
     * @param path Path to a file to send
     * @throws ScgException on error
     */
    public void uploadContent(final String path) throws ScgException {
        ((Resource)getResource()).upload(path, getId());
    }

    /**
     * Upload a file as the content of the attachment
     *
     * @param path Path to a file to send
     * @throws ScgException on error
     * @throws IOException on IO errors
     */
    public void downloadContent(final String path) throws ScgException, IOException {
        ((Resource)getResource()).download(path, getId());
    }

    /**
     * Resource class for Attachment
     */

    static public class Resource extends ResourceBase {

        static class CreateResponse {
            @Expose String id = "<none>";
        }

        public interface AttachmentApi {
            @GET("scg-external-api/api/v1/messaging/attachments")
            Call<BaseData.ListReturnMapper<Attachment>> list(@QueryMap Map<String, String> options);

            @POST("scg-external-api/api/v1/messaging/attachments")
            Call<CreateResponse> create(@Body Attachment attachment);

            @POST("scg-external-api/api/v1/messaging/attachments/{id}")
            Call<Object> update(@Path("id") String id, @Body Attachment attachment);

            @GET("scg-external-api/api/v1/messaging/attachments/{id}")
            Call<Attachment> get(@Path("id") String id);

            @DELETE("scg-external-api/api/v1/messaging/attachments/{id}")
            Call<Object> delete(@Path("id") String id);

            @Headers("Content-Type: application/json; charset=utf-8")
            @POST("scg-external-api/api/v1/messaging/attachments/{id}/access_tokens")
            Call<CreateResponse> getToken(@Path("id") String id);

            @POST("scg-attachment/api/v1/messaging/attachments/{token}/content")
            Call<Object> upload(@Path("token") String token, @Body RequestBody file);

            @GET("scg-attachment/api/v1/messaging/attachments/{token}/content")
            Call<ResponseBody> download(@Path("token") String token);
        }

        private AttachmentApi attachmentApi_;

        @Override
        void ResetApi() {
            ResetApiChannel();
        }

        void ResetApiChannel() {
            attachmentApi_ = session_.GetApi(AttachmentApi.class, Attachment.class, this);
        }

        /**
         *
         * @param session Instance of session to use
         */
        public Resource(final Session session) {
            session_ = session; // session_ from ResourceBase
            ResetApiChannel();
        }

        /**
         * Create an instance of a Attachment on the server.
         * @param attachment Attachment object where the relevant data-
         *      members are set to valid values.
         * @return id of the new object.
         * @throws ScgException on error
         */
        public String create(final Attachment attachment) throws ScgException {
            return ExecuteWithRetry(() -> Session.Execute(attachmentApi_.create(attachment)).id, 0);
        }

        public boolean update(final Attachment attachment) throws ScgException {
            ExecuteWithRetry(() -> Session.Execute(attachmentApi_.update(attachment.getId(), attachment)), 0);
            return true;
        }

        /**
         * Get a Attachment object from the server.
         * @param id id of the Attachment you want.
         * @return Instance
         * @throws ScgException on error
         */
        public Attachment get(final String id) throws ScgException {
            return ExecuteWithRetry(() -> Session.Execute(attachmentApi_.get(id)), 0);
        }

        /**
         * List Attachments
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *         - id
         *         - application_id
         *         - name
         *         - type
         *         - size
         *         - filename
         *         - state
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public BaseData.ItemItetaror<Attachment> list(Map<String, String> options) throws ScgException {
            return genericList(
                options,
                null,
                opts -> ExecuteWithRetry(() -> Session.Execute(attachmentApi_.list(opts)), 0));
        }

        /**
         * List Attachments
         * @param options Key/Value pairs of attributes that can
         *      filter the result-set.
         *         - id
         *         - application_id
         *         - name
         *         - type
         *         - size
         *         - filename
         *         - state
         * @param parameters List Parameters
         * @return Iterator to the result-set.
         * @throws ScgException on error
         */
        public BaseData.ItemItetaror<Attachment> list(Map<String, String> options,
                ListParameters parameters) throws ScgException {
            return genericList(options, parameters, opts -> Session.Execute(attachmentApi_.list(opts)));
        }

        // Use Attachment.delete()
        public boolean delete(final Attachment att) throws ScgException {
            return delete(att.getId());
        }

        /**
         * Delete a Attachment
         * @param id id of the Attachments to delete on the server.
         * @return true
         * @throws ScgException on error
         */
        public boolean delete(final String id) throws ScgException {
            ExecuteWithRetry(() -> Session.Execute(attachmentApi_.delete(id)), 0);
            return true;
        }

        // Use Attachment.uploadContent()
        public void upload(final String attachmentPath, final String id)
                throws ScgException {

            String token = ExecuteWithRetry(() -> Session.Execute(attachmentApi_.getToken(id)).id, 0);

            ExecuteWithRetry(() -> {
                File file = new File(attachmentPath);
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("Application/octet-stream"), file);

                return Session.Execute(attachmentApi_.upload(token, requestBody));
            }, 0);
        }

        // Use Attachment.downloadContent()
        public void download(final String attachmentPath, final String id)
                throws ScgException, IOException {

            String token = ExecuteWithRetry(() -> Session.Execute(attachmentApi_.getToken(id)).id, 0);

            ResponseBody body = ExecuteWithRetry(() -> Session.Execute(attachmentApi_.download(token)), 0);

            File dstFile = new File(attachmentPath);

            byte[] fileReader = new byte[4096];
            try (final InputStream inputStream = body.byteStream();
                 final OutputStream outputStream = new FileOutputStream(dstFile)) {
                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                }
                outputStream.flush();
            }
        }
    }
}
