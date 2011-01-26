package org.springframework.data.jpa.example.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.example.domain.User;
import org.springframework.data.jpa.example.repository.simple.SimpleUserRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Intergration test showing the basic usage of {@link SimpleUserRepository}.
 * 
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:simple-repository-context.xml")
@Transactional
public class SimpleUserRepositorySample {

    @Autowired
    SimpleUserRepository repository;
    User user;


    @Before
    public void setUp() {

        user = new User();
        user.setUsername("foobar");
        user.setFirstname("firstname");
        user.setLastname("lastname");
    }


    /**
     * Tests inserting a user and asserts it can be loaded again.
     */
    @Test
    public void testInsert() {

        user = repository.save(user);

        assertEquals(user, repository.findById(user.getId()));
    }


    @Test
    public void foo() throws Exception {

        user = repository.save(user);

        List<User> users = repository.findByLastname("lastname");

        assertNotNull(users);
        assertTrue(users.contains(user));
    }


    @Test
    public void testname() throws Exception {

        user = repository.save(user);

        List<User> users = repository.findByFirstnameOrLastname("lastname");

        assertTrue(users.contains(user));
    }
}
