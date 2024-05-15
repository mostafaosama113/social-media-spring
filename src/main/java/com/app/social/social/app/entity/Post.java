package com.app.social.social.app.entity;

import com.app.social.social.app.payload.PostDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.app.social.social.app.utils.AppConstants.POST_TABLE_NAME;

@Entity(name = POST_TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = POST_TABLE_NAME,
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
    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , orphanRemoval = true)
    //@JsonBackReference
    private List<Comment> comments = new ArrayList<>();
    public PostDto toDto(){
        return new PostDto(id, title, content , comments);
    }

}
