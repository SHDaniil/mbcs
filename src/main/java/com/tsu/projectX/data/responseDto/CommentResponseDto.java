package com.tsu.projectX.data.responseDto;

import com.tsu.projectX.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CommentResponseDto {

    private UUID id;
    private String user;
    private String text;

    public static CommentResponseDto fromComment(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getUser().getNickname(),
                comment.getText());
    }

    public static List<CommentResponseDto> fromListComment(List<Comment> comments) {
        List<CommentResponseDto> commentsResponse = new ArrayList<>();
        comments.forEach(comment -> commentsResponse.add(fromComment(comment)));
        return commentsResponse;
    }
}
