package org.springframework.data.jpa.example.repository;

import java.util.List;

import org.springframework.data.jpa.example.domain.User;


/**
 * Interface for repository functionality that ought to be implemented manually.
 * 
 * @author Oliver Gierke
 */
interface UserRepositoryCustom {

    /**
     * Custom repository operation.
     * 
     * @return
     */
    List<User> myCustomBatchOperation();
}
