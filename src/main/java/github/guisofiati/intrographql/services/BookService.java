package github.guisofiati.intrographql.services;

import github.guisofiati.intrographql.entities.Book;
import github.guisofiati.intrographql.repositories.AuthorRepository;
import github.guisofiati.intrographql.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    final BookRepository bookRepository;
    final AuthorRepository authorRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(String bookId) {
        UUID bookUUID = UUID.fromString(bookId);
        return Optional.ofNullable(bookRepository.findById(bookUUID).orElseThrow(() -> new EntityNotFoundException("Book id: " + bookId + " not found.")));
    }

    public Book insertBook(String name, Integer pages, String authorId) {
        UUID authorUUID = UUID.fromString(authorId);
        var author = authorRepository.findById(authorUUID).orElseThrow(() -> new EntityNotFoundException("Author id: " + authorId + " not found."));
        var newBook = new Book();
        newBook.setName(name);
        newBook.setPages(pages);
        newBook.setAuthor(author);
        return bookRepository.save(newBook);
    }

    public Book updateBook(String bookId, String name, Integer pages, String authorId) {
        UUID bookUUID = UUID.fromString(bookId);
        var bookEntity = bookRepository.findById(bookUUID).orElseThrow(() -> new EntityNotFoundException("Book id: " + bookId + " not found."));
        if (name != null) bookEntity.setName(name);
        if (pages != null) bookEntity.setPages(pages);
        if (authorId != null) {
            UUID authorUUID = UUID.fromString(authorId);
            var author = authorRepository.findById(authorUUID).orElseThrow(() -> new EntityNotFoundException("Author id: " + authorId + " not found."));
            bookEntity.setAuthor(author);
        }
        bookRepository.save(bookEntity);
        return bookEntity;
    }

    public boolean deleteBook(String bookId) {
        UUID bookUUID = UUID.fromString(bookId);
        var bookEntity = bookRepository.findById(bookUUID).orElseThrow(() -> new EntityNotFoundException("Book id: " + bookId + " not found."));
        bookRepository.deleteById(bookEntity.getId());
        return true;
    }
}
