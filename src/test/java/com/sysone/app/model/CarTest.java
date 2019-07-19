package com.sysone.app.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for {@code Car}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public class CarTest {

    static Car[] baseCar() {
	return new Car[] { new Car(CarType.SEDAN) };
    }

    static Car[] baseCarWithOptional() {
	Set<String> optionals = new HashSet<>();
	optionals.add("ABS");
	return new Car[] { new Car(CarType.SEDAN, optionals) };
    }

    @ParameterizedTest
    @EnumSource(CarType.class)
    public void testCarType(CarType type) {
	Car car = new Car(type);
	assertEquals("Invalid car type", type, car.getType());
    }

    @ParameterizedTest
    @EnumSource(CarType.class)
    public void testCarWithOptionals(CarType type) {

	Car car = new Car(type, new HashSet<>());
	assertNotNull("Car optionals is null", car.getOptionals());
	assertEquals("Car optionals is null", new HashSet<String>(), car.getOptionals());
    }

    @ParameterizedTest
    @MethodSource("baseCar")
    public void testAddOptionalToCar(Car car) {
	car.addOptional("AA");
	assertNotNull(car.getOptionals());
	assertEquals(1, car.getOptionals().size());
    }

    @ParameterizedTest
    @MethodSource("baseCarWithOptional")
    public void testCarRemoveOptional(Car car) {
	Set<String> optionals = car.getOptionals();
	assertEquals(1, car.getOptionals().size());
	String optional = optionals.iterator().next();
	car.removeOptional(optional);
	assertEquals(0, car.getOptionals().size());

    }

    @Test
    public void equalCars() {
	assertEquals(new Car(), new Car());
    }

    @Test
    public void equalCarsWithOptionals() {
	Car car1 = new Car();
	car1.addOptional("AA");
	Car car2 = new Car();
	car2.addOptional("AA");
	assertEquals(car1, car2);
    }
}
