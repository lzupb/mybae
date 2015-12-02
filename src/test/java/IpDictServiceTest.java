import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baidu.mywork.model.IpDictBean;
import com.baidu.mywork.service.IpDictService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles(profiles="development")
public class IpDictServiceTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private IpDictService service;
	
	@Test
	public void saveTest() {
		IpDictBean bean = new IpDictBean(null);
		bean.setName("beijing");
		bean.setServerIp("172.22.64.170");
		bean.setClientIpRange("172.17.0.0/20");
		service.saveDict(bean);
	}
}
