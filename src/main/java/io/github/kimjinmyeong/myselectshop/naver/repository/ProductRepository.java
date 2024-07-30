package io.github.kimjinmyeong.myselectshop.naver.repository;

import io.github.kimjinmyeong.myselectshop.naver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
