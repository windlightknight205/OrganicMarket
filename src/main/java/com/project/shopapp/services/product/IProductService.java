package com.project.shopapp.services.product;
import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.dtos.ProductImageDTO;
import com.project.shopapp.responses.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.project.shopapp.models.*;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;
    Product getProductById(long id) throws Exception;
    public Page<ProductResponse> getAllProducts(String keyword,
                                                Long categoryId, PageRequest pageRequest);
    Product updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception;

    List<Product> findProductsByIds(List<Long> productIds);
    void deleteFile(String filename) throws IOException;
    List<Product> findFavouriteProductByUserId(Long userId) throws Exception;
    Favourite addFavourite(Long userId, Long productId) throws Exception;
    boolean getFavourite(Long userId, Long productId) throws Exception;
    void deleteFavourite(Long userId, Long productId) throws Exception;
}
