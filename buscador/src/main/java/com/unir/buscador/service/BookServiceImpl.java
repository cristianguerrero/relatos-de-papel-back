package com.unir.buscador.service;

import com.unir.buscador.repository.BookRepository;
import com.unir.buscador.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> searchBooks(String title, String author, String category, String isbn, Integer rating,
            Boolean visible) {
        // Implementación de búsqueda combinada
        return bookRepository.findAll().stream()
                .filter(book -> (title == null || book.getTitle().contains(title)) &&
                        (author == null || book.getAuthor().contains(author)) &&
                        (category == null || book.getCategory().contains(category)) &&
                        (isbn == null || book.getIsbn().contains(isbn)) &&
                        (rating == null || book.getRating() == rating) &&
                        (visible == null || book.isVisible() == visible))
                .toList();
    }

    @Override
    public Book getBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        return optionalBook.get();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
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

    @Override
    public Map<String, String> deleteBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Map<String, String> response = new HashMap<>();
        System.out.println(bookOptional);

        if (!bookOptional.isPresent()) {
            response.put("message", "Libro no encontrado");
            return response;
        }
        bookRepository.deleteById(id);

        response.put("message", "Se eliminó correctamente");
        return response;
    }
}