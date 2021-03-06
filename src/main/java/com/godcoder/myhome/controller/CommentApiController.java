package com.godcoder.myhome.controller;

import com.godcoder.myhome.dto.CommentDto;
import com.godcoder.myhome.model.Comment;
import com.godcoder.myhome.model.User;
import com.godcoder.myhome.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;



    // 댓글 목록 조회
    @GetMapping("/api/boards/{boardId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long boardId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(boardId);

        // 결과 응답답
       return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

//    @PostMapping("/api/v1/board/{boardId}/reply")
//    public void save(@PathVariable Long boardId,
//                     @RequestBody Comment reply,
//                      User user) {
//        commentService.replySave(boardId, reply, user);
//    }

    // 댓글 생성
    @PostMapping("/api/boards/{boardId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long boardId,
                                             @RequestBody CommentDto dto) {
        // 서비스에게 위임
       CommentDto createDto = commentService.create(boardId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }


    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
