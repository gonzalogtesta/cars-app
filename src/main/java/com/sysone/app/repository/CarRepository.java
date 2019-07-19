package com.sysone.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sysone.app.model.Car;

/**
 * {@code Car} repository using {@code MongoRepository}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public interface CarRepository extends MongoRepository<Car, String> {

}
