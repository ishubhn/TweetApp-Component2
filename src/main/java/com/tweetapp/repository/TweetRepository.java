package com.tweetapp.repository;

import com.tweetapp.model.TweetEntity;
import com.tweetapp.model.dto.response.TweetResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
	Optional<Object> findById(String email);

	@Query("SELECT t FROM TweetEntity t WHERE t.email = ?1 and t.id = ?2")
	TweetEntity findByEmailAndId(String email, long id);
}
