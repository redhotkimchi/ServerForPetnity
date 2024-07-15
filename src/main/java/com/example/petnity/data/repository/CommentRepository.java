package com.example.petnity.data.repository;

import com.example.petnity.data.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPostEntityPostId(Long id);
    @Query(value="SELECT subcm.* FROM comment AS cm INNER JOIN comment AS subcm ON cm.comment_id = subcm.owner_comment_id WHERE cm.post_entity_post_id = :postId AND cm.comment_id = :commentId ORDER BY subcm.created_date asc", nativeQuery = true)
    List <CommentEntity> findSubComment(@Param("postId") Long postId, @Param("commentId") Long commentId);
    @Query(value="SELECT * FROM comment AS cm WHERE cm.sub_comment = 0 AND cm.post_entity_post_id = :postId ORDER BY cm.created_date asc", nativeQuery = true)
    List <CommentEntity> findMotherCommentEntity(@Param("postId") Long postId);

}
