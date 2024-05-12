package com.app.social.social.app.entity;

import com.app.social.social.app.payload.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "posts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        }
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    public PostDto toDto(){
        return new PostDto(id, title, content);
    }

}
