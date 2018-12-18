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
import uk.co.tatari.climb.domain.Room;
import uk.co.tatari.climb.domain.Wall;


@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,DbUnitTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class RoomRepositoryTests extends SequenceResetDbUtil {

	@Rule
	public final ExpectedException exception = ExpectedException.none();    
    @Autowired protected RoomRepository roomRepository;
    @Autowired protected CentreRepository centreRepository;
    
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
    
    
    @Test
    @ExpectedDatabase(value="newWall.xml",assertionMode=DatabaseAssertionMode.NON_STRICT)
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testSave() {
    		
    		Room room = roomRepository.findByRoomId(2);		
    		Wall wall = new Wall(room, new Integer(7), "right-facing-left");
    		wall.setName("Arete Left");
    		wall.setWidthBase(200);
    		wall.setWidthTop(200);
    		wall.setHeightLeft(800);
    		wall.setHeightRight(800);
    		wall.setzLeft(10);
    		wall.setzRight(10);
    		room.addWall(wall);
    		
    		roomRepository.save(room); 
    }
 
}
