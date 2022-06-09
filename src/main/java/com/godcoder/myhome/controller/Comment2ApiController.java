package com.godcoder.myhome.controller;

import com.godcoder.myhome.dto.CommentDto;
import com.godcoder.myhome.dto.CommentDto2;
import com.godcoder.myhome.service.Comment2Service;
import com.godcoder.myhome.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Comment2ApiController {
    @Autowired
    private Comment2Service commentService;



    // 댓글 목록 조회
    @GetMapping("/api2/boards/{boardId}/comments")
    public ResponseEntity<List<CommentDto2>> comments(@PathVariable Long boardId) {
        // 서비스에게 위임
        List<CommentDto2> dtos = commentService.comments(boardId);

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
    @PostMapping("/api2/boards/{boardId}/comments")
    public ResponseEntity<CommentDto2> create(@PathVariable Long boardId,
                                             @RequestBody CommentDto2 dto) {
        // 서비스에게 위임
       CommentDto2 createDto = commentService.create(boardId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }


    // 댓글 수정
    @PatchMapping("/api2/comments/{id}")
    public ResponseEntity<CommentDto2> update(@PathVariable Long id,
                                             @RequestBody CommentDto2 dto) {
        // 서비스에게 위임
        CommentDto2 updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    // 댓글 삭제
    @DeleteMapping("/api2/comments/{id}")
    public ResponseEntity<CommentDto2> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentDto2 deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
