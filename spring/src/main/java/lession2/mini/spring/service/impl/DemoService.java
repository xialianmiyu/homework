package lession2.mini.spring.service.impl;

import lession2.mini.spring.annotation.MyService;
import lession2.mini.spring.service.IDemoService;

@MyService
public class DemoService implements IDemoService {
    @Override
    public String get(String name) {
        return null;
    }
}
