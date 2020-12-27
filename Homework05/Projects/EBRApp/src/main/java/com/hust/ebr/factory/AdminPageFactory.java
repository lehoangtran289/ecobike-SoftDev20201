package com.hust.ebr.factory;

import com.hust.ebr.components.abstractdata.controller.ADataPageController;

import java.util.HashMap;
import java.util.Map;

public class AdminPageFactory {
    private static final AdminPageFactory singleton = new AdminPageFactory();
    private Map<String, ADataPageController<?>> registeredPage = new HashMap<>();

    private AdminPageFactory() {
    }

    public static AdminPageFactory singleton() {
        return singleton;
    }

    public void registerPage(String type, ADataPageController<?> page) {
        registeredPage.put(type, page);
    }

    public ADataPageController<?> createPage(String type) {
        return registeredPage.get(type).createPageController();
    }
}
