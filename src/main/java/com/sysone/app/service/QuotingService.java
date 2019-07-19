package com.sysone.app.service;

import java.util.List;

import com.sysone.app.model.dto.CarDTO;
import com.sysone.app.model.dto.PriceDTO;

/**
 * Quoting service interface
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public interface QuotingService {

    /**
     * Quotes a car
     * 
     * @param car
     * 
     *            dto car
     * @return returns the price of the car
     */
    Integer quote(CarDTO car);

    /**
     * Creates a price using the {@code PriceDTO} information
     * 
     * @param price a dto price instance
     * @return returns the id of the item price generated
     */
    String create(PriceDTO price);

    /**
     * Retrieves the {@code PriceDTO} from an id
     * 
     * @param id id of the item price
     * @return returns a {@code PriceDTO} instance if the id exists, otherwise
     *         returns null
     */
    PriceDTO get(String id);

    /**
     * Retrieves all the item prices
     * 
     * @return returns a {@code List} of {@code PriceDTO}s
     */
    List<PriceDTO> getAll();

    /**
     * Updates a price item
     * 
     * @param price
     * 
     *              a dto price to update
     */
    void update(PriceDTO price);

    /**
     * Deletes an item price based on the id
     * 
     * @param id
     * 
     *           id of the item to delete
     */
    void delete(String id);

}
