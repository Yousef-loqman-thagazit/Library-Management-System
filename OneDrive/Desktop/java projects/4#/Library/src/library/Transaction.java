/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

 

public class Transaction {
    private int memberId;
    private int bookId;
    private String status;

    public Transaction(int memberId, int bookId, String status) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.status = status;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getStatus() {
        return status;
    }
}
