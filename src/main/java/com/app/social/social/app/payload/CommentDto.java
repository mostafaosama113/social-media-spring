package com.app.social.social.app.payload;

import com.app.social.social.app.entity.Comment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    @Null
    private Long id;
    @NotNull
    @Size(min = 5 , message = "size must be greater than or equal 5")
    private String name;
    @NotNull
    @Size(min = 5 , message = "size must be greater than or equal 5")
    private String body;
    @Null
    private Date timeStamp;
    public Comment toEntity(){
        if(timeStamp == null){
            timeStamp = new Date();
        }
        Comment comment = new Comment();
        comment.setId(id);
        comment.setBody(body);
        comment.setTimeStamp(timeStamp);
        comment.setName(name);
        return comment;
    }
}
