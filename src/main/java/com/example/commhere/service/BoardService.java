package com.example.commhere.service;

import com.example.commhere.dto.BoardRequestDTO;
import com.example.commhere.dto.BoardResponseDTO;
import com.example.commhere.entity.Board;
import com.example.commhere.entity.User;
import com.example.commhere.repository.BoardRepository;
import com.example.commhere.repository.FavorRepository;
import com.example.commhere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public String save(BoardRequestDTO boardRequestDTO, String id){
        if(boardRequestDTO != null){
            if(!boardRequestDTO.getTitle().equals("") && !boardRequestDTO.getContent().equals("")){
                User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
                boardRepository.save(boardRequestDTO.toEntity(user));
                return "게시글 작성이 완료되었습니다.";
            } else {
                return "제목과 내용을 입력해주세요.";
            }
        }
        return "failed";
    }

    public String update(Long id, BoardRequestDTO boardRequestDTO, String userId) {
        if(boardRequestDTO != null){
            if(!boardRequestDTO.getTitle().equals("") && !boardRequestDTO.getContent().equals("")){
                User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
                Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
                if(board.getUser().getUserId().equals(userId)){
                    boardRepository.save(boardRequestDTO.toEntity(user));
                    return "수정이 완료되었습니다.";
                } else return "게시글 작성자만 수정이 가능합니다.";
            }
        }
        return "failed";
    }

    public String delete(Long id, String userId) {
        if(id != null){
                Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
                if(board != null){
                    if(board.getUser().getUserId().equals(userId)){
                        boardRepository.deleteById(id);
                        return "삭제가 완료되었습니다.";
                    } else return "게시글 작성자만 삭제 가능합니다.";
                }
        }
        return "failed";
    }

    public BoardResponseDTO select(Long id) {
        if(id != null){
            Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return new BoardResponseDTO(board);
        }
        return null;
    }
    public List<BoardResponseDTO> findByCreatedDateDesc(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdDate").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        List<Board> boardList = result.getContent();
        return boardList.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
    }

    public List<BoardResponseDTO> findByCreatedDateAsc(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdDate").ascending());
        Page<Board> result = boardRepository.findAll(pageable);
        List<Board> boardList = result.getContent();
        return boardList.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
    }
}
