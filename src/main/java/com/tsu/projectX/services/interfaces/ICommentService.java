package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.data.requestDto.CommentRequestDto;

public interface ICommentService {

    boolean addComment(CommentRequestDto commentRequestDto);
}
