package com.godcoder.myhome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godcoder.myhome.model.Comment;
import com.godcoder.myhome.model.Comment3;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto3 {
    private Long id;
    @JsonProperty("board_id")
    private Long boardId;

    private String nickname;

    private String content;

    public static CommentDto3 createCommentDto(Comment3 comment) {
        return new CommentDto3(
            comment.getId(),
            comment.getBoard().getId(),
            comment.getNickname(),
                comment.getContent()
        );
    }
}
