package com.cars.app.model.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO representation of {@code ItemPrice}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public class PriceDTO {

    @JsonProperty
    private String id;

    @JsonProperty
    @NotNull
    private String code;

    @JsonProperty
    private Integer price;

    public PriceDTO() {

    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public Integer getPrice() {
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

}
