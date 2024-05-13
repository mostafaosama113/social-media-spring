package com.app.social.social.app.payload;

import com.app.social.social.app.entity.Comment;
import com.app.social.social.app.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String body;

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
