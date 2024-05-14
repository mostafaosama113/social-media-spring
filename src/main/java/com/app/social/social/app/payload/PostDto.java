package com.app.social.social.app.payload;

import com.app.social.social.app.entity.Post;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    @NotNull
    @Size(min = 5 , message = "size must be greater than or equal 5")
    private String title;
    @NotNull
    @Size(min = 5 , message = "size must be greater than or equal 5")
    private String content;

    public Post toPost(){
        return new Post(this.id , this.title, this.content , null);
    }
}
