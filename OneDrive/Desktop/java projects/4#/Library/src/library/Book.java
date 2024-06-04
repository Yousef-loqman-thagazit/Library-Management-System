/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;
 

public class Book {
    private int id;
    private String title;
    private String author;
    private int quantity;
    private String genre;

    public Book(int id, String title, String author, int quantity, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getGenre() {
        return genre;
    }

    public void borrowBook() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void returnBook() {
        quantity++;
    }
}
