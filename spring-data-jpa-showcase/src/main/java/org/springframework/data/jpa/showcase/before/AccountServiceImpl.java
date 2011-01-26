package org.springframework.data.jpa.showcase.before;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.showcase.core.Account;
import org.springframework.data.jpa.showcase.core.Customer;
import org.springframework.stereotype.Repository;


/**
 * Plain JPA implementation of {@link AccountService}.
 * 
 * @author Oliver Gierke
 */
@Repository
class AccountServiceImpl implements AccountService {

    @PersistenceContext
    private EntityManager em;


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.geecon.hades.before.AccountRepository#save(org.geecon.hades.before
     * .Account)
     */
    @Override
    public Account save(Account account) {

        if (account.getId() == null) {
            em.persist(account);
            return account;
        } else {
            return em.merge(account);
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.geecon.hades.before.AccountRepository#findByCustomer(org.geecon.hades
     * .before.Customer)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Account> findByCustomer(Customer customer) {

        Query query = em.createQuery("from Account a where a.customer = ?");
        query.setParameter(1, customer);

        return query.getResultList();
    }
}
