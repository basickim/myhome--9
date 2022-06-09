package com.godcoder.myhome.controller;

import com.godcoder.myhome.dto.CommentDto;
import   com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board2;
import com.godcoder.myhome.model.User;
import com.godcoder.myhome.repository.Board2Repository;
import com.godcoder.myhome.repository.Board3Repository;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.repository.UserRepository;
import com.godcoder.myhome.service.BoardService;
import com.godcoder.myhome.service.CommentService;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private CommentService commentService;

    @GetMapping("/category1")
    public String category1()
    {
        return"/fragments/category1";
    }

    // 최신순
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 8) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board> BOARD = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board> boards1 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards1",boards1);
        return "board/list";
    }

    //  좋아요 많은순
    @GetMapping("/list2")
    public String list2(Model model, @PageableDefault(size = 8) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board> BOARD = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"likes"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board> boards2 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board/list2";
    }
    // 조회수 많은순
    @GetMapping("/list3")
    public String list3(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board> BOARD = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"view"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board> boards2 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board/list3";
    }

    @GetMapping("/list4")
    public String list4(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        List<Board> BOARD = boardRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), BOARD.size());
        final Page<Board> boards2 = new PageImpl<>(BOARD.subList(start,end),pageable,BOARD.size());


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("boards2",boards2);
        return "board/list4";
    }

    @GetMapping("/search")
    public String search1(Model model, @PageableDefault(size = 8) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/search";
    }




    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @GetMapping("/view")
    public String view(Model model, Long id, Authentication authentication) {
        boardService.updateView(id);

        model.addAttribute("board", boardService.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        List<CommentDto> commentDtos = commentService.comments(id);
        model.addAttribute("commentDtos", commentDtos);
        return "board/view";
    }
    @GetMapping("/likes")
    public String likes(Model model, Long id, Authentication authentication) {
        boardService.updateLikes(id);

        model.addAttribute("board", boardService.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        return "board/view";
    }

    @GetMapping("/hate")
    public String hate(Model model, Long id, Authentication authentication) {
        boardService.updateHate(id);

        model.addAttribute("board", boardService.boardView(id));
        String username = authentication.getName();
        model.addAttribute("boardUser",username);
        return "board/view";
    }


    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication, MultipartFile file)
     throws Exception {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        String username = authentication.getName();
        boardService.save(username, board, file);

        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String delete(Long id) {

        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String Modify(@PathVariable("id") Long id) {
        return "board/modify";
    }

}
