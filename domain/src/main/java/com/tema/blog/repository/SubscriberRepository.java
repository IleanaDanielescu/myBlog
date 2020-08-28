package com.tema.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tema.blog.entity.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

	Optional<Subscriber> findByEmail(String email);
	
}
