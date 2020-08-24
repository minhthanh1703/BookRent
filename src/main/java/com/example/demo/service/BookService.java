package com.example.demo.service;

import com.example.demo.entities.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks() throws Exception;

    public Page<Book> getAllBooks(int page, int size) throws Exception;

    public Book createBook(Book book) throws Exception;

    public Book updateBook(Book book) throws Exception;

    public Book disableBook(int bookId) throws Exception;

    public Book enableBook(int bookId) throws Exception;
}
