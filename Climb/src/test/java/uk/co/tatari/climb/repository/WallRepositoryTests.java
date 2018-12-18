package uk.co.tatari.climb.repository;


import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.hibernate.LazyInitializationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import uk.ac.tatari.climb.test.SequenceResetDbUtil;
import uk.co.tatari.climb.domain.Room;
import uk.co.tatari.climb.domain.ScrewThread;
import uk.co.tatari.climb.domain.Wall;


@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class WallRepositoryTests extends SequenceResetDbUtil {

	@Rule
	public final ExpectedException exception = ExpectedException.none();    
    @Autowired protected WallRepository wallRepository;
    @Autowired protected RoomRepository roomRepository;
    @Autowired protected CentreRepository centreRepository;
    
    @Test(expected=LazyInitializationException.class)
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testFindOneFailOnScrewThreads() {
    		
    		Wall areteRight = wallRepository.findOne(5);
    		areteRight.getScrewThreads().size(); 
    }  
    
    @Test
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testFindByWallId() {
    		
    		Wall areteRight = wallRepository.findByWallId(5);
    		assertEquals("Arete Right", areteRight.getName()); 
    		assertEquals(1, areteRight.getScrewThreads().size()); 
    		
    		ScrewThread screwThread = (ScrewThread) areteRight.getScrewThreads().toArray()[0];
    		assertEquals(5, screwThread.getX().intValue()); 
    		assertEquals(5, screwThread.getY().intValue()); 
    		assertEquals(5, screwThread.getZ().intValue()); 
    } 
    
    
    @Test
    @ExpectedDatabase(value="newScrewThread.xml",assertionMode=DatabaseAssertionMode.NON_STRICT)
    @DatabaseSetup(value="repositoryTestsDataSetup.xml",type=DatabaseOperation.CLEAN_INSERT)    
	public void testSave() {
    			
    		Wall wall =  wallRepository.findByWallId(5);
    		wall.addScrewThread(new ScrewThread(wall, 5, 10, 0));
    		
    		wallRepository.save(wall); 
    		
    		BufferedImage off_Image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
    		File outputfile = new File("/Users/paulgrimwood/Documents/saved.png");
    	    try {
				ImageIO.write(off_Image, "png", outputfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
 
}
