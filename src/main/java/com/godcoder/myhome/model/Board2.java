package com.godcoder.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;

    private String content;

    private String filename;

    private String filepath;

    private String url;

    private Long likes=0L;

    private Long view=0L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user2;




}
