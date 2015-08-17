package pl.spring.demo.repository.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:pl/spring/demo/repository/BookRepositoryTest-context.xml")
public class BookRepositoryImplTest {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testShouldFindBookWithOnlyTitleCriteria() {
		// given
		BookSearchCriteria criteria = new BookSearchCriteria.CriteriaBuilder()
				.title("Pierwsza")
				.build();
		// when
		List<BookEntity> result = bookRepository.findBookByCriteria(criteria);
		
		// then
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertTrue(result.size() == 1);
	}

	@Test
	public void testShouldFindBookWithOnlyAuthorCriteria() {
		// given
		BookSearchCriteria criteria = new BookSearchCriteria.CriteriaBuilder()
				.author("Jan")
				.build();
		// when
		List<BookEntity> result = bookRepository.findBookByCriteria(criteria);
		for (BookEntity bookEntity : result) {
			System.out.println(bookEntity.getTitle());
		}
		// then
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	@Test
	public void testShouldFindBookWithOnlyLibraryNameCriteria() {
		// given
		BookSearchCriteria criteria = new BookSearchCriteria.CriteriaBuilder()
				.libraryName("bib")
				.build();
		// when
		List<BookEntity> result = bookRepository.findBookByCriteria(criteria);
		for (BookEntity bookEntity : result) {
			System.out.println(bookEntity.getTitle());
		}
		// then
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	@Test
	public void testShouldFindAllBooksBecauseOfNoCriteria() {
		// given
		BookSearchCriteria criteria = new BookSearchCriteria.CriteriaBuilder()
				.build();
		// when
		List<BookEntity> result = bookRepository.findBookByCriteria(criteria);
		for (BookEntity bookEntity : result) {
			System.out.println(bookEntity.getTitle());
		}
		// then
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	@Test
	public void testShouldFindParticularBooko() {
		// given
		BookSearchCriteria criteria = new BookSearchCriteria.CriteriaBuilder()
				.author("Jan")
				.title("Pierwsza")
				.libraryName("Biblioteka Miejska")
				.build();
		// when
		List<BookEntity> result = bookRepository.findBookByCriteria(criteria);
		for (BookEntity bookEntity : result) {
			System.out.println(bookEntity.getTitle());
		}
		// then
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertTrue(result.size() == 1);
	}

}
