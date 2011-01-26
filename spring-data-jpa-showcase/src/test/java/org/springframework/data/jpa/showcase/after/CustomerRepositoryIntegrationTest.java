package org.springframework.data.jpa.showcase.after;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.showcase.core.AbstractShowcaseTest;
import org.springframework.data.jpa.showcase.core.Customer;
import org.springframework.test.context.ContextConfiguration;


/**
 * @author Oliver Gierke
 */
@ContextConfiguration("classpath:application-context-after.xml")
public class CustomerRepositoryIntegrationTest extends AbstractShowcaseTest {

    @Autowired
    CustomerRepository repository;


    @Test
    public void findsAllCustomers() throws Exception {

        List<Customer> result = repository.findAll();

        assertThat(result, is(notNullValue()));
        assertFalse(result.isEmpty());
    }


    @Test
    public void findsFirstPageOfMatthews() throws Exception {

        Page<Customer> customers =
                repository.findByLastname("Matthews", new PageRequest(0, 2));

        assertThat(customers.getContent().size(), is(2));
        assertFalse(customers.hasPreviousPage());
    }


    @Test
    public void findsCustomerById() throws Exception {

        Customer customer = repository.findById(2L);

        assertThat(customer.getFirstname(), is("Carter"));
        assertThat(customer.getLastname(), is("Beauford"));
    }
}
