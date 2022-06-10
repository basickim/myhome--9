package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Board3Repository extends JpaRepository<Board3, Long> {

    List<Board3> findByTitle(String title);
    List<Board3> findByTitleOrContent(String title, String content);

    Page<Board3> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    @Modifying
    @Query("update Board3 p set p.view = p.view + 1 where p.id = :id")
    void updateView(Long id);
    @Modifying
    @Query("update Board3 p set p.likes = p.likes + 1 where p.id = :id")
    void updateLikes(Long id);

    @Modifying
    @Query("update Board3 p set p.likes = p.likes - 1 where p.id = :id")
    void updateHate(Long id);

}
