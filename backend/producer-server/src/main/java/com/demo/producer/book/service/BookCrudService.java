package com.demo.producer.book.service;

import com.demo.producer.book.entity.Book;
import com.demo.producer.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCrudService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAllPage(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size));
    }

    public Book createOne(Book book) {
        return bookRepository.save(book);
    }
}
