package com.demo.consumer.book.controller;

import com.demo.consumer.book.vo.Book;
import com.demo.consumer.rpc.GrpcBookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController(value = "/books")
public class BookController {

    private final GrpcBookClient bookClient;

    @GetMapping
    public List<Book> getAllBooks() {
        return  bookClient.getAllBooks();
    }

    @GetMapping(value = "stream")
    public List<Book> streamAllBooks() {
        return bookClient.streamAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookClient.addNewBook(book);
    }
}
