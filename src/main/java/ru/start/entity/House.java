package ru.start.entity;

import java.util.Objects;

public class House {
    private final String city;
    private final String street;
    private final Short house;
    private final Short floor;

    public House(String city, String street, Short house, Short floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }


    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Short getHouse() {
        return house;
    }

    public Short getFloor() {
        return floor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House houseJSON = (House) o;
        return Objects.equals(city, houseJSON.city) && Objects.equals(street, houseJSON.street) && Objects.equals(house, houseJSON.house) && Objects.equals(floor, houseJSON.floor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, floor);
    }

    @Override
    public String toString() {
        return  "Город: " + city +
                ", Улица: " + street +
                ", Дом: " + house +
                ", Этажей: " + floor;
    }
}
