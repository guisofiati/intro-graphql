package github.guisofiati.intrographql.controllers;

import github.guisofiati.intrographql.entities.Author;
import github.guisofiati.intrographql.entities.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    /*
    query: http://localhost:8080/graphiql
    query bookDetails {
        bookById(id: "book-1") {
            id
            name
            #pageCount
            author {
              id
              #firstName
              lastName
            }
        }
    }
     */
    @QueryMapping // binds this method to the query 'getById'
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping // binds this method to a field in graphql schema
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }
}
