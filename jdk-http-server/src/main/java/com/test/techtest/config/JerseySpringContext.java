package com.test.techtest.config;

import com.test.techtest.SpringRequestResourceClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SpringRequestResourceClass.class)
public class JerseySpringContext {}
