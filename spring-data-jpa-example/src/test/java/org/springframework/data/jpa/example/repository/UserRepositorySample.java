package org.springframework.data.jpa.example.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.example.domain.User;
import org.springframework.data.jpa.example.repository.UserRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Intergration test showing the basic usage of {@link UserRepository}.
 * 
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class UserRepositorySample {

    @Autowired
    UserRepository repository;


    /**
     * Tests inserting a user and asserts it can be loaded again.
     */
    @Test
    public void testInsert() {

        User user = new User();
        user.setUsername("username");

        user = repository.save(user);

        assertEquals(user, repository.findById(user.getId()));
    }


    @Test
    public void foo() {

        User user = new User();
        user.setUsername("foobar");
        user.setLastname("lastname");

        user = repository.save(user);

        List<User> users = repository.findByLastname("lastname");

        assertNotNull(users);
        assertTrue(users.contains(user));

        User reference = repository.findByTheUsersName("foobar");
        assertEquals(user, reference);
    }


    /**
     * Test invocation of custom method.
     */
    @Test
    public void testCustomMethod() {

        User user = new User();
        user.setUsername("username");

        user = repository.save(user);

        List<User> users = repository.myCustomBatchOperation();

        assertNotNull(users);
        assertTrue(users.contains(user));
    }
}
