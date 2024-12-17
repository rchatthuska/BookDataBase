package com.example.finalprojectbase;

public class Book {
    private String title;
    private String author;
    private int yearPublished;
    private double price;
    private boolean bestseller;

    public Book() {
    }

    public Book(String title, String author, int yearPublished, double price, boolean isBestseller) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.price = price;
        this.bestseller = isBestseller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBestseller() {
        return bestseller;
    }

    public void setBestseller(boolean bestseller) {
        this.bestseller= bestseller;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                ", price=" + price +
                ", isBestseller=" + bestseller +
                '}';
    }
}
