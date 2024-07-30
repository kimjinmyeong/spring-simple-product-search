package io.github.kimjinmyeong.myselectshop.naver.dto;

import io.github.kimjinmyeong.myselectshop.naver.entity.Folder;
import lombok.Getter;

@Getter
public class FolderResponseDto {

    private Long id;

    private String name;

    public FolderResponseDto(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
    }
}