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

import uk.co.tatari.climb.domain.Centre;
import uk.co.tatari.climb.domain.Room;


@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,DbUnitTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class RoomRepositoryTests {

	@Rule
	public final ExpectedException exception = ExpectedException.none();    
    @Autowired protected RoomRepository roomRepository;

    
    @Test(expected=LazyInitializationException.class)
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testFindOneFailOnWalls() {
    		
    		Room blueRoom = roomRepository.findOne(1);
    		assertEquals(1, blueRoom.getWalls().size()); 
    }  
    
    @Test
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testFindByRoomId() {
    		
    		Room blueRoom = roomRepository.findByRoomId(1);
    		assertEquals("The Blue Room", blueRoom.getName()); 
    		assertEquals(4, blueRoom.getWalls().size()); 
    } 
    
    /*
    @Test
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testSave() {
    		
    		Centre centre = new Centre("Glasgow Climbing Centre");
    		centreRepository.save(centre);   
    		
    		centre.addRoom(new Room(centre, "Blue Room"));
    		centreRepository.save(centre); 
    }*/
 
}
