package com.company.cities;

public class Country {
    private int id;
    private String countryName;
    private int countryCode;
    private int countryPopulation;

    public Country(int id, String name, int countryCode, int countryPopulation) {
        this.id = id;
        this.countryName = name;
        this.countryCode = countryCode;
        this.countryPopulation = countryPopulation;
    }

    @Override
    public String toString(){
        return "Country ID: " + getId() +
                "\nCountry name: " + getCountryName() +
                "\nCountry code: " + getCountryCode() +
                "\nCountry population: " + getCountryPopulation();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(int countryPopulation) {
        this.countryPopulation = countryPopulation;
    }
}
