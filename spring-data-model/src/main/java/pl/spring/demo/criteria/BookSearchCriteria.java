package pl.spring.demo.criteria;

import com.google.common.base.Objects;

public class BookSearchCriteria {
	//TODO: Check if author should be String.
	private String author = "";
	private String title = "";
	private String libraryName = "";
	
	private BookSearchCriteria(CriteriaBuilder cb){
		this.author = cb.getAuthor();
		this.title = cb.getTitle();
		this.libraryName = cb.getLibraryName();
	}
	
	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	//OverKill, fun.
	public static class CriteriaBuilder {
		
		private String author = "";
		private String title = "";
		private String libraryName = "";
				
		public CriteriaBuilder author(String author) {
			this.author = author;
			return this;
		}

		public CriteriaBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public CriteriaBuilder libraryName(String libName) {
			this.libraryName = libName;
			return this;
		}
		
		public BookSearchCriteria build(){
			return new BookSearchCriteria(this);
		}

		public String getAuthor() {
			return author;
		}

		public String getTitle() {
			return title;
		}

		public String getLibraryName() {
			return libraryName;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj == null || getClass() != obj.getClass()) return false;
		
		BookSearchCriteria bscObj = (BookSearchCriteria) obj;
		return 	( 
				 Objects.equal(author, bscObj.getAuthor()) &&
				 Objects.equal(title, bscObj.getTitle()) &&
				 Objects.equal(libraryName, bscObj.getLibraryName())
			    ) || obj == this;
	}
}
