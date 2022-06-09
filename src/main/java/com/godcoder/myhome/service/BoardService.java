package com.godcoder.myhome.service;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.User;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;



    public void save(String username, Board board, MultipartFile file) throws  Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);
            board.setFilename(fileName);
            board.setFilepath("/static/files/"+fileName);
            User user= userRepository.findByUsername(username);
            board.setUser(user);
            boardRepository.save(board);
        }


    // 특정 게시글 불러오기
    public Board boardView(Long id) {

        return boardRepository.findById(id).get();
    }

    public void boardDelete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateView(Long id) {
         boardRepository.updateView(id);
    }

    @Transactional
    public void updateLikes(Long id) {
        boardRepository.updateLikes(id);
    }

    @Transactional
    public void updateHate(Long id) {
        boardRepository.updateHate(id);
    }

}
