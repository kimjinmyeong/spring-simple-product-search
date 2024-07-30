package io.github.kimjinmyeong.myselectshop.naver.repository;

import io.github.kimjinmyeong.myselectshop.naver.entity.Folder;
import io.github.kimjinmyeong.myselectshop.naver.entity.Product;
import io.github.kimjinmyeong.myselectshop.naver.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
