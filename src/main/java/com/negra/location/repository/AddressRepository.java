package com.negra.location.repository;

import com.negra.location.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT A.town FROM Address A GROUP BY A.town")
    List<String> findAvailableTowns();

}