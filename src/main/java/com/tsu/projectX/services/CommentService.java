package com.tsu.projectX.services;

import com.tsu.projectX.data.requestDto.CommentRequestDto;
import com.tsu.projectX.entities.Comment;
import com.tsu.projectX.entities.Team;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.ICommentRepository;
import com.tsu.projectX.repositories.ITeamRepository;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITeamRepository teamRepository;
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public boolean addComment(CommentRequestDto commentRequestDto) {
        Optional<User> optionalUser = userRepository.findById(commentRequestDto.getUserId());
        Optional<Team> optionalTeam = teamRepository.findById(commentRequestDto.getTeamId());
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        Team team = optionalTeam.get();
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setTeam(team);
        comment.setText(commentRequestDto.getText());
        commentRepository.save(comment);
        return true;
    }
}
