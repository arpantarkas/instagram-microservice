package com.arpan.instagram.dto;

import com.arpan.instagram.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentDto {
    private String message;
    private Long userId;
    private Optional<Long> parentId;
}
