/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.tests;

import com.syniverse.scgapi.ScgException;
import com.syniverse.scgapi.SenderId;
import com.syniverse.scgapi.SenderIdClass;
import com.syniverse.scgapi.SenderIdType;
import com.syniverse.scgapi.integration.helper.IntegrationTest;
import com.syniverse.scgapi.integration.helper.TestSetup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Assume;
import static org.junit.Assume.assumeFalse;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author jaase
 */
@Category(IntegrationTest.class)
public class SenderIdFT {

    private final TestSetup setup;

    public SenderIdFT() throws Exception {
        setup = new TestSetup();
        Assume.assumeTrue(setup.isTestable());
    }

    @Test
    public void testSenderIdClass() throws ScgException {
        SenderIdClass.Resource res = new SenderIdClass.Resource(setup.getSession());

        assumeFalse(res.list(null).getAllAsList().isEmpty());
    }

    @Test
    public void testSenderIdType() throws ScgException {
        SenderIdType.Resource res = new SenderIdType.Resource(setup.getSession());

        assumeFalse(res.list(null).getAllAsList().isEmpty());
    }

    @Test
    public void testSenderId() throws ScgException {
        SenderId.Resource res = new SenderId.Resource(setup.getSession());

        List<SenderId> sender_ids = res.list(null).getAllAsList();
        assumeFalse(sender_ids.isEmpty());

        SenderId sid = res.get(sender_ids.get(0).getId());

        assertEquals(sender_ids.get(0).getId(), sid.getId());
    }

    @Test
    public void listSenderids() throws ScgException {
        Map<String, String> filter = new HashMap<>();
        filter.put("class_id", "COMMERCIAL");
        filter.put("state", "ACTIVE");

        SenderId.Resource res = new  SenderId.Resource(setup.getSession());
        for (SenderId sid : res.list(filter)) {
            System.out.println("Sender id " + sid.getId()
                + " has capabilities " +  sid.getCapabilities().toString());
        }
    }
}
