package com.spring.demo.my.mvc.test.service;

public class ModifyService implements IModifyService {
    @Override
    public String add(String name, String addr) throws Exception {
        throw new Exception("exception");
    }

    @Override
    public String edit(Integer id, String name) {
        return id+name;
    }

    @Override
    public String remove(Integer id) {
        return id+"";
    }
}
