package github.guisofiati.intrographql.services;

import github.guisofiati.intrographql.entities.Author;
import github.guisofiati.intrographql.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    final AuthorRepository authorRepository;

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(String authorId) {
        UUID authorUUID = UUID.fromString(authorId);
        return Optional.ofNullable(authorRepository.findById(authorUUID).orElseThrow(() -> new EntityNotFoundException("Author id " + authorId + " not found")));
    }

    public Author insertAuthor(String firstName, String lastName) {
        var author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return authorRepository.save(author);
    }

    public Author updateAuthor(String authorId, String firstName, String lastName) {
        UUID authorUUID = UUID.fromString(authorId);
        var authorEntity = authorRepository.findById(authorUUID).orElseThrow(() -> new EntityNotFoundException("Author id: " + authorId + " not found."));
        if (firstName != null) authorEntity.setFirstName(firstName);
        if (lastName != null) authorEntity.setLastName(lastName);
        return authorRepository.save(authorEntity);
    }

    public boolean deleteAuthor(String authorId) {
        UUID authorUUID = UUID.fromString(authorId);
        var authorEntity = authorRepository.findById(authorUUID).orElseThrow(() -> new EntityNotFoundException("Author id: " + authorId + " not found."));
        try {
            authorRepository.deleteById(authorEntity.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Cannot be deleted because it contains books associated with this author.");
        }
        return true;
    }
}
