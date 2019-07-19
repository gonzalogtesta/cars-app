package com.sysone.app.model.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sysone.app.model.Car;
import com.sysone.app.model.CarType;

/**
 * Tests for {@code CarBuilder}
 * 
 * @author gonzalogtesta@gmail.com
 *
 */
public class CarBuilderTest {

    static Stream<?> optionals() {
	Set<String> optionals = new HashSet<>();
	optionals.add("AA");
	optionals.add("ABS");
	return Stream.of(optionals);
    }

    @Test
    public void testBuilderWithoutData() {
	CarBuilder builder = new CarBuilder();
	builder.type(CarType.SEDAN);

	Car car = builder.getCar();
	assertNotNull(car);

	assertEquals(CarType.SEDAN, car.getType());
    }

    @Test
    public void testCarBaseFamily() {
	CarBuilder builder = new CarBuilder();
	builder.type(CarType.FAMILY);

	Car car = builder.getCar();
	assertNotNull(car);

	assertEquals(CarType.FAMILY, car.getType());
    }

    @Test
    public void testCarBaseCoupe() {
	CarBuilder builder = new CarBuilder();
	builder.type(CarType.COUPE);

	Car car = builder.getCar();
	assertNotNull(car);

	assertEquals(CarType.COUPE, car.getType());
    }

    @Test
    public void testCarBaseWithOptional() {
	CarBuilder builder = new CarBuilder();
	builder.type(CarType.COUPE);
	builder.withOptional("AA");
	Car car = builder.getCar();
	assertNotNull(car);

	assertEquals(CarType.COUPE, car.getType());
	assertNotNull(car.getOptionals());
	assertEquals(1, car.getOptionals().size());
    }

    @ParameterizedTest
    @MethodSource("optionals")
    public void testCarBaseWithOptionals(Set<String> optionals) {
	CarBuilder builder = new CarBuilder();
	builder.type(CarType.COUPE);
	builder.withOptionals(optionals);
	Car car = builder.getCar();
	assertNotNull(car);

	assertEquals(CarType.COUPE, car.getType());

	assertNotNull(car.getOptionals());
	assertEquals(optionals.size(), car.getOptionals().size());
    }

    @Test
    public void testReturnSameBuilderForType() {
	CarBuilder builder = new CarBuilder();
	CarBuilder builder2 = builder.type(CarType.COUPE);

	Car car = builder.getCar();
	assertNotNull(car);

	assertEquals(builder, builder2);
    }

    @Test
    public void testReturnSameBuilderForOptional() {
	CarBuilder builder = new CarBuilder();
	builder.type(CarType.COUPE);
	CarBuilder builder2 = builder.withOptional("AA");

	Car car = builder.getCar();
	assertNotNull(car);

	assertEquals(builder, builder2);
    }

    @ParameterizedTest
    @MethodSource("optionals")
    public void testReturnSameBuilderForOptionals(Set<String> optionals) {
	CarBuilder builder = new CarBuilder();
	builder.type(CarType.COUPE);
	CarBuilder builder2 = builder.withOptionals(optionals);

	Car car = builder.getCar();
	assertNotNull(car);

	assertEquals(builder, builder2);
    }
}
