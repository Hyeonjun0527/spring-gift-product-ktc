package study.domain.product.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import study.pagination.Page;
import study.pagination.Pageable;
import study.domain.product.dto.ProductRequest;
import study.domain.product.dto.ProductResponse;
import study.domain.product.model.Product;
import study.domain.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponse::from);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        }
        Product product = productRepository.findById(id);
        return ProductResponse.from(product);
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = new Product(null, productRequest.getName(), productRequest.getPrice(), productRequest.getImageUrl());
        Product savedProduct = productRepository.save(product);
        return ProductResponse.from(savedProduct);
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        }
        Product updatedProduct = new Product(id, productRequest.getName(), productRequest.getPrice(), productRequest.getImageUrl());
        productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        }
        productRepository.deleteById(id);
    }
    

}
