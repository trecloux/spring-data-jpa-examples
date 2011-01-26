package org.springframework.data.jpa.showcase.before;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.showcase.core.AbstractShowcaseTest;
import org.springframework.data.jpa.showcase.core.Account;
import org.springframework.data.jpa.showcase.core.Customer;
import org.springframework.test.context.ContextConfiguration;


/**
 * @author Oliver Gierke
 */
@ContextConfiguration("classpath:application-context-before.xml")
public class AccountServiceIntegrationTest extends AbstractShowcaseTest {

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;


    @Test
    public void testname() throws Exception {

        Customer customer = customerService.findById(1L);

        List<Account> accounts = accountService.findByCustomer(customer);

        assertFalse(accounts.isEmpty());
        assertThat(accounts.get(0).getCustomer(), is(customer));
    }
}
