package org.acme.model;

import java.util.List;

public class Book {

    private Long id;
    private String title;
    private int year;

    private List<Author> authors;
    private List<Loan> loans;

}