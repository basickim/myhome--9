package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Board2Repository extends JpaRepository<Board2, Long> {

    List<Board2> findByTitle(String title);
    List<Board2> findByTitleOrContent(String title, String content);

    Page<Board2> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    @Modifying
    @Query("update Board p set p.likes = p.likes + 1 where p.id = :id")
    void updateLikes(Long id);

    @Modifying
    @Query("update Board p set p.likes = p.likes - 1 where p.id = :id")
    void updateHate(Long id);

}
