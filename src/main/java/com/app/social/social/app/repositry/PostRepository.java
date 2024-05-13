package com.app.social.social.app.repositry;

import com.app.social.social.app.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.social.social.app.utils.AppConstants.POST_TABLE_NAME;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> getPostByTitle(String title);

    @Query(value = "SELECT column_name FROM information_schema.columns WHERE table_name = " + POST_TABLE_NAME, nativeQuery = true)
    List<String> getAllColumns();


}