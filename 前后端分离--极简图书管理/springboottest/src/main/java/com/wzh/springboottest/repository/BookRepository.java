package com.wzh.springboottest.repository;

import com.wzh.springboottest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
