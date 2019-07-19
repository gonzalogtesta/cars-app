package com.sysone.app.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.sysone.app.model.Car;
import com.sysone.app.model.CarType;
import com.sysone.app.model.ItemPrice;
import com.sysone.app.model.builder.CarBuilder;
import com.sysone.app.model.dto.CarDTO;
import com.sysone.app.model.dto.PriceDTO;
import com.sysone.app.repository.PricesRepository;
import com.sysone.app.service.QuotingService;

/**
 * Tests for {@code QuotingService}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotingServiceTest {

    @TestConfiguration
    static class QuotingServiceImplTestContextConfiguration {

	@Bean
	public QuotingService quotingService() {
	    return new QuotingServiceImpl();
	}

	@Bean
	public ModelMapper mapper() {
	    return new ModelMapper();
	}
    }

    @MockBean
    private PricesRepository repository;

    @Autowired
    private QuotingService quotingService;

    @Autowired
    private ModelMapper mapper;

    @Test
    public void quoteBaseCar() {

	Car car = (new CarBuilder()).type(CarType.SEDAN).getCar();

	Mockito.when(repository.findByCode(CarType.SEDAN.name())).thenReturn(Optional.of(new ItemPrice("SEDAN", 200)));
	Mockito.when(repository.findAllByCodeIn(Mockito.anySet())).thenReturn(new ArrayList<>());

	assertEquals(new Integer(200), quotingService.quote(mapper.map(car, CarDTO.class)));
    }

    @Test
    public void quoteBaseCarWithOptionals() {

	Car car = (new CarBuilder()).type(CarType.SEDAN).withOptional("AA").getCar();

	Mockito.when(repository.findByCode(CarType.SEDAN.name())).thenReturn(Optional.of(new ItemPrice("SEDAN", 200)));
	Mockito.when(repository.findAllByCodeIn(Mockito.anySet())).thenReturn(Arrays.asList(new ItemPrice("AA", 100)));

	assertEquals(new Integer(300), quotingService.quote(mapper.map(car, CarDTO.class)));
    }

    @Test
    public void createPrice() {

	PriceDTO price = new PriceDTO();

	quotingService.update(price);

	Mockito.verify(repository, Mockito.atMost(1)).save(Mockito.any(ItemPrice.class));
    }

    @Test
    public void deletePrice() {

	String id = UUID.randomUUID().toString();

	quotingService.delete(id);

	Mockito.verify(repository, Mockito.atMost(1)).deleteById(id);
    }

    @Test
    public void updatePrice() {

	PriceDTO price = new PriceDTO();

	quotingService.update(price);

	Mockito.verify(repository, Mockito.atMost(1)).save(Mockito.any(ItemPrice.class));
    }

    @Test
    public void deleteCar() {

	String id = UUID.randomUUID().toString();

	quotingService.delete(id);

	Mockito.verify(repository, Mockito.atMost(1)).deleteById(id);
    }
}
