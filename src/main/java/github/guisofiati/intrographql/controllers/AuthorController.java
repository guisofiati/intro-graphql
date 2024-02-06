package github.guisofiati.intrographql.controllers;

import github.guisofiati.intrographql.entities.Author;
import github.guisofiati.intrographql.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    final AuthorService authorService;

    @QueryMapping
    public List<Author> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    @QueryMapping
    public Optional<Author> findAuthorById(@Argument String id) {
        return authorService.findAuthorById(id);
    }

    @MutationMapping
    public Author insertAuthor(@Argument String firstName, @Argument String lastName) {
        return authorService.insertAuthor(firstName, lastName);
    }

    @MutationMapping
    public Author updateAuthor(@Argument String id, @Argument String firstName, @Argument String lastName) {
        return authorService.updateAuthor(id, firstName, lastName);
    }

    @MutationMapping
    public boolean deleteAuthor(@Argument String id) {
        return authorService.deleteAuthor(id);
    }
}
