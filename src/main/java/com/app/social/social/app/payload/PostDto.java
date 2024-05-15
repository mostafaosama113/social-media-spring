package com.app.social.social.app.payload;

import com.app.social.social.app.entity.Comment;
import com.app.social.social.app.entity.Post;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @Null
    private Long id;
    @NotNull
    @Size(min = 5 , message = "size must be greater than or equal 5")
    private String title;
    @NotNull
    @Size(min = 5 , message = "size must be greater than or equal 5")
    private String content;

    private List<Comment> comments;
    public Post toPost(){
        return new Post(this.id , this.title, this.content , comments);
    }
}
