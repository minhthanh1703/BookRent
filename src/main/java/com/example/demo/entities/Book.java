package com.example.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private double price;

    @Column(name = "Author")
    private String author;

    @Column(name = "CategoryId")
    private int categoryId;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Details")
    private String details;

    @Column(name = "CreateAt")
    private Timestamp createAt;

    @Column(name = "UpdateAt")
    private Timestamp updateAt;

    @Column(name = "StatusActive")
    private boolean statusActive;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }


    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }


    public boolean isStatusActive() {
        return statusActive;
    }

    public void setStatusActive(boolean statusActive) {
        this.statusActive = statusActive;
    }

}
