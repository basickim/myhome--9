package com.godcoder.myhome.controller;

import com.godcoder.myhome.dto.CommentDto;
import com.godcoder.myhome.dto.CommentDto2;
import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board2;
import com.godcoder.myhome.repository.Board2Repository;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.service.Board2Service;
import com.godcoder.myhome.service.BoardService;
import com.godcoder.myhome.service.Comment2Service;
import com.godcoder.myhome.service.CommentService;
import com.godcoder.myhome.validator.Board2Validator;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board2")
public class Board2Controller {

    @Autowired
    private Comment2Service commentService;
    @Autowired
    private Board2Repository board2Repository;

    @Autowired
    private Board2Service board2Service;

    @Autowired
    private Board2Validator board2Validator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 8) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board2> boards = board2Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board2> BOARD = board2Repository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board2> boards1 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards1",boards1);
        return "board2/list";
    }

    @GetMapping("/list2")
    public String list2(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board2> boards = board2Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board2> BOARD2 = board2Repository.findAll(Sort.by(Sort.Direction.DESC,"likes"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD2.size());
        final Page<Board2> boards2 = new PageImpl<>(BOARD2.subList(start,end),pageable,BOARD2.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board2/list2";
    }
    // 조회수 많은순
    @GetMapping("/list3")
    public String list3(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board2> boards = board2Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board2> BOARD = board2Repository.findAll(Sort.by(Sort.Direction.DESC,"view"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board2> boards2 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board2/list3";
    }

    @GetMapping("/list4")
    public String list4(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board2> boards = board2Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board2> BOARD = board2Repository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board2> boards2 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board2/list4";
    }

    @GetMapping("/search")
    public String search1(Model model, @PageableDefault(size = 8) Pageable pageable,
                          @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board2> boards = board2Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board2/search";
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
        board2Service.updateView(id);
        model.addAttribute("board", board2Service.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        List<CommentDto2> commentDtos = commentService.comments(id);
        model.addAttribute("commentDtos", commentDtos);
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

    @GetMapping("/likes")
    public String likes(Model model, Long id, Authentication authentication) {
        board2Service.updateLikes(id);

        model.addAttribute("board", board2Service.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        return "board2/view";
    }

    @GetMapping("/hate")
    public String hate(Model model, Long id, Authentication authentication) {
        board2Service.updateHate(id);

        model.addAttribute("board", board2Service.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        return "board2/view";
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
