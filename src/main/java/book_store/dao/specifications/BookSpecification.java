package book_store.dao.specifications;

import book_store.dao.entity.Book;
import book_store.dao.filters.BookFilter;
import book_store.dao.filters.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class BookSpecification {

    public static Specification<Book> byFilter(BookFilter filter) {
        return (root, q , cb) -> {
            Predicate predicate = cb.isNotNull(root.get("name"));

            if (filter.getName() != null) {
                predicate = cb.and(predicate, cb.like(root.get("name"),  filter.getName()));
            }

         return  predicate;
    };
    }

}
