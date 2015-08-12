package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@Test
	public void testShouldFindLibraryByName() {
		// given
		final long libId = 1L;
		// when
		LibraryEntity libraryEntity = libraryRepository.findOne(libId);
		// then
		assertNotNull(libraryEntity);
		assertEquals("Biblioteka Miejska", libraryEntity.getName());
	}
	
	@Test
	public void testShouldFindLMultipleLibrariesByName () {
		// given
		final String libName = "Biblioteka";
		// when
		List<LibraryEntity> libraryEntities = libraryRepository.findLibraryByName(libName);
		// then 
		assertNotNull(libraryEntities);
		assertFalse(libraryEntities.isEmpty());
		assertEquals("Biblioteka Miejska", libraryEntities.get(0).getName());
	}
	
}
