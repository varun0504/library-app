package com.needleinnovision.libraryapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.needleinnovision.libraryapp.bo.BookBo;
import com.needleinnovision.libraryapp.response.BookFinderResponse;

@Service
public interface BookService {
	List<BookFinderResponse> searchBook(BookBo bookBo) throws Exception;
}
