package com.sysone.app.model.builder;

import java.util.HashSet;
import java.util.Set;

import com.sysone.app.model.Car;
import com.sysone.app.model.CarType;

/**
 * Car builder
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public class CarBuilder {

    private CarType type;
    private Set<String> optionals;

    /**
     * Builder constructor
     */
    public CarBuilder() {
	this.optionals = new HashSet<>();
    }

    /**
     * Sets the {@code CartType} to assign to the {@code Car}
     * 
     * @param type
     * 
     *             a {@code CartType} that represents the type of car
     * @return returns the same {@code CarBuilder} instance
     */
    public CarBuilder type(CarType type) {
	this.type = type;
	return this;
    }

    /**
     * Sets an optional to assign to the {@code Car}
     * 
     * @param optional
     * 
     *                 string representing the optional item to include
     * @return returns the same {@code CarBuilder} instance
     */
    public CarBuilder withOptional(String optional) {
	this.optionals.add(optional);
	return this;

    }

    /**
     * Sets optionals to assign to the {@code Car}
     * 
     * @param optionals
     * 
     *                  a {@code List} of strings representing the optional items to
     *                  include
     * @return returns the same {@code CarBuilder} instance
     */
    public CarBuilder withOptionals(Set<String> optionals) {
	this.optionals.addAll(optionals);
	return this;
    }

    /**
     * Builds a {@code Car} based on the configurations provided
     * 
     * @return returns a {@code Car} instance
     */
    public Car getCar() {

	return new Car(this.type, this.optionals);
    }
}
