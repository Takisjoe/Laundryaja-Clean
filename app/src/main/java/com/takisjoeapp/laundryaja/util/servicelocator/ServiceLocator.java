package com.takisjoeapp.laundryaja.util.servicelocator;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<String, Object> services = new HashMap<>();

    public static void registerService(String key, Object service) {
        services.put(key, service);
    }

    public static <T> T getService(String key) {
//        if (!services.containsKey(key)) {
//            throw new IllegalStateException("Service not registered yet for key: " + key);
//        }
        return (T) services.get(key);
    }
}
