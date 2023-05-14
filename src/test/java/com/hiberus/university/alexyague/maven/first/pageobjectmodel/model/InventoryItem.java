package com.hiberus.university.alexyague.maven.first.pageobjectmodel.model;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//
//@AllArgsConstructor
//@Setter
//@Getter

public class InventoryItem {
    private String name;
    private String description;
    private float price;

    public InventoryItem(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public InventoryItem(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
