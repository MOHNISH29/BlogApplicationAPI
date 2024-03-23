package com.example.demo.services;

import com.example.demo.payloads.CommentDto;

public interface CommentService {
CommentDto addComment(CommentDto commentdto , Integer postId);
void deleteComment(Integer commentId);
}
