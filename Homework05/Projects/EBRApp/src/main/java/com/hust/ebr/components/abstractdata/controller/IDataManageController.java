package com.hust.ebr.components.abstractdata.controller;

public interface IDataManageController<T> {
    void create(T t);
    void read(T t);
    void delete(T t);
    void update(T t);
}
