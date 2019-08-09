package com.cars.app.model.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.cars.app.model.CarType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO representation of a {@code Car}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public class CarDTO {

    @JsonProperty
    private String id;

    @JsonProperty
    @NotNull
    private CarType type;

    @JsonProperty
    private Set<String> optionals;

    @JsonProperty
    private Integer price;

    @JsonProperty
    private Date quotedAt;

    public CarDTO() {

    }

    public CarDTO(CarType type) {
	this.type = type;
	this.optionals = new HashSet<>();
    }

    public CarDTO(CarType type, Set<String> optionals) {
	this.type = type;
	this.optionals = optionals;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setType(CarType type) {
	this.type = type;
    }

    public void setOptionals(Set<String> optionals) {
	this.optionals = optionals;
    }

    public CarType getType() {
	return this.type;
    }

    public Set<String> getOptionals() {
	return this.optionals;
    }

    public void addOptional(String optional) {
	this.optionals.add(optional);
    }

    public void removeOptional(String carOptional) {
	if (this.optionals == null) {
	    return;
	}
	this.optionals.removeIf(e -> e.equals(carOptional));
    }

    public Integer getPrice() {
	return this.price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public void setQuotedAt(Date quotedAt) {
	this.quotedAt = quotedAt;
    }

    public void addPrice(Integer price) {
	this.price = price;
	this.quotedAt = new Date();
    }

    public Date getQuotedAt() {
	return this.quotedAt;
    }

}
