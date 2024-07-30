package io.github.kimjinmyeong.myselectshop.naver.controller;

import io.github.kimjinmyeong.myselectshop.naver.dto.ItemDto;
import io.github.kimjinmyeong.myselectshop.naver.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NaverApiController {

    private final NaverApiService naverApiService;

    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String query)  {
        return naverApiService.searchItems(query);
    }

}