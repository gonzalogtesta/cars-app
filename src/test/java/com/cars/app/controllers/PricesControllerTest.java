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

import com.cars.app.controllers.PricesController;
import com.cars.app.model.dto.PriceDTO;
import com.cars.app.service.QuotingService;

/**
 * Tests for {@code PricesController}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PricesControllerTest {

    @TestConfiguration
    static class CarControllerTestContextConfiguration {

	@Bean
	public ModelMapper mapper() {
	    return new ModelMapper();
	}
    }

    @MockBean
    private QuotingService quotingService;

    @Autowired
    private PricesController controller;

    @Test
    public void contexLoads() throws Exception {
	assertThat(controller).isNotNull();
    }

    @Test(expected = ResponseStatusException.class)
    public void testFailOnCreateCar() {
	PriceDTO inputPrice = createPrice();

	Mockito.when(this.quotingService.create(inputPrice)).thenReturn(null);

	PriceDTO returnedCar = controller.postPrice(inputPrice);
	assertNull(returnedCar);
    }

    @Test(expected = ResponseStatusException.class)
    public void testCreateNotAbleToQuoteCar() {
	PriceDTO inputPrice = createPrice();

	String id = UUID.randomUUID().toString();
	Mockito.when(this.quotingService.create(inputPrice)).thenReturn(id);
	Mockito.when(this.quotingService.get(id)).thenReturn(null);

	PriceDTO returnedPrice = controller.postPrice(inputPrice);
	assertNull(returnedPrice);
    }

    @Test
    public void testCreateCar() {
	PriceDTO inputPrice = createPrice();

	String id = UUID.randomUUID().toString();
	Mockito.when(this.quotingService.create(inputPrice)).thenReturn(id);
	Mockito.when(this.quotingService.get(id)).thenReturn(inputPrice);

	PriceDTO returnedCar = controller.postPrice(inputPrice);
	assertNotNull(returnedCar);
	assertNotNull(returnedCar.getPrice());
	assertEquals(new Integer(1234), returnedCar.getPrice());
    }

    @Test
    public void testUpdateCar() {
	PriceDTO inputPrice = createPrice();

	String id = UUID.randomUUID().toString();

	PriceDTO returnedPrice = controller.patchPrice(id, inputPrice);
	assertNotNull(returnedPrice);
	assertNotNull(returnedPrice.getPrice());
	assertEquals(new Integer(1234), returnedPrice.getPrice());
	Mockito.verify(this.quotingService, Mockito.atMost(1)).update(inputPrice);
    }

    @Test
    public void testDeletePrice() {
	String id = UUID.randomUUID().toString();

	controller.deletePrice(id);
	Mockito.verify(this.quotingService, Mockito.atMost(1)).delete(id);
    }

    private PriceDTO createPrice() {
	PriceDTO price = new PriceDTO();
	price.setCode("AA");
	price.setPrice(1234);
	return price;
    }
}