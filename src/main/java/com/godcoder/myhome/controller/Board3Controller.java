package com.godcoder.myhome.controller;

import com.godcoder.myhome.dto.CommentDto;
import com.godcoder.myhome.dto.CommentDto3;
import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board2;
import com.godcoder.myhome.model.Board3;
import com.godcoder.myhome.repository.Board3Repository;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.service.Board3Service;
import com.godcoder.myhome.service.BoardService;
import com.godcoder.myhome.service.Comment3Service;
import com.godcoder.myhome.service.CommentService;
import com.godcoder.myhome.validator.Board3Validator;
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
@RequestMapping("/board3")
public class Board3Controller {
    @Autowired
    private Comment3Service commentService;

    @Autowired
    private Board3Repository board3Repository;

    @Autowired
    private Board3Service board3Service;

    @Autowired
    private Board3Validator board3Validator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 8) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board3> boards = board3Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board3> BOARD = board3Repository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board3> boards1 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards1",boards1);
        return "board3/list";
    }

    @GetMapping("/list2")
    public String list2(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board3> boards = board3Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board3> BOARD2 = board3Repository.findAll(Sort.by(Sort.Direction.DESC,"likes"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD2.size());
        final Page<Board3> boards2 = new PageImpl<>(BOARD2.subList(start,end),pageable,BOARD2.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board3/list2";
    }
    // 조회수 많은순
    @GetMapping("/list3")
    public String list3(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board3> boards = board3Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board3> BOARD = board3Repository.findAll(Sort.by(Sort.Direction.DESC,"view"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board3> boards2 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board3/list3";
    }

    @GetMapping("/list4")
    public String list4(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board3> boards = board3Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board3> BOARD = board3Repository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board3> boards2 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board3/list4";
    }

    @GetMapping("/search")
    public String search1(Model model, @PageableDefault(size = 8) Pageable pageable,
                          @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board3> boards = board3Repository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board3/search";
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
        model.addAttribute("board", board3Service.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        List<CommentDto3> commentDtos = commentService.comments(id);
        model.addAttribute("commentDtos", commentDtos);
        return "board3/view";
    }

    @GetMapping("/likes")
    public String likes(Model model, Long id, Authentication authentication) {
        board3Service.updateLikes(id);

        model.addAttribute("board", board3Service.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        return "board/view";
    }

    @GetMapping("/hate")
    public String hate(Model model, Long id, Authentication authentication) {
        board3Service.updateHate(id);

        model.addAttribute("board", board3Service.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        return "board/view";
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
