package com.cars.app.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.cars.app.controllers.CarController;
import com.cars.app.model.CarType;
import com.cars.app.model.builder.CarBuilder;
import com.cars.app.model.dto.CarDTO;
import com.cars.app.service.CarService;
import com.cars.app.service.QuotingService;

/**
 * Test for {@code CarController}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarControllerTest {

    @TestConfiguration
    static class CarControllerTestContextConfiguration {

	@Bean
	public ModelMapper mapper() {
	    return new ModelMapper();
	}
    }

    @MockBean
    private QuotingService quotingService;

    @MockBean
    private CarService carService;

    @Autowired
    private CarController controller;

    @Autowired
    private ModelMapper mapper;

    @Test
    public void contexLoads() throws Exception {
	assertThat(controller).isNotNull();
    }

    @Test(expected = ResponseStatusException.class)
    public void testFailOnCreateCar() {
	CarDTO inputCar = createCar();

	Mockito.when(this.carService.create(inputCar)).thenReturn(null);

	CarDTO returnedCar = controller.postCar(inputCar);
	assertNull(returnedCar);
    }

    @Test(expected = ResponseStatusException.class)
    public void testCreateNotAbleToQuoteCar() {
	CarDTO inputCar = createCar();

	String id = UUID.randomUUID().toString();
	Mockito.when(this.carService.create(inputCar)).thenReturn(id);
	Mockito.when(this.carService.get(id)).thenReturn(inputCar);
	Mockito.when(this.quotingService.quote(inputCar)).thenReturn(null);

	CarDTO returnedCar = controller.postCar(inputCar);
	assertNotNull(returnedCar);
    }

    @Test
    public void testCreateCar() {
	CarDTO inputCar = createCar();

	String id = UUID.randomUUID().toString();
	Mockito.when(this.carService.create(inputCar)).thenReturn(id);
	Mockito.when(this.carService.get(id)).thenReturn(inputCar);
	Mockito.when(this.quotingService.quote(inputCar)).thenReturn(new Integer(1000));

	CarDTO returnedCar = controller.postCar(inputCar);
	assertNotNull(returnedCar);
	assertNotNull(returnedCar.getPrice());
	assertEquals(new Integer(1000), returnedCar.getPrice());
    }

    @Test
    public void testUpdateCar() {
	CarDTO inputCar = createCar();

	String id = UUID.randomUUID().toString();
	Mockito.when(this.carService.get(id)).thenReturn(inputCar);
	Mockito.when(this.quotingService.quote(inputCar)).thenReturn(new Integer(1000));

	CarDTO returnedCar = controller.patchCar(id, inputCar);
	assertNotNull(returnedCar);
	assertNotNull(returnedCar.getPrice());
	assertEquals(new Integer(1000), returnedCar.getPrice());
	Mockito.verify(this.carService, Mockito.atMost(1)).update(inputCar);
    }

    @Test
    public void testDeleteCar() {
	String id = UUID.randomUUID().toString();

	controller.deleteCar(id);
	Mockito.verify(this.carService, Mockito.atMost(1)).delete(id);
    }

    private CarDTO createCar() {
	CarBuilder builder = new CarBuilder();
	return mapper.map(builder.type(CarType.COUPE).withOptional("AA").getCar(), CarDTO.class);
    }
}