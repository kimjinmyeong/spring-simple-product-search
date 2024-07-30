package io.github.kimjinmyeong.myselectshop.naver.service;

import io.github.kimjinmyeong.myselectshop.naver.dto.ItemDto;
import io.github.kimjinmyeong.myselectshop.naver.dto.ProductMypriceRequestDto;
import io.github.kimjinmyeong.myselectshop.naver.dto.ProductRequestDto;
import io.github.kimjinmyeong.myselectshop.naver.dto.ProductResponseDto;
import io.github.kimjinmyeong.myselectshop.naver.entity.Product;
import io.github.kimjinmyeong.myselectshop.naver.entity.User;
import io.github.kimjinmyeong.myselectshop.naver.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto, User user) {
        Product product = productRepository.save(new Product(requestDto, user));
        return new ProductResponseDto(product);
    }

    public static final int MIN_MY_PRICE = 100;

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        int myprice = requestDto.getMyprice();
        if (myprice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("Invalid interest price. Please set it to at least " + MIN_MY_PRICE + " won.");
        }

        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("Product not found.")
        );

        product.update(requestDto);

        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getProducts(User user) {
        List<Product> productList = productRepository.findAllByUser(user);
        return productList.stream().map(ProductResponseDto::new).toList();
    }

    @Transactional
    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Product Not Found")
        );
        product.updateByItemDto(itemDto);
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductResponseDto::new).toList();
    }

}
