package com.sysone.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysone.app.model.Car;
import com.sysone.app.model.ItemPrice;
import com.sysone.app.model.dto.CarDTO;
import com.sysone.app.model.dto.PriceDTO;
import com.sysone.app.repository.PricesRepository;
import com.sysone.app.service.QuotingService;

/**
 * A {@code QuotingService} implementation using a {@code PricesRepository} and
 * a {@code ModelMapper}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@Service
public class QuotingServiceImpl implements QuotingService {

    @Autowired
    private PricesRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Integer quote(CarDTO car) {

	Car carMapped = this.mapper.map(car, Car.class);

	Integer total = 0;

	Optional<ItemPrice> carPrice = this.repository.findByCode(carMapped.getType().name());

	if (carPrice.isPresent()) {
	    total += carPrice.get().getPrice();
	}

	return total + this.getOptionalsPrice(carMapped.getOptionals());
    }

    private Integer getOptionalsPrice(Set<String> optionals) {

	Iterable<ItemPrice> prices = this.repository.findAllByCodeIn(optionals);

	return StreamSupport.stream(prices.spliterator(), false).map(x -> x.getPrice()).reduce(0, Integer::sum);
    }

    @Override
    public String create(PriceDTO price) {
	ItemPrice itemPrice = this.mapper.map(price, ItemPrice.class);
	ItemPrice saved = this.repository.save(itemPrice);
	return saved.getId();
    }

    @Override
    public PriceDTO get(String id) {

	Optional<ItemPrice> item = this.repository.findById(id);

	if (item.isPresent()) {
	    return this.mapper.map(item.get(), PriceDTO.class);
	}

	return null;
    }

    @Override
    public List<PriceDTO> getAll() {

	List<ItemPrice> items = this.repository.findAll();

	return items.stream().map(x -> mapper.map(x, PriceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void update(PriceDTO price) {
	this.repository.save(this.mapper.map(price, ItemPrice.class));
    }

    @Override
    public void delete(String id) {
	this.repository.deleteById(id);
    }

}
