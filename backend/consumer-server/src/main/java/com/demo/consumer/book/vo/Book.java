package com.demo.consumer.book.vo;

import com.example.rpc.book.BookMessage;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    private String title;
    private String author;
    private String isbn;
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
