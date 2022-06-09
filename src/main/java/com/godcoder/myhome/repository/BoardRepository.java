package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String title);

    List<Board> findAll();

    List<Board> findByTitleOrContent(String title, String content);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    @Modifying
    @Query("update Board p set p.view = p.view + 1 where p.id = :id")
    void updateView(Long id);

    @Modifying
    @Query("update Board p set p.likes = p.likes + 1 where p.id = :id")
    void updateLikes(Long id);

    @Modifying
    @Query("update Board p set p.likes = p.likes - 1 where p.id = :id")
    void updateHate(Long id);

}
