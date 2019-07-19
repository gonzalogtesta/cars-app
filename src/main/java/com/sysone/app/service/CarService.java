package com.sysone.app.service;

import java.util.List;

import com.sysone.app.model.dto.CarDTO;

/**
 * Car service interface
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public interface CarService {

    /**
     * Gets {@CarDTO} instance from id
     * 
     * @param id
     * 
     *           string id
     * @return
     */
    CarDTO get(String id);

    /**
     * Creates a Car based on a {@CarDTO}
     * 
     * @param car
     * 
     *            car dto object
     * @return returns an {@code String} for the Car generated, if there was an
     *         issue null is returned
     */
    String create(CarDTO car);

    /**
     * Updates a Car using a {@code CarDTO} instance
     * 
     * @param car
     * 
     *            car dto object
     */
    void update(CarDTO car);

    /**
     * Deletes a
     * 
     * @param id
     * 
     *           string id
     */
    void delete(String id);

    /**
     * Get all car objects
     * 
     * @return returns a {@code List} of {@code CarDTO}
     */
    List<CarDTO> getAll();

}
