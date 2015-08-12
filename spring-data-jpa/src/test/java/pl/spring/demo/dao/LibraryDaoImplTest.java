package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "LibraryDaoTest-context.xml")
public class LibraryDaoImplTest {
	
	@Autowired
	private LibraryDao libDao;
	
	@Test
	public void testShouldFindLibraryById() {
		//given
		final long libId = 1L;
		// when
		LibraryEntity libEntity = libDao.findOne(libId);
		// then
		assertNotNull(libEntity);
		assertEquals("Biblioteka Miejska", libEntity.getName());
	}
	
	@Test
	public void testShouldFindMultipleLibrariesByName() {
		// given
		final String libName = "Biblioteka";
		// when
		List<LibraryEntity> libEntities = libDao.findLibraryByName(libName);
		// then
		assertNotNull(libEntities);
		assertFalse(libEntities.isEmpty());
		assertEquals("Biblioteka Miejska", libEntities.get(0).getName());
	}
}
