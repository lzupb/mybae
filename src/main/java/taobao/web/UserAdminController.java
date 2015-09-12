package taobao.web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baidu.livecast.util.MySQLDB;
import com.baidu.livecast.util.SpringContextUtil;


/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserAdminController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		try {
			MySQLDB mySQLDB = SpringContextUtil.getBeanByType(MySQLDB.class);
			List<Map<String, Object>> list = mySQLDB.query("select * from taobao_user", null);
			model.addAttribute("list", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "error/404";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", "abc");
		return "account/adminUserForm";
	}	
}