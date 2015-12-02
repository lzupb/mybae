import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baidu.mywork.service.IpCalculateService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@ActiveProfiles(profiles="development")
public class IpCalculateServiceTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private IpCalculateService service;
	
	@Test
	public void loadMap() {
		service.refreshIPRangeMap();
		String serverip = service.calculateServerIpByClientIp("172.22.217.56");
		System.out.println("serverip:"+serverip);
	}
}
