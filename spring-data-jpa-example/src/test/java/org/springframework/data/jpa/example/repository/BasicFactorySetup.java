package org.springframework.data.jpa.example.repository;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.example.domain.User;
import org.springframework.data.jpa.example.repository.simple.SimpleUserRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;


/**
 * Test case showing how to use the basic {@link GenericDaoFactory}
 * 
 * @author Oliver Gierke
 */
public class BasicFactorySetup {

    private static final EntityManagerFactory factory =
        Persistence.createEntityManagerFactory("jpa.sample.plain");

    private SimpleUserRepository userRepository;
    private EntityManager em;

    private User user;


    /**
     * Creates a {@link SimpleUserRepository} instance.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() {

        em = factory.createEntityManager();

        userRepository = new JpaRepositoryFactory(em).getRepository(SimpleUserRepository.class);

        em.getTransaction().begin();

        user = new User();
        user.setUsername("username");
        user.setFirstname("firstname");
        user.setLastname("lastname");

        user = userRepository.save(user);

    }


    /**
     * Rollback transaction.
     */
    @After
    public void tearDown() {

        em.getTransaction().rollback();
    }


    /**
     * Showing invocation of finder method.
     */
    @Test
    public void executingFinders() {

        assertEquals(user, userRepository.findByTheUsersName("username"));
        assertEquals(user, userRepository.findByLastname("lastname").get(0));
        assertEquals(user, userRepository.findByFirstname("firstname").get(0));
    }
}
