package com.judacribz.amazonbooks.model.event;

import com.judacribz.amazonbooks.model.amazonbooksresponse.Book;

import java.util.List;

public class AmazonBooksEvent {

    private List<Book> books;

    public AmazonBooksEvent(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setAmazonBooksResponse(List<Book> books) {
        this.books = books;
    }
}
