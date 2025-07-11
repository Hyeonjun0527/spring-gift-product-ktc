package study.domain.product.service;

import study.pagination.Page;
import study.pagination.Pageable;
import study.domain.product.dto.ProductRequest;
import study.domain.product.dto.ProductResponse;

public interface ProductService {

    public Page<ProductResponse> getAllProducts(Pageable pageable);

    public ProductResponse getProductById(Long id);

    public ProductResponse addProduct(ProductRequest productRequest);

    public void updateProduct(Long id, ProductRequest productRequest);

    public void deleteProduct(Long id);


}
