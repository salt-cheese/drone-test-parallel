package com.demo.producer.book.entity;

import com.example.rpc.book.BookMessage;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "isbn", unique = true)
    private String isbn;

    @NotNull
    @Column(name = "category")
    private String category;

    public static Book fromBookMessage(BookMessage bookMessage) {
        return Book.builder()
            .title(bookMessage.getTitle())
            .author(bookMessage.getAuthor())
            .isbn(bookMessage.getIsbn())
            .category(bookMessage.getCategory())
            .build();
    }

    public BookMessage toBookMessage() {
        return BookMessage.newBuilder()
            .setTitle(title)
            .setAuthor(author)
            .setIsbn(isbn)
            .setCategory(category)
            .build();
    }
}
