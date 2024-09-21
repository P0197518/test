package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther zzyy
 * @create 2023-11-23 17:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main9002
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main9002.class,args);
    }
}