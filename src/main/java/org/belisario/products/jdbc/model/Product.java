package org.belisario.products.jdbc.model;

import java.util.Date;

public class Product {

    private Long id;
    private String name;
    private Double price;
    private Date registerDate;

    public Product(Long id, String name, Double price, Date regsiterDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.registerDate = regsiterDate;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public static void printProductName(Product product) {
        System.out.println(product.getName());
    }
}
