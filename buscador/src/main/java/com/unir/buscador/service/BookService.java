package com.unir.buscador.service;

import com.unir.buscador.model.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> searchBooks(String title, String author, String category, String isbn, Integer rating, Boolean visible);
    Book getBook(Long id);
    Book saveBook(Book book);
    Book updateBookPartial(Long id, Map<String, Object> updates);
    Map<String, String> deleteBook(Long id);
}
