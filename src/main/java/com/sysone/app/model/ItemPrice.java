package com.sysone.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Item entity that contains a code which represents an item, including a price
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@Document(collection = "prices")
public class ItemPrice {

    @Id
    private String id;
    private String code;
    private Integer price;

    public ItemPrice() {

    }

    public ItemPrice(String code, Integer price) {
	this.code = code;
	this.price = price;
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
