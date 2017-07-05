/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.tests;

import com.syniverse.scgapi.Attachment;
import com.syniverse.scgapi.ScgException;
import com.syniverse.scgapi.integration.helper.IntegrationTest;
import com.syniverse.scgapi.integration.helper.TestSetup;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.categories.Category;


/**
 *
 * @author jaase
 */
@Category(IntegrationTest.class)
public class AttachmentFT {

    private final TestSetup setup;

    public AttachmentFT() throws Exception {
        setup = new TestSetup();
        Assume.assumeTrue(setup.isTestable());
    }

    // Generate a temporary file with rubbish and return the path
    private String getTestFile(int size) throws IOException {
        String tmpPath = File.createTempFile("ul-attachments", ".tmp").getAbsolutePath();
        try (FileOutputStream fos = new FileOutputStream(tmpPath)) {
            for(int i = 0; i < size; i++) {
                fos.write((byte)i);
            }
        }

        return tmpPath;
    }

    @Test
    public void testsList() throws ScgException {
        List<String> atachments = new ArrayList<>();
        Attachment att = new Attachment();
        att.setName("test_upload");
        att.setType("image/jpeg");
        att.setFilename("foo.jpg");

        Attachment.Resource res = new Attachment.Resource(setup.getSession());
        atachments.add(res.create(att));
        atachments.add(res.create(att));
        atachments.add(res.create(att));

        List<Attachment> rcvList = res.list(null).getAllAsList();
        assertTrue("Invalid list size", rcvList.size() >= atachments.size());

        try {
            for(String id : atachments) {
                res.delete(id);
            }
        } catch(Exception ex) {
            ; // Ignore
        }
    }

    @Test
    public void testUploadDownload() throws ScgException, IOException {
        Attachment att = new Attachment();
        att.setName("test_upload");
        att.setType("image/jpeg");
        att.setFilename("foo.jpg");

        Attachment.Resource res = new Attachment.Resource(setup.getSession());
        String new_id = res.create(att);
        att = res.get(new_id);

        String testFilePath = getTestFile(1024);
        att.uploadContent(testFilePath);

        String dlPath = File.createTempFile("dl-attachments", ".tmp").getAbsolutePath();

        File origin = new File(testFilePath);
        File received = new File(dlPath);

        try {
            att.downloadContent(dlPath);
            assertTrue( "Downloaded attachment don't match the uploaded attachment.",
                    FileUtils.contentEquals(origin, received));
        } finally {
            origin.delete();
            received.delete();
            att.delete();
        }
    }

    @Test(expected = com.syniverse.scgapi.Error.ServerFailure.class)
    public void testsCRUD() throws ScgException {

        Attachment att = new Attachment();
        att.setName("crud");
        att.setType("image/jpeg");
        att.setFilename("foo.jpg");

        Attachment.Resource res = new Attachment.Resource(setup.getSession());
        String new_id = res.create(att);

        Attachment rcvAtt = res.get(new_id);

        assertEquals("crud", rcvAtt.getName());
        assertEquals("foo.jpg", rcvAtt.getFilename());
        assertEquals("image/jpeg", rcvAtt.getType());
        assertEquals("CREATED", rcvAtt.getState());

        rcvAtt.changeName("durc");

        Attachment changedAtt = res.get(new_id);
        assertEquals("durc", changedAtt.getName());

        rcvAtt.delete();

        // Throws com.syniverse.scgapi.Error.ServerFailure
        res.get(new_id);
    }
}
