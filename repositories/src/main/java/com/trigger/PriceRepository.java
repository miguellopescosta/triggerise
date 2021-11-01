package com.trigger;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* INTERFACE RESPONSIBLE FOR DB QUERIES AND TRANSACTIONS IN A GIVEN SQL DB, FOR A CERTAIN TABLE. IN THIS CASE, SHOULD BE THE PRICE TABLE */
@Repository
public interface PriceRepository<Price> extends JpaRepository<Price, Long> {

}
