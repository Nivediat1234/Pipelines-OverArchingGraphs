package com.example.demo;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.File;
import java.io.IOException;

import java.util.*;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private List<String> verticesGraph1=new ArrayList<>();
    private List<String> verticesGraph2=new ArrayList<>();
    private List<String> EdgesSourceGraph1=new ArrayList<>();
    private List<String> EdgesSourceGraph2=new ArrayList<>();
    private List<String> EdgesDestinationGraph1=new ArrayList<>();
    private List<String> EdgesDestinationGraph2=new ArrayList<>();


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }

    @Override
    public void run(String... args) throws IOException {


    }
}
