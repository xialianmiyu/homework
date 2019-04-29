package com.spring.demo.my.mvc.test.service;

import java.util.Date;

public class QueryService implements IQueryService {
    @Override
    public String query(String name) {

        return "{name:\"" + name + "\",time:\"" + new Date() + "\"}";
    }
}
