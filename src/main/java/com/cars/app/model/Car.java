package com.cars.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Car entity
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
@Document(collection = "cars")
public class Car {

    @Id
    private String id;

    private CarType type;

    private Set<String> optionals;

    private Integer price;

    private Date quotedAt;

    public Car() {
	this.optionals = new HashSet<String>();
    }

    public Car(CarType type) {
	this.type = type;
	this.optionals = new HashSet<String>();
    }

    public Car(CarType type, Set<String> optionals) {
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
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public Date getQuotedAt() {
	return quotedAt;
    }

    public void setQuotedAt(Date quotedAt) {
	this.quotedAt = quotedAt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((optionals == null) ? 0 : optionals.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Car other = (Car) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (optionals == null) {
	    if (other.optionals != null)
		return false;
	} else if (!optionals.containsAll(other.optionals))
	    return false;
	if (type != other.type)
	    return false;
	return true;
    }

}
