package com.needleinnovision.libraryapp.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.needleinnovision.libraryapp.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private BookService bookService;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public AppResponse searchBook(HttpServletResponse response) {
		return searchBook(new BookFinderRequest(), response);
	}*/
	
	/*@RequestMapping(value = "/search", method = RequestMethod.POST)
	public AppResponse searchBook(@RequestBody BookFinderRequest request,
			HttpServletResponse response){
		logger.info("searchBook: started");
		
		AppResponse appResponse = null;
		BookBo bookBo = null;
		try {
			bookBo = new BookBo();
			modelmapper.map(request, bookBo);
			appResponse = ResponseBuilder.getSuccessResponse(bookService.searchBook(bookBo));
		}
		catch (AppException ex) {
			appResponse = ResponseBuilder.getErrorResponse(ex.getErrorDetails(), response);
		}
		catch (Throwable ex) {
			logger.info("Error occured in registerUser", ex);
			
			ErrorDetails errorDetails = new ErrorDetails(5000,2,"error","Internal Server Error");
			appResponse = ResponseBuilder.getErrorResponse(errorDetails, response);
		}
		logger.info("registerUser: ended");
		return appResponse;
	}*/
}
