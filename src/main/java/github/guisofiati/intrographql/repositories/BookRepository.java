package github.guisofiati.intrographql.repositories;

import github.guisofiati.intrographql.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> { }
