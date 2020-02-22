package com.demo.consumer.rpc;

import com.demo.consumer.book.vo.Book;
import com.example.rpc.book.BookMessage;
import com.example.rpc.book.BookServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrpcBookClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcBookClient.class);

    private BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;

    @Value("${grpc.host}")
    private String grpcHost;

    @Value("${grpc.port}")
    private int grpcPort;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort).usePlaintext().build();
        bookServiceBlockingStub = BookServiceGrpc.newBlockingStub(managedChannel);
    }

    public List<Book> getAllBooks() {
        return bookServiceBlockingStub.findAll(Empty.newBuilder().build())
                .getBookList()
                .stream()
                .map(Book::fromBookMessage)
                .collect(Collectors.toList());
    }

    public Book addNewBook(Book book) {
        BookMessage response = bookServiceBlockingStub.addBook(book.toBookMessage());
        return Book.fromBookMessage(response);
    }

    public List<Book> streamAllBooks() {
        List<Book> result = new ArrayList<>();
        int pageNumber = 0;
        while (bookServiceBlockingStub.streamAll(Empty.newBuilder().build()).hasNext()) {
            LOGGER.info("receive page " + pageNumber);
            List<Book> page = bookServiceBlockingStub.streamAll(Empty.newBuilder().build()).next()
                    .getBookList()
                    .stream()
                    .map(Book::fromBookMessage)
                    .collect(Collectors.toList());
            result.addAll(page);
        }
        return result;
    }
}
