package com.yodaplus.todos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yodaplus.todos.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	@Override
	Optional<Tag> findById(Long id);

	@Query("select t from Tag t where t.tagname=?1")
	public Tag findTag(@Param("tagname")String tagname);
}
