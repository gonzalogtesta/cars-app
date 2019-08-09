package com.cars.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cars.app.model.ItemPrice;

/**
 * {@code ItemPrice} repository based on {@code MongoRepository}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public interface PricesRepository extends MongoRepository<ItemPrice, String> {

    /**
     * Finds an {@code ItemPrice} based on a code
     * 
     * @param code
     * 
     *             string representation of the item code
     * @return returns an optional of the {@code ItemPrice}
     */
    Optional<ItemPrice> findByCode(String code);

    /**
     * Finds all the {@code ItemPrice}s using code
     * 
     * @param codes
     * 
     *              an {@code Iterable} of strings representing the codes
     * @return returns an {@code Iterable} of {@code ItemPrice}
     */
    Iterable<ItemPrice> findAllByCodeIn(Iterable<String> codes);

}
