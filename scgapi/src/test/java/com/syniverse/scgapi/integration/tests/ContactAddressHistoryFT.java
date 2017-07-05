/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.tests;

import com.syniverse.scgapi.ContactAddressHistory;
import com.syniverse.scgapi.ScgException;
import com.syniverse.scgapi.integration.helper.IntegrationTest;
import com.syniverse.scgapi.integration.helper.TestSetup;
import org.apache.logging.log4j.LogManager;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author jaase
 */
@Category(IntegrationTest.class)
public class ContactAddressHistoryFT {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager
            .getLogger(ContactAddressHistoryFT.class);

    private final TestSetup setup;

    public ContactAddressHistoryFT() throws Exception {
        setup = new TestSetup();
        Assume.assumeTrue(setup.isTestable());
    }

    @Test
    public void testsList() throws ScgException {
        ContactAddressHistory.Resource res = new ContactAddressHistory.Resource(setup.getSession());

        for(ContactAddressHistory cah : res.list(null)) {
            LOGGER.debug("cah: {} status: {} keyword: {}", cah.getId(),
                    cah.getStatus(),
                    cah.getKeyword());
        }
    }
}
