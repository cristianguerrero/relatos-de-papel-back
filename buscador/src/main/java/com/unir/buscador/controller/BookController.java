package com.unir.buscador.controller;

import com.unir.buscador.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unir.buscador.model.Book;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping
    public List<Book> searchBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Boolean visible) {
        return bookServiceImpl.searchBooks(title, author, category, isbn, rating, visible);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookServiceImpl.getBook(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookServiceImpl.saveBook(book);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBookPartial(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Book updatedBook = bookServiceImpl.updateBookPartial(id, updates);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookServiceImpl.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}