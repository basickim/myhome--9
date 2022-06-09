package com.godcoder.myhome.controller;

import com.godcoder.myhome.dto.CommentDto;
import com.godcoder.myhome.dto.CommentDto3;
import com.godcoder.myhome.service.Comment3Service;
import com.godcoder.myhome.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Comment3ApiController {
    @Autowired
    private Comment3Service commentService;



    // 댓글 목록 조회
    @GetMapping("/api3/boards/{boardId}/comments")
    public ResponseEntity<List<CommentDto3>> comments(@PathVariable Long boardId) {

        // 서비스에게 위임
        List<CommentDto3> dtos = commentService.comments(boardId);

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
    @PostMapping("/api3/boards/{boardId}/comments")
    public ResponseEntity<CommentDto3> create(@PathVariable Long boardId,
                                             @RequestBody CommentDto3 dto) {
        // 서비스에게 위임
       CommentDto3 createDto = commentService.create(boardId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }


    // 댓글 수정
    @PatchMapping("/api3/comments/{id}")
    public ResponseEntity<CommentDto3> update(@PathVariable Long id,
                                             @RequestBody CommentDto3 dto) {
        // 서비스에게 위임
        CommentDto3 updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    // 댓글 삭제
    @DeleteMapping("/api3/comments/{id}")
    public ResponseEntity<CommentDto3> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentDto3 deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
