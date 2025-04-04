package com.group6.agile.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    void save(String key, String value);

    void save(String key, String value, long seconds, TimeUnit timeUnit);

    String get(String key);

    void delete(String key);
}
