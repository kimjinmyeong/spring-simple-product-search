package io.github.kimjinmyeong.myselectshop.naver.controller;

import io.github.kimjinmyeong.myselectshop.naver.dto.FolderRequestDto;
import io.github.kimjinmyeong.myselectshop.naver.dto.FolderResponseDto;
import io.github.kimjinmyeong.myselectshop.naver.security.UserDetailsImpl;
import io.github.kimjinmyeong.myselectshop.naver.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/folders")
    public void addFolders(@RequestBody FolderRequestDto folderRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<String> folderNames = folderRequestDto.getFolderNames();
        folderService.addFolders(folderNames, userDetails.getUser());
    }

    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return folderService.getFolders(userDetails.getUser());
    }

}
