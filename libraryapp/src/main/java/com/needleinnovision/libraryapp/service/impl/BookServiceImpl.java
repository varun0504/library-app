package com.needleinnovision.libraryapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.needleinnovision.libraryapp.bo.BookBo;
import com.needleinnovision.libraryapp.entities.BooksInventory;
import com.needleinnovision.libraryapp.exception.ExceptionUtil;
import com.needleinnovision.libraryapp.repository.BookRepository;
import com.needleinnovision.libraryapp.response.BookFinderResponse;
import com.needleinnovision.libraryapp.service.BookService;

@Service
@Transactional(rollbackOn = Throwable.class)
public class BookServiceImpl implements BookService{
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<BookFinderResponse> searchBook(BookBo bookBo) throws Exception{
		logger.info("searchBook: started");
		List<BooksInventory> list;
		List<BookFinderResponse> response = null;
		try {
			String authorName = bookBo.getAuthorName();
			String bookName = bookBo.getBookName();
			if( (bookName == null || bookName.trim().equals("")) && 
					(authorName == null || authorName.trim().equals(""))) {
				list = bookRepository.findAll();
			}else {
				list = bookRepository.findByBookNameOrAuthorName(bookBo.getBookName(), bookBo.getAuthorName());
			}
			response = modelMapper.map(list, new TypeToken<List<BookFinderResponse>>() {}.getType());
		}catch(Exception ex) {
			logger.error("Unexpected exception occured at searchBook service", ex);
			ExceptionUtil.handleException(ex);
		}
		logger.info("searchBook: started");
		return response;
	}
}
