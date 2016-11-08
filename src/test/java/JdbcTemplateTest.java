import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@ActiveProfiles(profiles="development")
public class JdbcTemplateTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Test
	public void querySql() {
		String sql = "select * from livecast_conf";
		  
		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println("rs.name:"+ rs.getString("name"));
				System.out.println("rs.value:"+ rs.getString("value"));

			}
			
		});
		
	}
}
