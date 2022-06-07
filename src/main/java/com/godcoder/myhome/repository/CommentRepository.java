package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓그 조회

    @Query( value =
            "SELECT * " +
            "FROM comment " +
            "WHERE board_id = :boardId", nativeQuery = true
    )
    List<Comment> findByBoardIdNativeQuery(@Param("boardId") Long boardId);



}
