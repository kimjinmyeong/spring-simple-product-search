package io.github.kimjinmyeong.myselectshop.Scheduler;

import io.github.kimjinmyeong.myselectshop.naver.dto.ItemDto;
import io.github.kimjinmyeong.myselectshop.naver.entity.Product;
import io.github.kimjinmyeong.myselectshop.naver.repository.ProductRepository;
import io.github.kimjinmyeong.myselectshop.naver.service.NaverApiService;
import io.github.kimjinmyeong.myselectshop.naver.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "Scheduler")
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final NaverApiService naverApiService;

    private final ProductService productService;

    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 0 * * *") // Every day at midnight
    public void updatePrice() throws InterruptedException {
        log.info("Price update execution");
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {

            // Retrieve one product per second (NAVER API limit)
            TimeUnit.SECONDS.sleep(1);

            String title = product.getTitle();
            List<ItemDto> itemDtoList = naverApiService.searchItems(title);

            if (!itemDtoList.isEmpty()) {
                ItemDto itemDto = itemDtoList.get(0);
                Long id = product.getId();
                try {
                    productService.updateBySearch(id, itemDto);
                } catch (Exception e) {
                    log.error("{} : {}", id, e.getMessage());
                }
            }
        }
    }

}
