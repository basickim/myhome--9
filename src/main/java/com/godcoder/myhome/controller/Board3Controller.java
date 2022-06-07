package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board3;
import com.godcoder.myhome.repository.Board3Repository;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.service.Board3Service;
import com.godcoder.myhome.service.BoardService;
import com.godcoder.myhome.validator.Board3Validator;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/board3")
public class Board3Controller {

    @Autowired
    private Board3Repository board3Repository;

    @Autowired
    private Board3Service board3Service;

    @Autowired
    private Board3Validator board3Validator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 8) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board3> boards3 = board3Repository.findAll(pageable);
        Page<Board3> boards3 = board3Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards3.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards3.getTotalPages(), boards3.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards3", boards3);
        return "board3/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board3", new Board3());
        } else {
            Board3 board3 = board3Repository.findById(id).orElse(null);
            model.addAttribute("board3", board3);
        }
        return "board3/form";
    }

    @GetMapping("/view")
    public String view(Model model, Long id, Authentication authentication) {
        model.addAttribute("board3", board3Service.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        return "board3/view";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board3 board3, BindingResult bindingResult, Authentication authentication, MultipartFile file)
     throws Exception {
        board3Validator.validate(board3, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board3/form";
        }

        String username = authentication.getName();
        board3Service.save(username, board3, file);

        return "redirect:/board3/list";
    }

    @GetMapping("/delete")
    public String delete(Long id) {

        board3Service.boardDelete(id);
        return "redirect:/board3/list";
    }

    @GetMapping("/board3/modify/{id}")
    public String Modify(@PathVariable("id") Long id) {
        return "board3/modify";
    }

}
