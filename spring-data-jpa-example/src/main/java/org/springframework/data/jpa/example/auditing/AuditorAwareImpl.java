package org.springframework.data.jpa.example.auditing;

import org.springframework.data.domain.AuditorAware;


/**
 * Dummy implementation of {@link AuditorAware}. It will return the configured
 * {@link AuditableUser} as auditor on every call to
 * {@link #getCurrentAuditor()}. Normally you would access the applications
 * security subsystem to return the current user.
 * 
 * @author Oliver Gierke
 */
public class AuditorAwareImpl implements AuditorAware<AuditableUser> {

    private AuditableUser auditor;


    /**
     * @param auditor the auditor to set
     */
    public void setAuditor(AuditableUser auditor) {

        this.auditor = auditor;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
     */
    public AuditableUser getCurrentAuditor() {

        return auditor;
    }

}
