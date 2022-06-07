package com.godcoder.myhome.controller;
import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board3;
import com.godcoder.myhome.repository.Board3Repository;
import com.godcoder.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class Board3ApiController {

    @Autowired
    private Board3Repository repository;

    @GetMapping("/boards3")
    List<Board3> all(@RequestParam(required = false, defaultValue = "") String title,
                     @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        } else {
            return repository.findByTitleOrContent(title, content);
        }
    }

    @PostMapping("/boards3")
    Board3 newBoard(@RequestBody Board3 newBoard3) {
        return repository.save(newBoard3);
    }

    // Single item

    @GetMapping("/boards3/{id}")
    Board3 one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/boards3/{id}")
    Board3 replaceBoard(@RequestBody Board3 newBoard3, @PathVariable Long id) {

        return repository.findById(id)
                .map(board3 -> {
                    board3.setTitle(newBoard3.getTitle());
                    board3.setContent(newBoard3.getContent());
                    return repository.save(board3);
                })
                .orElseGet(() -> {
                    newBoard3.setId(id);
                    return repository.save(newBoard3);
                });
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/boards3/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}