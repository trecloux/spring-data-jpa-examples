package org.springframework.data.jpa.example.repository;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.example.domain.User;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.Repository;


/**
 * This unit tests shows plain usage of {@link SimpleJpaRepository}.
 * 
 * @author Oliver Gierke
 */
public class BasicSample {

    private Repository<User, Long> userRepository;
    private EntityManager em;


    /**
     * Sets up a {@link SimpleJpaRepository} instance.
     */
    @Before
    public void setUp() {

        EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("jpa.sample.plain");
        em = factory.createEntityManager();

        userRepository = SimpleJpaRepository.create(em, User.class);

        em.getTransaction().begin();
    }


    @After
    public void tearDown() {

        em.getTransaction().rollback();
    }


    /**
     * Tests saving users. Don't mimic transactionality shown here. It seriously
     * lacks resource cleanup in case of an exception. Simplification serves
     * descriptivness.
     */
    @Test
    public void savingUsers() {

        User user = new User();
        user.setUsername("username");

        user = userRepository.save(user);

        assertEquals(user, userRepository.findById(user.getId()));
    }
}
