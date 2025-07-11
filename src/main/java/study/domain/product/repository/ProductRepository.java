package study.domain.product.repository;

import study.pagination.Page;
import study.pagination.Pageable;
import study.domain.product.model.Product;

public interface ProductRepository {

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);

}
