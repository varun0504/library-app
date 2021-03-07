package com.needleinnovision.libraryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.needleinnovision.libraryapp.entities.BooksInventory;

@Repository
public interface BookRepository extends JpaRepository<BooksInventory, Long>{

	List<BooksInventory> findByBookNameOrAuthorName(String bookName, String authorName);
	
}
