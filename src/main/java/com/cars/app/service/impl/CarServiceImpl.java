package com.cars.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.app.model.Car;
import com.cars.app.model.dto.CarDTO;
import com.cars.app.repository.CarRepository;
import com.cars.app.service.CarService;

/**
 * A {@code CarService} implementation using a {@code CarRepository} and a
 * {@code ModelMapper}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CarDTO get(String id) {

	Optional<Car> item = repository.findById(id);

	if (item.isPresent()) {
	    return this.mapper.map(item.get(), CarDTO.class);
	}

	return null;
    }

    @Override
    public String create(CarDTO car) {
	Car item = repository.save(this.mapper.map(car, Car.class));

	if (item != null) {
	    return item.getId();
	}

	return null;

    }

    @Override
    public void update(CarDTO car) {
	repository.save(this.mapper.map(car, Car.class));
    }

    @Override
    public void delete(String id) {
	repository.deleteById(id);
    }

    @Override
    public List<CarDTO> getAll() {
	return repository.findAll().stream().map(x -> mapper.map(x, CarDTO.class)).collect(Collectors.toList());
    }

}
