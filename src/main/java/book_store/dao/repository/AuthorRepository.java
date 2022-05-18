package book_store.dao.repository;
import book_store.dao.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author getAuthorByName(String name);

    Boolean existsAuthorByName(String name);

}
