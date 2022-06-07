package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Board3Repository extends JpaRepository<Board3, Long> {

    List<Board3> findByTitle(String title);
    List<Board3> findByTitleOrContent(String title, String content);

    Page<Board3> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
