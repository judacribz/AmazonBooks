package com.judacribz.amazonbooks;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.judacribz.amazonbooks.model.amazonbooksresponse.Book;

import static org.junit.Assert.assertEquals;

public class BookUnitTest {
    final private String jsonStr = new StringBuilder("{")
            .append("\"title\"").append(":")
            .append("\"Harry Potter: Complete 8-Film Collection (DVD, 2011, 8-Disc Set)\",")
            .append("\"imageURL\"").append(":")
            .append("\"testing\",")
            .append("\"author\"").append(":")
            .append("\"Harry Potter\"")
            .append("}")
            .toString();
    final private String testing = "testing";
    private Book book;
    private Gson gson = new Gson();

    @Before
    public void setupBookObject() {
        book = gson.fromJson(jsonStr, Book.class);
    }

    @Test
    public void book_HasRightImageUrl() {
        assertEquals(testing, book.getImageURL());
    }
}
