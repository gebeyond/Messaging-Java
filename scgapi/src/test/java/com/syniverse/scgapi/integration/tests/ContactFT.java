/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syniverse.scgapi.integration.tests;

import com.syniverse.scgapi.Contact;
import com.syniverse.scgapi.Contact.Address;
import com.syniverse.scgapi.ScgException;
import com.syniverse.scgapi.integration.helper.IntegrationTest;
import com.syniverse.scgapi.integration.helper.TestSetup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author jaase
 */
@Category(IntegrationTest.class)
public class ContactFT {

    private final TestSetup setup;

    public ContactFT() throws Exception {
        setup = new TestSetup();
        Assume.assumeTrue(setup.isTestable());
    }

    @Before
    public void setUp() {
        try {
            Map<String, String> filter = new HashMap<>();
            filter.put("primary_mdn", setup.getTestConfig().mdnRangeStart.toString());

            Contact.Resource res = new  Contact.Resource(setup.getSession());
            for (Contact c : res.list(filter)) {
                c.delete();
            }
        } catch (ScgException ex) {
            ; // Ignore
        }
    }

    @Test(expected = com.syniverse.scgapi.Error.ServerFailure.class)
    public void testsCRUD() throws Exception {
        String uuid = UUID.randomUUID().toString();

        Long mdn = setup.getTestConfig().mdnRangeStart;

        Contact cn = new Contact();
        cn.setFirstName("test");
        cn.setLastName("User");
        cn.setPrimaryMdn(mdn.toString());
        cn.setExternalId(uuid);

        List<Address> addresses = new ArrayList<Address>();
        Contact.Address a = new Contact.Address();
        a.setCity("Atlanta");
        a.setCountry(setup.getTestConfig().senderIdCountry);
        a.setDesignation("home");
        a.setSource("testing");
        a.setStatus("VALID_NEW");
        addresses.add(a);
        cn.setAddressList(addresses);

        Contact.Resource res = new Contact.Resource(setup.getSession());
        String new_id = res.create(cn);
        System.out.println("Created contact " + new_id);

        Contact c = res.get(new_id);
        System.out.println("Got contact " + c.getId());

        assertEquals(uuid, c.getExternalId());
        assertEquals("test", c.getFirstName());
        assertEquals(new_id, c.getId());

        c.setFirstName("John");
        c.update();

        Contact cu = res.get(new_id);
        assertEquals("John", cu.getFirstName());

        Contact.ItemItetaror contacts = res.list(null);
        assertFalse(contacts.getAllAsList().size() < 1);

        HashMap<String, String> options = new HashMap<>();
        options.put("external_id", uuid);

        contacts = res.list(options);
        assertTrue(contacts.getAllAsList().size() == 1);

        cu.delete();

        // Throws com.syniverse.scgapi.Error.ServerFailure
        res.get(new_id);
    }
}
