package com.app.social.social.app.entity;

import com.app.social.social.app.payload.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.Date;
import static com.app.social.social.app.utils.AppConstants.COMMENTS_TABLE_NAME;

@Entity
@Table(name = COMMENTS_TABLE_NAME)
@Data
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
    @JoinColumn(referencedColumnName = "id" , name = "post_id")
    private Post post;

    public CommentDto ToDto(){
        CommentDto commentDto = new CommentDto();
        commentDto.setBody(body);
        commentDto.setId(id);
        commentDto.setName(name);
        return commentDto;
    }

}
