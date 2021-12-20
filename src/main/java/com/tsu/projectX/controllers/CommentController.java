package com.tsu.projectX.controllers;

import com.tsu.projectX.data.requestDto.CommentRequestDto;
import com.tsu.projectX.services.interfaces.IAuthService;
import com.tsu.projectX.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.ROLE_ADMIN;

@Controller
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IAuthService authService;

    @PostMapping
    public ResponseEntity<?> addComment(
            @RequestHeader(name = "auth-token") UUID authToken,
            @RequestBody CommentRequestDto commentRequestDto) {
        boolean accessible = authService.checkAuthAndPermission(authToken);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean added = commentService.addComment(commentRequestDto);
        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
