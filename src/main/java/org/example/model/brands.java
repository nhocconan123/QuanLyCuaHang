package org.example.model;

public class brands {
    private int id;
    private String brand_name;

    private String brand_address;

    public brands() {
    }

    public brands(String brand_name, String brand_address) {
        this.brand_name = brand_name;
        this.brand_address = brand_address;
    }

    public brands(int id, String brand_name, String brand_address) {
        this.id = id;
        this.brand_name = brand_name;
        this.brand_address = brand_address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_address() {
        return brand_address;
    }

    public void setBrand_address(String brand_address) {
        this.brand_address = brand_address;
    }

    @Override
    public String toString() {
        return "brands{" +
                "id=" + id +
                ", brand_name='" + brand_name + '\'' +
                ", brand_address='" + brand_address + '\'' +
                '}';
    }
}
