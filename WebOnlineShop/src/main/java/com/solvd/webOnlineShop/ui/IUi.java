package com.solvd.webOnlineShop.ui;

public interface IUi<T extends Enum<T>> {

    T changeOption(Class<T> options);

    T manageOptions(Class<T> options);
}
