package com.tweetapp.repository;

import com.tweetapp.model.ReplyEntity;
import com.tweetapp.model.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
//	@Query("INSERT INTO REPLIES r (tweet)")
//	ReplyEntity saveReplyByTweetId(long tweetId, String tweet);

//	@Query("SELECT t FROM TweetEntity t WHERE t.email = ?1 and t.id = ?2")
}
