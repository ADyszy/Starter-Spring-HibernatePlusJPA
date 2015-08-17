package pl.spring.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void testShouldFindBookById() {
        // given
        final long bookId = 1;
        // when
        BookEntity bookEntity = bookDao.findOne(bookId);
        // then
        assertNotNull(bookEntity);
        assertEquals("Pierwsza książka", bookEntity.getTitle());
    }

    @Test
    public void testShouldFindBooksByTitle() {
        // given
        final String bookTitle = "p";
        // when
        List<BookEntity> booksEntity = bookDao.findBookByTitle(bookTitle);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
    
    
    @Test
	public void testShouldFindBookWithOnlyTitleCriteria() {
		// given
		BookSearchCriteria criteria = new BookSearchCriteria.CriteriaBuilder()
				.title("Pierwsza")
				.build();
		// when
		List<BookEntity> result = bookDao.findBookByCriteria(criteria);
		
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
		List<BookEntity> result = bookDao.findBookByCriteria(criteria);
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
		List<BookEntity> result = bookDao.findBookByCriteria(criteria);
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
		List<BookEntity> result = bookDao.findBookByCriteria(criteria);
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
		List<BookEntity> result = bookDao.findBookByCriteria(criteria);
		for (BookEntity bookEntity : result) {
			System.out.println(bookEntity.getTitle());
		}
		// then
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertTrue(result.size() == 1);
	}

    
}
