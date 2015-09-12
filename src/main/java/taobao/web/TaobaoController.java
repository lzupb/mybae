package taobao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/taobao")
public class TaobaoController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {		
		return "error/404";
	}	
}