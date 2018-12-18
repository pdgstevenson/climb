package uk.ac.tatari.climb.test;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;


@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,DbUnitTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class SequenceResetDbUtil  {
    
	@Autowired DataSource dataSource;
    
	@Before
    public void setUp() throws SQLException  {
   	
        Connection jdbcConnection = null;
        try {
        		jdbcConnection = dataSource.getConnection();
	        	Statement statement = jdbcConnection.createStatement();
	        	statement.execute("ALTER SEQUENCE centre_centre_id_seq RESTART WITH 2;");
	        	statement.execute("ALTER SEQUENCE room_room_id_seq RESTART WITH 3;");
	        	statement.execute("ALTER SEQUENCE wall_wall_id_seq RESTART WITH 6;");
	        	statement.execute("ALTER SEQUENCE screw_thread_screw_thread_id_seq RESTART WITH 2;");
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			jdbcConnection.close();
		}
    }
}
