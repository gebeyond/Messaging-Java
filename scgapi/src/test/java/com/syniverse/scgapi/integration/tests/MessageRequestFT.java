/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.tests;

import com.syniverse.scgapi.MessageRequest;
import com.syniverse.scgapi.ScgException;
import com.syniverse.scgapi.SenderId;
import com.syniverse.scgapi.integration.helper.IntegrationTest;
import com.syniverse.scgapi.integration.helper.TestSetup;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author jaase
 */
@Category(IntegrationTest.class)
public class MessageRequestFT {

    private final TestSetup setup;

    public MessageRequestFT() throws Exception {
        setup = new TestSetup();
        Assume.assumeTrue(setup.isTestable());
    }

    private String findSenderId() throws ScgException {
        SenderId.Resource res = new SenderId.Resource(setup.getSession());

        Map<String, String> filter = new HashMap<>();
        filter.put("class_id", "COMMERCIAL");
        filter.put("state", "ACTIVE");

        for (SenderId sid : res.list(filter)) {
            if ((sid.getClassId() != null && sid.getClassId().matches("COMMERCIAL"))
                && (sid.getState() != null && sid.getState().matches("ACTIVE"))
                && (sid.getOwnership()!= null && sid.getOwnership().matches("PRIVATE"))
                && (sid.getCountry() != null && sid.getCountry().equals(setup.getTestConfig().senderIdCountry))
                && (sid.getMessageTemplates() == null || sid.getMessageTemplates().isEmpty())
                && (sid.getCapabilities() != null && sid.getCapabilities().contains("SMS"))) {
                return sid.getId();
            }
        }
        throw new RuntimeException("No suitable SenderId was found");
    }

    @Test
    public void testsSendMessage() throws Exception {
        MessageRequest.Resource res = new MessageRequest.Resource(setup.getSession());

        final String senderId = findSenderId();

        MessageRequest mrq = new MessageRequest();
        mrq.setFrom("sender_id:" + senderId);
        mrq.setTo(setup.getTestConfig().mdnRangeStart.toString());
        mrq.setBody("Hello World");
        mrq.setTestMessageFlag(true);

        String newId = res.create(mrq);
        mrq = res.get(newId);

        assertEquals(newId, mrq.getId());

        try {
            mrq.delete();
        } catch(Exception ex) {
            // Ignore
        }
    }
}
