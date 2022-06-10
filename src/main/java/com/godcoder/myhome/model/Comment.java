package com.godcoder.myhome.model;


import com.godcoder.myhome.dto.CommentDto;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


    private String nickname;

    @Column
    private String content;




    public static Comment createComment(CommentDto dto, Board board) {
        // 예외 처리
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (dto.getBoardId() != board.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");


        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                board,
                dto.getNickname(),
                dto.getContent()
        );
        //
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getContent() != null)
            this.content = dto.getContent();
    }
}
