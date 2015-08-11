package pl.spring.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARY")
public class LibraryEntity {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private Set<BookEntity> books;
}
