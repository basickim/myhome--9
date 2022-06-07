package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board2;
import com.godcoder.myhome.repository.Board2Repository;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.service.Board2Service;
import com.godcoder.myhome.service.BoardService;
import com.godcoder.myhome.validator.Board2Validator;
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
@RequestMapping("/board2")
public class Board2Controller {

    @Autowired
    private Board2Repository board2Repository;

    @Autowired
    private Board2Service board2Service;

    @Autowired
    private Board2Validator board2Validator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 8) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board2> boards2 = board2Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards2.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards2.getTotalPages(), boards2.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards2", boards2);
        return "board2/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board2", new Board2());
        } else {
            Board2 board2 = board2Repository.findById(id).orElse(null);
            model.addAttribute("board2", board2);
        }
        return "board2/form";
    }

    @GetMapping("/view")
    public String view(Model model, Long id, Authentication authentication) {
        model.addAttribute("board2", board2Service.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        return "board2/view";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board2 board2, BindingResult bindingResult, Authentication authentication, MultipartFile file)
     throws Exception {
        board2Validator.validate(board2, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board2/form";
        }

        String username = authentication.getName();
        board2Service.save(username, board2, file);

        return "redirect:/board2/list";
    }

    @GetMapping("/delete")
    public String delete(Long id) {

        board2Service.boardDelete(id);
        return "redirect:/board2/list";
    }

    @GetMapping("/board2/modify/{id}")
    public String Modify(@PathVariable("id") Long id) {
        return "board2/modify";
    }

}
