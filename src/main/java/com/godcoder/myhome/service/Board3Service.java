package com.godcoder.myhome.service;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board3;
import com.godcoder.myhome.model.User;
import com.godcoder.myhome.repository.Board3Repository;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.UUID;

@Service
public class Board3Service {

    @Autowired
    private Board3Repository board3Repository;

    @Autowired
    private UserRepository userRepository;


    public void save(String username, Board3 board3, MultipartFile file) throws  Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);
            board3.setFilename(fileName);
            board3.setFilepath("/static/files/"+fileName);
            User user= userRepository.findByUsername(username);
            board3.setUser3(user);
            board3Repository.save(board3);

        }


    // 특정 게시글 불러오기
    public Board3 boardView(Long id) {

        return board3Repository.findById(id).get();
    }

    public void boardDelete(Long id) {
        board3Repository.deleteById(id);
    }
    @Transactional
    public void updateLikes(Long id) {
        board3Repository.updateLikes(id);
    }

    @Transactional
    public void updateHate(Long id) {
        board3Repository.updateHate(id);
    }
}
