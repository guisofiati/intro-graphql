package github.guisofiati.intrographql.controllers;

import github.guisofiati.intrographql.entities.Book;
import github.guisofiati.intrographql.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    final BookService bookService;

    // @SchemaMapping(typeName = "Query", value = "findAllBooks")
    @QueryMapping // method's name must be equal to the name defined at Query {}
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @QueryMapping
    public Optional<Book> findBookById(@Argument String id) { // argument name must be the same as declared in schema
        return bookService.findBookById(id);
    }

    @MutationMapping
    public Book insertBook(@Argument String name, @Argument Integer pages, @Argument String author) {
        return bookService.insertBook(name, pages, author);
    }

    @MutationMapping
    public Book updateBook(@Argument String id, @Argument String name, @Argument Integer pages, @Argument String author) {
        return bookService.updateBook(id, name, pages, author);
    }

    @MutationMapping
    public boolean deleteBook(@Argument String id) {
        return bookService.deleteBook(id);
    }
}
