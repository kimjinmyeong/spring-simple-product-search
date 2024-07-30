package io.github.kimjinmyeong.myselectshop.naver.service;

import io.github.kimjinmyeong.myselectshop.naver.dto.FolderResponseDto;
import io.github.kimjinmyeong.myselectshop.naver.entity.Folder;
import io.github.kimjinmyeong.myselectshop.naver.entity.User;
import io.github.kimjinmyeong.myselectshop.naver.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public void addFolders(List<String> folderNames, User user) {
        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            if (!isExistFolderName(folderName, existFolderList)) {
                Folder folder = new Folder(folderName, user);
                folderList.add(folder);
            } else {
                throw new IllegalArgumentException("Duplicate Folder name.");
            }
        }

        folderRepository.saveAll(folderList);
    }

    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        return folderList.stream().map(FolderResponseDto::new).toList();
    }

    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        for (Folder existFolder : existFolderList) {
            if (folderName.equals(existFolder.getName())) {
                return true;
            }
        }
        return false;
    }
}
