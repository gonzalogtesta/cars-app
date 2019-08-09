package com.cars.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
	return new ModelMapper();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String defaultPath() {
	return "";
    }

}
