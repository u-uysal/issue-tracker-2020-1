package com.kodstar.backend.controller;

import com.kodstar.backend.model.dto.Comment;
import com.kodstar.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentController {

        private final CommentService commentService;

        @GetMapping("comment/{id}")
        public ResponseEntity<Comment> getCommentById(@Valid @PathVariable Long id) {

            return ResponseEntity.ok(commentService.findById(id));
        }

        @GetMapping("issue/{issueId}/comments")
        public ResponseEntity<Collection<Comment>> getCommentsByIssueId(@Valid @PathVariable Long issueId) {

            return ResponseEntity.ok(commentService.findAllByIssueEntityId(issueId));
        }

        @PostMapping("comment")
        public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment) {

            return new ResponseEntity(commentService.saveComment(comment), HttpStatus.CREATED);
        }

        @PutMapping("comment/{id}")
        public ResponseEntity<Comment> updateComment(@Valid @PathVariable Long id, @RequestBody Comment comment) {

            return ResponseEntity.ok(commentService.updateComment(id, comment));

        }

        @DeleteMapping("comment/{id}")
        public ResponseEntity<Void> deleteComment(@Valid @PathVariable Long id) {

            commentService.deleteComment(id);

            return ResponseEntity.noContent().build();
        }
    }
