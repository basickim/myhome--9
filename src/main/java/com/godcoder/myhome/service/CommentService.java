package com.godcoder.myhome.service;

import com.godcoder.myhome.dto.CommentDto;
import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Comment;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

   @Autowired
   private CommentRepository commentRepository;

   @Autowired
   private BoardRepository boardRepository;

    public List<Comment> comments(Long boardId) {

        return commentRepository.findByBoardIdNativeQuery(boardId);

    }


}
