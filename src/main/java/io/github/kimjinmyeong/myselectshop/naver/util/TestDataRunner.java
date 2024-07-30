package io.github.kimjinmyeong.myselectshop.naver.util;

import io.github.kimjinmyeong.myselectshop.naver.dto.ItemDto;
import io.github.kimjinmyeong.myselectshop.naver.entity.Product;
import io.github.kimjinmyeong.myselectshop.naver.entity.User;
import io.github.kimjinmyeong.myselectshop.naver.entity.UserRoleEnum;
import io.github.kimjinmyeong.myselectshop.naver.repository.ProductRepository;
import io.github.kimjinmyeong.myselectshop.naver.repository.UserRepository;
import io.github.kimjinmyeong.myselectshop.naver.service.NaverApiService;
import io.github.kimjinmyeong.myselectshop.naver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static io.github.kimjinmyeong.myselectshop.naver.service.ProductService.MIN_MY_PRICE;

@Profile("dev")
@Component
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    NaverApiService naverApiService;

    @Override
    public void run(ApplicationArguments args) {
        User testUser = new User("test", passwordEncoder.encode("1234"), "test@test.com", UserRoleEnum.USER);
        testUser = userRepository.save(testUser);

        createTestData(testUser, "Shoes");
        createTestData(testUser, "Snacks");
        createTestData(testUser, "Keyboard");
        createTestData(testUser, "Tissue");
        createTestData(testUser, "Phone");
        createTestData(testUser, "Album");
        createTestData(testUser, "Headphones");
        createTestData(testUser, "Earphones");
        createTestData(testUser, "Laptop");
        createTestData(testUser, "Wireless Earphones");
        createTestData(testUser, "Monitor");
    }

    private void createTestData(User user, String searchWord) {
        List<ItemDto> itemDtoList = naverApiService.searchItems(searchWord);
        List<Product> productList = new ArrayList<>();
        for (ItemDto itemDto : itemDtoList) {
            Product product = new Product();
            product.setUser(user);
            product.setTitle(itemDto.getTitle());
            product.setLink(itemDto.getLink());
            product.setImage(itemDto.getImage());
            product.setLprice(itemDto.getLprice());

            int myPrice = getRandomNumber(MIN_MY_PRICE, itemDto.getLprice() + 10000);
            product.setMyprice(myPrice);

            productList.add(product);
        }

        productRepository.saveAll(productList);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}