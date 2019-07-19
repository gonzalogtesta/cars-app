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

import com.sysone.app.model.dto.CarDTO;
import com.sysone.app.service.CarService;
import com.sysone.app.service.QuotingService;

/**
 * Car controller class, using {@code CarService} and {@code QuotingService}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@RestController
public class CarController {

    @Autowired
    private CarService service;

    @Autowired
    private QuotingService quotingService;

    @RequestMapping(path = "/cars", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO postCar(@RequestBody CarDTO car) {

	String id = service.create(car);

	if (id == null) {
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create car");
	}

	CarDTO savedCar = this.service.get(id);

	Integer price = quotingService.quote(savedCar);

	if (price == null) {
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to quote car");
	}

	savedCar.addPrice(price);

	service.update(savedCar);

	return savedCar;
    }

    @RequestMapping(path = "/cars/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CarDTO getCar(@PathVariable @NotNull String id) {

	CarDTO car = service.get(id);

	if (car == null) {
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to quote car");
	}

	return car;
    }

    @RequestMapping(path = "/cars/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public CarDTO patchCar(@PathVariable @NotNull String id, @RequestBody CarDTO car) {

	Integer price = quotingService.quote(car);

	if (price == null) {
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to quote car");
	}

	car.addPrice(price);

	service.update(car);

	return car;
    }

    @RequestMapping(path = "/cars/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable @NotNull String id) {
	service.delete(id);
    }

    @RequestMapping(path = "/cars", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CarDTO> getCars() {

	return service.getAll();

    }

}
