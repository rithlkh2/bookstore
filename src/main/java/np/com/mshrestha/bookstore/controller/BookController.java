package np.com.mshrestha.bookstore.controller;

import np.com.mshrestha.bookstore.model.Book;
import np.com.mshrestha.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = { "/", "/listBooks" })
	public String listBooks(Map<String, Object> map) {

		map.put("book", new Book());
		map.put("bookList", bookService.listBooks());

		return "/book/listBooks";
	}

	@RequestMapping("/get/{bookId}")
	public String getBook(@PathVariable Long bookId, Map<String, Object> map) {

		Book book = bookService.getBook(bookId);

		map.put("book", book);

		return "/book/bookForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute("book") Book book,
			BindingResult result) {

		bookService.saveBook(book);

		/*
		 * Note that there is no slash "/" right after "redirect:" So, it
		 * redirects to the path relative to the current path
		 */
		return "redirect:listBooks";
	}

	@RequestMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable("bookId") Long id) {

		bookService.deleteBook(id);

		/*
		 * redirects to the path relative to the current path
		 */
		// return "redirect:../listBooks";

		/*
		 * Note that there is the slash "/" right after "redirect:" So, it
		 * redirects to the path relative to the project root path
		 */
		return "redirect:/listBooks";
	}
	@RequestMapping(value = "/getListBooks",method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllStudents(){
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		List<Book> studentList =bookService.listBooks();
		return new ResponseEntity<List<Book>>(studentList,httpHeaders, HttpStatus.OK);
	}
	@RequestMapping(value="/getListBooks/{id}", method = RequestMethod.GET )
	public ResponseEntity<Book> getPersonById(@PathVariable("id") Long id) {
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Book book = bookService.getBook(id);
		return new ResponseEntity<Book>(book,httpHeaders, HttpStatus.OK);
	}
	/*
	Add
	 */
	@RequestMapping(value= "/getListBooks", method = RequestMethod.POST)
	public ResponseEntity<Void> addBooks(@RequestBody Book book, UriComponentsBuilder builder) {
		bookService.saveBook(book);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/getListBooks/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	/*
	Delete
	 */
	@RequestMapping(value="/getListBooks/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBooks(@PathVariable("id") Long id) {
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		bookService.deleteBook(id);
		return new ResponseEntity<Void>(httpHeaders,HttpStatus.NO_CONTENT);
	}
	/*
	Update
	 */
	@RequestMapping(value="/getListBooks/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Void> UpdateBook(@RequestBody Book book, UriComponentsBuilder builder) {
		bookService.saveBook(book);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/getListBooks/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/home")
	public String getHomePage(){
		return "/book/home";
	}
}
