package pl.spring.demo.dao.impl;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;

import javax.persistence.TypedQuery;

import org.springframework.util.StringUtils;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.impl.JPAQuery;

import java.util.List;

public class BookDaoImpl extends AbstractDao<BookEntity, Long>implements BookDao {

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		TypedQuery<BookEntity> query = entityManager.createQuery(
				"select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')",
				BookEntity.class);
		query.setParameter("title", title);
		return query.getResultList();
	}

	@Override
	public List<BookEntity> findBookByCriteria(BookSearchCriteria criteria) {
		final QBookEntity bookEntity = QBookEntity.bookEntity;

		final JPAQuery query = new JPAQuery(entityManager, HQLTemplates.DEFAULT);
		query.from(bookEntity);

		if (criteria != null) {
			final BooleanBuilder booleanBuilder = new BooleanBuilder();

			if (!StringUtils.isEmpty(criteria.getTitle())) {
				final String title = criteria.getTitle();
				booleanBuilder.and(bookEntity.title.startsWithIgnoreCase(title));
			}
			if (!StringUtils.isEmpty(criteria.getAuthor())) {
				final String author = criteria.getAuthor();
				booleanBuilder.and(bookEntity.authors.any().firstName.startsWithIgnoreCase(author)
						.or(bookEntity.authors.any().lastName.startsWithIgnoreCase(author)));
			}
			if (!StringUtils.isEmpty(criteria.getLibraryName())) {
				final String libName = criteria.getLibraryName();
				booleanBuilder.and(bookEntity.library.name.startsWithIgnoreCase(libName));
			}

			query.where(booleanBuilder);
		}
		return query.listResults(bookEntity).getResults();
	}
}
