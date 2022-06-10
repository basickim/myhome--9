package com.godcoder.myhome.service;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board2;
import com.godcoder.myhome.model.User;
import com.godcoder.myhome.repository.Board2Repository;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.UUID;

@Service
public class Board2Service {

    @Autowired
    private Board2Repository board2Repository;

    @Autowired
    private UserRepository userRepository;


    public void save(String username, Board2 board2, MultipartFile file) throws  Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);
            board2.setFilename(fileName);
            board2.setFilepath("/static/files/"+fileName);
            User user= userRepository.findByUsername(username);
            board2.setUser2(user);
            board2Repository.save(board2);

        }


    // 특정 게시글 불러오기
    public Board2 boardView(Long id) {

        return board2Repository.findById(id).get();
    }
    @Transactional
    public void updateView(Long id) {
        board2Repository.updateView(id);
    }

    public void boardDelete(Long id) {
        board2Repository.deleteById(id);
    }
    @Transactional
    public void updateLikes(Long id) {
        board2Repository.updateLikes(id);
    }

    @Transactional
    public void updateHate(Long id) {
        board2Repository.updateHate(id);
    }
}
