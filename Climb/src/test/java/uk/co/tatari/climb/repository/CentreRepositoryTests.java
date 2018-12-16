package uk.co.tatari.climb.repository;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.tatari.climb.domain.Centre;


@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CentreRepositoryTests {

	@Rule
	public final ExpectedException exception = ExpectedException.none();    
    @Autowired protected CentreRepository centreRepository;

    @Test
	public void testSave() {
    		
    		Centre centre = new Centre();
    		centreRepository.save(centre);   		
    }
 
}
