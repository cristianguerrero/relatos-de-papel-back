package com.unir.buscador.service;

import com.unir.buscador.repository.BookRepository;
import com.unir.buscador.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> searchBooks(String title, String author, String category, String isbn, Integer rating, Boolean visible) {
        // Implementación de búsqueda combinada
        return bookRepository.findAll(); // Debes implementar la lógica combinada
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}