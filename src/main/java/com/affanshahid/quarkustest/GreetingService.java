package com.affanshahid.quarkustest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
    public String getGreeting(String name) {
        return "hello " + name;
    }
}
