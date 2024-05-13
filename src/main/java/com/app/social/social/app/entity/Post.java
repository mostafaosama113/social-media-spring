package com.app.social.social.app.entity;

import com.app.social.social.app.payload.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static com.app.social.social.app.utils.AppConstants.POST_TABLE_NAME;

@Entity(name = POST_TABLE_NAME)
@Data
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
    private Set<Comment> comments = new HashSet<>();
    public PostDto toDto(){
        return new PostDto(id, title, content);
    }

}
