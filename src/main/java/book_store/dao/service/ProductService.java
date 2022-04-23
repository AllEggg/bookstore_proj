package book_store.dao.service;

import book_store.dao.entity.Product;
import book_store.dao.repository.ProductRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaBuilder;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product getProductById(Integer id) {
        return repository.getProductById(id);
    }

    public void setName(String name, int id) {
        repository.setName(name, id);
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void updateProduct(Product product) {
        repository.save(product);
    }
}
