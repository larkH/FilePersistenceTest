package com.mi.litepaltest;

import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {

    private int id;
    private String name;
    private String author;
    private int pages;
    private double price;
    private String press; //出版社

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Book() {
    }

    public Book(String name, String author, int pages, double price, String press) {
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.press = press;
    }

    public Book(int id, String name, String author, int pages, double price, String press) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.press = press;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", press='" + press + '\'' +
                '}';
    }
}
