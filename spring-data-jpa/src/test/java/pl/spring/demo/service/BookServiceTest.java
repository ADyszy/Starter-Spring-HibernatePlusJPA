package pl.spring.demo.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

public class BookServiceTest {
	
	@InjectMocks
	BookServiceImpl bookService;
	
	@Mock
	BookMapper bookMapper;
	
	@Mock
	BookRepository bookRepository;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testShouldSearchBookByCriteria() {
		// given
		List<BookEntity> books = Arrays.asList(new BookEntity(1L,"title"));
		Mockito.when(bookRepository.findBookByCriteria(Mockito.any())).thenReturn(books);
		
		// when
		List<BookTo> found = bookService.findBookByCriteria(new BookSearchCriteria.CriteriaBuilder().build());
		
		// then
		assertNotNull(found);
		assertFalse(found.isEmpty());
	}

}
