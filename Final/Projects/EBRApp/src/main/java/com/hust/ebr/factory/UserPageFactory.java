package com.hust.ebr.factory;

import com.hust.ebr.components.abstractdata.controller.ADataPageController;

import java.util.HashMap;
import java.util.Map;

public class UserPageFactory {
    private static final UserPageFactory singleton = new UserPageFactory();
    private Map<String, ADataPageController<?>> registeredPage = new HashMap<>();

    private UserPageFactory() {
    }

    public static UserPageFactory singleton() {
        return singleton;
    }

    public void registerPage(String type, ADataPageController<?> page) {
        registeredPage.put(type, page);
    }

    public ADataPageController<?> createPage(String type) {
        return registeredPage.get(type).createPageController();
    }
}
