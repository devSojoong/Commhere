package com.example.commhere.controller;

import com.example.commhere.dto.BoardRequestDTO;
import com.example.commhere.dto.BoardResponseDTO;
import com.example.commhere.dto.UserDTO;
import com.example.commhere.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Board", description = "게시판 서비스를 담당합니다.")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시글 작성", description = "새로운 게시글을 작성합니다.")
    @PostMapping("/board/insert")
    public String save(@RequestBody BoardRequestDTO boardRequestDTO, Authentication authentication){
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return boardService.save(boardRequestDTO, userDTO.getUserId());
    }

    @Operation(summary = "게시글 수정", description = "기존 게시글을 수정합니다.")
    @PatchMapping("/board/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody BoardRequestDTO boardRequestDTO,
                        Authentication authentication) {
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return boardService.update(id, boardRequestDTO, userDTO.getUserId());
    }

    @Operation(summary = "게시글 삭제", description = "기존 게시글을 삭제합니다.")
    @DeleteMapping("/board/{id}")
    public String delete(@PathVariable Long id, Authentication authentication){
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return boardService.delete(id, userDTO.getUserId());
    }

    @Operation(summary = "게시글 조회", description = "상세 게시글을 조회합니다.")
    @GetMapping("/board/{id}")
    public BoardResponseDTO select(@PathVariable Long id){
        return boardService.select(id);
    }

    /**
     * 게시글 최신 순으로 가져오기
     * ?order = -createdDate : 내림차순 desc created_date
     * <p>
     * 게시글 오래된 순으로 가져오기
     * ?order = createdDate : 오름차순 asc create_date
     */

    @Operation(summary = "전체 게시글 조회", description = "전체 게시글을 최신 순 기준으로 조회합니다.")
    @GetMapping("/board/list")
    public List<BoardResponseDTO> selecctByCreatedDate(@RequestParam(required = false, defaultValue = "-createdDate") @Nullable String order,
                                                       @RequestParam(required = false, defaultValue = "1") int page) {
        if (order.equals("-createdDate") || order.equals("")) {
            return boardService.findByCreatedDateDesc(page - 1);
        }
        return boardService.findByCreatedDateAsc(page - 1);
    }

}
