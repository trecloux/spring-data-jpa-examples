package org.springframework.data.jpa.example.auditing;

import javax.persistence.Entity;

import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractAuditable;


/**
 * User domain class that uses auditing functionality of Spring Data that can either
 * be aquired implementing {@link Auditable} or extend
 * {@link AbstractAuditable}.
 * 
 * @author Oliver Gierke
 */
@Entity
public class AuditableUser extends AbstractAuditable<AuditableUser, Long> {

    private static final long serialVersionUID = 1L;

    private String username;


    /**
     * Set's the user's name.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {

        this.username = username;
    }


    /**
     * Returns the user's name.
     * 
     * @return the username
     */
    public String getUsername() {

        return username;
    }
}
