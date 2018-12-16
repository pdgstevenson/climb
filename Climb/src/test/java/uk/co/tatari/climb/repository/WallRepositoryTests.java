package uk.co.tatari.climb.repository;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.tatari.climb.domain.Wall;


@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class WallRepositoryTests {

	@Rule
	public final ExpectedException exception = ExpectedException.none();    
    @Autowired protected WallRepository wallRepository;

    @Test
	public void testSave() {
    		
    		Wall wall = new Wall();
    		wall.setDescription("Beginner wall, with approximate uniform 80 degree angle.");
    		wall.setWidth(830);
    		wallRepository.save(wall);
    		
    }
 
}
