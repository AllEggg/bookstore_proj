package book_store.dao.specifications;
import book_store.dao.entity.Book;
import book_store.dao.entity.BookOrder;
import book_store.dao.filters.BookFilter;
import book_store.dao.filters.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
public class OrderSpecification {
    public static Specification<BookOrder> orderByFilter(OrderFilter filter) {
        return (root, q , cb) -> {
            Predicate predicate = cb.isNotNull(root.get("id"));

            if (filter.getId() != null) {
                predicate = cb.and(predicate, cb.like(root.get("id"), filter.getId().toString()));
            }

            return  predicate;
        };
    }

}
