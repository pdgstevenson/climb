package uk.co.tatari.climb.repository;


import static org.junit.Assert.assertEquals;

import org.hibernate.LazyInitializationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import uk.ac.tatari.climb.test.SequenceResetDbUtil;
import uk.co.tatari.climb.domain.Centre;
import uk.co.tatari.climb.domain.Room;


@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,DbUnitTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class CentreRepositoryTests extends SequenceResetDbUtil {
    
	@Rule
	public final ExpectedException exception = ExpectedException.none();    
    @Autowired protected CentreRepository centreRepository;
    
    @Test(expected=LazyInitializationException.class)
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testFindOneFailOnRooms() {
    		
    		Centre centre = centreRepository.findOne(1);
    		assertEquals(1, centre.getCentreId().intValue()); 
    		assertEquals("Climbtastic", centre.getName()); 
    		assertEquals(1, centre.getRooms().size()); 
    }  
    
    @Test(expected=LazyInitializationException.class)
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testFindOneFailOnWalls() {
    		
    		Centre centre = centreRepository.findOne(1);
    		assertEquals(1, centre.getCentreId().intValue()); 
    		assertEquals("Climbtastic", centre.getName()); 
    		assertEquals(1, centre.getRooms().size()); 
    		Room blueRoom = (Room) centre.getRooms().toArray()[0];
    		assertEquals(1, blueRoom.getWalls().size()); 
    }  
    
    @Test
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testFindByCentreId() {
    		
    		Centre centre = centreRepository.findByCentreId(1);
    		assertEquals(1, centre.getCentreId().intValue()); 
    		assertEquals("Climbtastic", centre.getName()); 
    		assertEquals(2, centre.getRooms().size()); 
    } 
    
    @Test
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value="newCentre.xml",assertionMode=DatabaseAssertionMode.NON_STRICT)    
	public void testSave() {
    		
    		Centre centre = new Centre("Glasgow Climbing Academy");
    		centre.addRoom(new Room(centre, "The Crazy Room"));
    		centreRepository.save(centre); 
    }

}
