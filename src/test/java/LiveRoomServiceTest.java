import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baidu.mywork.model.LiveRoomBean;
import com.baidu.mywork.service.LiveRoomService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles(profiles="development")
public class LiveRoomServiceTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private LiveRoomService service;
	
	@Test
	public void saveTest() {
		LiveRoomBean bean = new LiveRoomBean(null);
		bean.setName("testroom4");
		bean.setRoomDetail("test room detail4");
		bean.setRoomPassword("123456");
		bean.setStartTime(new Date());
		bean.setEndTime(new Date());
		service.saveLiveRoom(bean);
	}
	
	@Test
	public void updateTest() {
		LiveRoomBean bean = new LiveRoomBean(1L);		
		service.saveLiveRoom(bean);
	}
	
	@Test
	public void searchTest() {
		List<LiveRoomBean> list = service.findTodayLiveRoom();
		System.out.println(list.size());
		System.out.println("date:"+ (new Date(1383302150652L)));
	}
}
