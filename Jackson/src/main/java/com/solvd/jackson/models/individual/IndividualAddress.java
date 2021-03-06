package com.solvd.jackson.models.individual;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.jackson.models.AbstractEntity;

import java.util.Objects;

public class IndividualAddress extends AbstractEntity {
    @JsonProperty("number")
    private int number;
    @JsonProperty("Addresses_id")
    private int addressId;
    @JsonProperty("Individuals_id")
    private int individualId;

    public IndividualAddress(){}

    public IndividualAddress(int id,
                             int number,
                             int addressId,
                             int individualId) {
        super(id);
        this.number = number;
        this.addressId = addressId;
        this.individualId = individualId;
    }

    public int getNumber() {
        return number;
    }

    public int getAddress() {
        return addressId;
    }

    public int getIndividual() {
        return individualId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setIndividualId(int individualId) {
        this.individualId = individualId;
    }

    @Override
    public String toString() {
        return "IndividualAddress{" +
                "number=" + number +
                ", addressId=" + addressId +
                ", individualId=" + individualId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualAddress that = (IndividualAddress) o;
        return getNumber() == that.getNumber() &&
                addressId == that.addressId &&
                individualId == that.individualId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), addressId, individualId);
    }
}
