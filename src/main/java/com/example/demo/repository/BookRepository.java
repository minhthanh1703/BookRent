package com.example.demo.repository;

import com.example.demo.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);

    Book findByName(String name);

    Book findById(int id);
}
