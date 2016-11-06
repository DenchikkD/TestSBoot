package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Onlin on 02.11.2016.
 */
@SpringBootApplication
public class Boot {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Boot.class, args);
    }

}
