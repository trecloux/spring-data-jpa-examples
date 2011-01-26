package org.springframework.data.jpa.showcase.after;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.showcase.core.Customer;


/**
 * Repository to manage {@link Customer} instances.
 * 
 * @author Oliver Gierke
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Returns all {@link Customer}s with the given lastname.
     * 
     * @param lastname
     * @return
     */
    List<Customer> findByLastname(String lastname);


    /**
     * Returns a page of {@link Customer}s with the given lastname.
     * 
     * @param lastname
     * @param pageable
     * @return
     */
    Page<Customer> findByLastname(String lastname, Pageable pageable);
}
