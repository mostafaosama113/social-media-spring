package com.app.social.social.app.entity;

import com.app.social.social.app.payload.CommentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import static com.app.social.social.app.utils.AppConstants.COMMENTS_TABLE_NAME;

@Entity
@Table(name = COMMENTS_TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date timeStamp;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id" ,name = "post_id" , nullable = false)
    @JsonBackReference
    private Post post;
    public CommentDto toDto(){
        CommentDto commentDto = new CommentDto();
        commentDto.setBody(body);
        commentDto.setId(id);
        commentDto.setName(name);
        commentDto.setTimeStamp(timeStamp);
        return commentDto;
    }

}
