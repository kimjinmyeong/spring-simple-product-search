package io.github.kimjinmyeong.myselectshop.naver.repository;

import io.github.kimjinmyeong.myselectshop.naver.entity.Folder;
import io.github.kimjinmyeong.myselectshop.naver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    List<Folder> findAllByUser(User user);

    // SELECT * FROM folder WHERE user_id = ? and name in (?, ?, ? ...)
    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);

}
