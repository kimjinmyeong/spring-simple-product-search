package io.github.kimjinmyeong.myselectshop.naver.service;

import io.github.kimjinmyeong.myselectshop.naver.dto.ProductRequestDto;
import io.github.kimjinmyeong.myselectshop.naver.dto.ProductResponseDto;
import io.github.kimjinmyeong.myselectshop.naver.entity.Product;
import io.github.kimjinmyeong.myselectshop.naver.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return new ProductResponseDto(productRepository.save(new Product(productRequestDto)));
    }

}
