package com.solvd.jackson.services;

public interface IAbstractServ<T> {
    void create(T t);
    T getByID(int id);
    void update(T t);
    void delete (int id);
}
