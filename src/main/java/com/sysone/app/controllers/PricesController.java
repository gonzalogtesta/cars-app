package com.sysone.app.controllers;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sysone.app.model.dto.PriceDTO;
import com.sysone.app.service.QuotingService;

/**
 * Prices controller, using a {@code QuotingService}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@RestController
public class PricesController {

    @Autowired
    private QuotingService quotingService;

    @RequestMapping(path = "/prices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDTO postPrice(@RequestBody PriceDTO price) {

	String id = quotingService.create(price);

	if (id == null) {
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create car");
	}

	PriceDTO savedPrice = this.quotingService.get(id);

	if (savedPrice == null) {
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to quote car");
	}

	return savedPrice;
    }

    @RequestMapping(path = "/prices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PriceDTO> getPrices() {

	return quotingService.getAll();
    }

    @RequestMapping(path = "/prices/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public PriceDTO patchPrice(@PathVariable @NotNull String id, @RequestBody PriceDTO price) {

	quotingService.update(price);

	return price;
    }

    @RequestMapping(path = "/prices/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePrice(@PathVariable @NotNull String id) {

	quotingService.delete(id);

    }

}
