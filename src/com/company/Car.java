package com.company;

import java.util.Date;

public class Car {
    private Integer carId;
    private Date manufactureYear;
    private String model;
    private Integer price;
    private String color;

    public Car(){}

    public Car(Integer carId, Date manufactureYear, String model, Integer price, String color) {
        this.carId = carId;
        this.manufactureYear = manufactureYear;
        this.model = model;
        this.price = price;
        this.color = color;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Date manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", manufactureYear=" + manufactureYear +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
