package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.Comment;
import com.godcoder.myhome.model.Comment2;
import com.godcoder.myhome.model.Comment3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Comment3Repository extends JpaRepository<Comment3, Long> {
    //특정 게시글의 모든 댓그 조회

    @Query( value =
                    "SELECT * " +
                    "FROM comment3 " +
                    "WHERE board_id = :boardId", nativeQuery = true)
    List<Comment3> findByBoardIdNativeQuery(@Param("boardId") Long boardId);
    }

