package io.github.kimjinmyeong.myselectshop.naver.controller;

import io.github.kimjinmyeong.myselectshop.naver.dto.ProductMypriceRequestDto;
import io.github.kimjinmyeong.myselectshop.naver.dto.ProductRequestDto;
import io.github.kimjinmyeong.myselectshop.naver.dto.ProductResponseDto;
import io.github.kimjinmyeong.myselectshop.naver.security.UserDetailsImpl;
import io.github.kimjinmyeong.myselectshop.naver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProducts(userDetails.getUser(),  page-1, size, sortBy, isAsc);
    }

    @PostMapping("/products/{productId}/folder")
    public void addFolder(
            @PathVariable Long productId,
            @RequestParam Long folderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        productService.addFolder(productId, folderId, userDetails.getUser());
    }

}
