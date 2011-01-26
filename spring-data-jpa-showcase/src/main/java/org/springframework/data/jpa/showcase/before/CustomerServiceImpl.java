package org.springframework.data.jpa.showcase.before;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.showcase.core.Customer;
import org.springframework.stereotype.Repository;


/**
 * Plain JPA implementation of {@link CustomerService}.
 * 
 * @author Oliver Gierke
 */
@Repository
public class CustomerServiceImpl implements CustomerService {

    @PersistenceContext
    private EntityManager em;


    /*
     * (non-Javadoc)
     * 
     * @see org.geecon.hades.before.CustomerRepository#findById(java.lang.Long)
     */
    @Override
    public Customer findById(Long id) {

        return em.find(Customer.class, id);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.geecon.hades.before.CustomerRepository#findAll()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> findAll() {

        return em.createQuery("from Customer c").getResultList();
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.geecon.hades.before.CustomerRepository#findAll(int, int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> findAll(int page, int pageSize) {

        Query query = em.createQuery("from Customer c");

        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.geecon.hades.before.CustomerRepository#save(org.geecon.hades.before
     * .Customer)
     */
    @Override
    public Customer save(Customer customer) {

        // Is new?
        if (customer.getId() == null) {
            em.persist(customer);
            return customer;
        } else {
            return em.merge(customer);
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.geecon.hades.before.CustomerRepository#findByLastname(java.lang.String
     * , int, int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> findByLastname(String lastname, int page, int pageSize) {

        Query query = em.createQuery("from Customer c where c.lastname = ?");

        query.setParameter(1, lastname);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }
}
