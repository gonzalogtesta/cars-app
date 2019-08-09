package com.cars.app.service.impl;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Optional;
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

import com.cars.app.model.Car;
import com.cars.app.model.CarType;
import com.cars.app.model.builder.CarBuilder;
import com.cars.app.model.dto.CarDTO;
import com.cars.app.repository.CarRepository;
import com.cars.app.service.CarService;

/**
 * Tests for {@code CarService}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @MockBean
    private CarRepository repository;

    @TestConfiguration
    static class CarServiceImplTestContextConfiguration {

	@Bean
	public ModelMapper mapper() {
	    return new ModelMapper();
	}

    }

    @Autowired
    private CarService carService;

    @Autowired
    private ModelMapper mapper;

    @Test
    public void getCar() {

	Car car = (new CarBuilder()).type(CarType.SEDAN).getCar();

	String id = UUID.randomUUID().toString();

	Mockito.when(repository.findById(id.toString())).thenReturn(Optional.of(car));

	assertEquals(car, mapper.map(carService.get(id), Car.class));
    }

    @Test
    public void saveCarWithOptionals() {

	Car car = (new CarBuilder()).type(CarType.SEDAN).withOptional("AA").getCar();
	Car carSaved = (new CarBuilder()).type(CarType.SEDAN).withOptional("AA").getCar();
	carSaved.setId(UUID.randomUUID().toString());

	Mockito.when(repository.save(Mockito.any(Car.class))).thenReturn(carSaved);

	assertThat(carService.create(mapper.map(car, CarDTO.class)), instanceOf(String.class));
    }

    @Test
    public void saveCarFailure() {

	Car car = (new CarBuilder()).type(CarType.SEDAN).withOptional("ABS").getCar();

	Mockito.when(repository.save(Mockito.any(Car.class))).thenReturn(null);

	assertNull(carService.create(mapper.map(car, CarDTO.class)));
    }

    @Test
    public void updateCar() {

	Car car = (new CarBuilder()).type(CarType.SEDAN).withOptional("ABS").getCar();

	carService.update(mapper.map(car, CarDTO.class));

	Mockito.verify(repository, Mockito.atMost(1)).save(Mockito.any(Car.class));
    }

    @Test
    public void deleteCar() {

	String id = UUID.randomUUID().toString();

	carService.delete(id);

	Mockito.verify(repository, Mockito.atMost(1)).deleteById(id);
    }
}
