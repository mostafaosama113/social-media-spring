package com.app.social.social.app.payload;

import lombok.Data;
import lombok.Getter;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;

}
