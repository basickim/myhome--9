package com.godcoder.myhome.model;


import lombok.*;

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

    @Column
    private String nickname;

    @Column
    private String content;


}
