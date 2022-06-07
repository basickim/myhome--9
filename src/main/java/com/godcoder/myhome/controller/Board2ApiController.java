package com.godcoder.myhome.controller;
import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board2;
import com.godcoder.myhome.repository.Board2Repository;
import com.godcoder.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class Board2ApiController {

    @Autowired
    private Board2Repository repository;

    @GetMapping("/boards2")
    List<Board2> all(@RequestParam(required = false, defaultValue = "") String title,
                     @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        } else {
            return repository.findByTitleOrContent(title, content);
        }
    }

    @PostMapping("/boards2")
    Board2 newBoard(@RequestBody Board2 newBoard2) {
        return repository.save(newBoard2);
    }

    // Single item

    @GetMapping("/boards2/{id}")
    Board2 one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/boards2/{id}")
    Board2 replaceBoard(@RequestBody Board2 newBoard2, @PathVariable Long id) {

        return repository.findById(id)
                .map(board2 -> {
                    board2.setTitle(newBoard2.getTitle());
                    board2.setContent(newBoard2.getContent());
                    return repository.save(board2);
                })
                .orElseGet(() -> {
                    newBoard2.setId(id);
                    return repository.save(newBoard2);
                });
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/boards2/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}