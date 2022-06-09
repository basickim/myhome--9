package com.godcoder.myhome.service;

import com.godcoder.myhome.dto.CommentDto;
import com.godcoder.myhome.dto.CommentDto2;
import com.godcoder.myhome.dto.CommentDto3;
import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board2;
import com.godcoder.myhome.model.Comment;
import com.godcoder.myhome.model.Comment2;
import com.godcoder.myhome.repository.Board2Repository;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.repository.Comment2Repository;
import com.godcoder.myhome.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Comment2Service {

   @Autowired
   private Comment2Repository commentRepository;

   @Autowired
   private Board2Repository boardRepository;

   public List<CommentDto2> comments(Long boardId) {




      return commentRepository.findByBoardIdNativeQuery(boardId)
              .stream()
              .map(comment -> CommentDto2.createCommentDto(comment))
              .collect(Collectors.toList());

   }

    @Transactional
    public CommentDto2 create(Long boardId, CommentDto2 dto) {
        // 게시글 조회 및 예외 처리
       Board2 board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

       // 댓글 엔티티 생성
       Comment2 comment = Comment2.createComment(dto, board);

        // 댓글 엔티티를 DB로 저장
      Comment2 created = commentRepository.save(comment);

        // DTO로 변경하여 반환

        return CommentDto2.createCommentDto(created);
    }

    @Transactional
    public CommentDto2 update(Long id, CommentDto2 dto) {
        // 댓글 조회 및 예외 발생
        Comment2 target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);
        // DB로 갱신
        Comment2 updated = commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto2.createCommentDto(updated);

    }

    @Transactional
    public CommentDto2 delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Comment2 target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        // 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 반환
        return CommentDto2.createCommentDto(target);
    }
}
