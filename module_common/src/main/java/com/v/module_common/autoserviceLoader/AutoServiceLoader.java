package com.v.module_common.autoserviceLoader;

import java.util.ServiceLoader;

public final class AutoServiceLoader {

    private AutoServiceLoader() {

    }

    public static <T> T load(Class<T> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
