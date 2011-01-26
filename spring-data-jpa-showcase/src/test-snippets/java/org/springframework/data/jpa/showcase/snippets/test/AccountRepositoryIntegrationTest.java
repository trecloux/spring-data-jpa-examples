package org.springframework.data.jpa.showcase.snippets.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.showcase.snippets.AccountRepository;


/**
 * @author Oliver Gierke
 */
public abstract class AccountRepositoryIntegrationTest {

    private AccountRepository accountDao;


    public void removesExpiredAccountsCorrectly() throws Exception {

        accountDao.removedExpiredAccounts(new LocalDate(2011, 1, 1));
        assertThat(accountDao.count(), is(1L));
    }
}
