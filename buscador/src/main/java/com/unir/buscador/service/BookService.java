package com.unir.buscador.service;

import com.unir.buscador.repository.BookRepository;
import com.unir.buscador.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Book updateBookPartial(Long id, Map<String, Object> updates) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        Book book = optionalBook.get();

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Book.class, key); // Encuentra el campo por nombre
            if (field != null) {
                field.setAccessible(true); // Permite modificar el campo privado
                ReflectionUtils.setField(field, book, value); // Aplica el nuevo valor
            }
        });

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}