package com.app.social.social.app.payload;

import com.app.social.social.app.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;

    public Post toPost(){
        return new Post(this.id , this.title, this.content);
    }
}
