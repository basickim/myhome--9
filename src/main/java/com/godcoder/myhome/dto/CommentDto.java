package com.godcoder.myhome.dto;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Comment;
import com.godcoder.myhome.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;

    private Long boardid;

    private String nickname;

    private String content;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
            comment.getId(),
            comment.getBoard().getId(),
            comment.getNickname(),
                comment.getContent()
        );
    }
}
