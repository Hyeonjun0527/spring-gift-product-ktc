package study;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import study.domain.product.service.ProductService;

@Service
public class WishService {

    private final ProductService productService;

    public WishService(ProductService productService) {
        this.productService = productService;
    }

    public CreateWishResponse create(Long id, CreateWishRequest request, Long memberId) {
        return new CreateWishResponse(id, request.productId(), memberId);
    }
}
