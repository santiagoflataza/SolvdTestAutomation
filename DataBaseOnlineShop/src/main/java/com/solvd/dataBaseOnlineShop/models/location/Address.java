package com.solvd.dataBaseOnlineShop.models.location;

import com.solvd.dataBaseOnlineShop.models.AbstractEntity;

import java.util.Objects;

public class Address extends AbstractEntity {
    private String name;
    private int cityId;

    public Address(){}

    public Address(int id, String name, int cityId) {
        super(id);
        this.name = name;
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public int getCity() {
        return cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", cityId=" + cityId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return cityId == address.cityId &&
                getName().equals(address.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), cityId);
    }
}
