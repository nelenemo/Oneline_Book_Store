package com.nemo.bookservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}