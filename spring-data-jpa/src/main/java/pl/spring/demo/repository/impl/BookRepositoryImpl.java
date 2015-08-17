package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.repository.CustomBookRepository;

@Repository
public class BookRepositoryImpl implements CustomBookRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	private void addTitleFilter(BooleanBuilder booleanBuilder, BookSearchCriteria criteria, QBookEntity bookEntity) {
		if ( !StringUtils.isEmpty(criteria.getTitle()) ) {
			final String title = criteria.getTitle();
			booleanBuilder.and(bookEntity.title.startsWithIgnoreCase(title));
		}
	}
	
	private void addAuthorFilter(BooleanBuilder booleanBuilder, BookSearchCriteria criteria, QBookEntity bookEntity) {
		if ( !StringUtils.isEmpty(criteria.getAuthor()) ) {
			final String author = criteria.getAuthor();
			booleanBuilder.and( bookEntity.authors.any().firstName.startsWithIgnoreCase(author)
					.or(bookEntity.authors.any().lastName.startsWithIgnoreCase(author)));
		}
	}
	
	private void addLibraryNameFilter(BooleanBuilder booleanBuilder, BookSearchCriteria criteria, QBookEntity bookEntity) {
		if ( !StringUtils.isEmpty(criteria.getLibraryName()) ) {
			final String libName = criteria.getLibraryName();
			booleanBuilder.and(bookEntity.library.name.startsWithIgnoreCase(libName));
		}
	}
	
	@Override // TODO[qstn]: to dao, service or here, in repo ?
	public List<BookEntity> findBookByCriteria(BookSearchCriteria criteria) {		
		final QBookEntity bookEntity = QBookEntity.bookEntity;
		
		final JPAQuery query = new JPAQuery(entityManager, HQLTemplates.DEFAULT);
		query.from(bookEntity);
		
		if (criteria != null ) {	
			final BooleanBuilder booleanBuilder = new BooleanBuilder();
			
			addTitleFilter(booleanBuilder, criteria, bookEntity);
			addAuthorFilter(booleanBuilder, criteria, bookEntity);
			addLibraryNameFilter(booleanBuilder, criteria, bookEntity);
			
			query.where(booleanBuilder);
		}
		
		return query.listResults(bookEntity).getResults();
	}
	
}
