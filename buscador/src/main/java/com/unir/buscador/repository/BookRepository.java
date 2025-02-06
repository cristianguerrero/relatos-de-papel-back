package com.unir.buscador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unir.buscador.model.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthorContaining(String author);
    List<Book> findByCategory(String category);
    List<Book> findByIsbn(String isbn);
    List<Book> findByRating(int rating);
    List<Book> findByVisible(boolean visible);
}