package taobao.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.baidu.mywork.util.MySQLDB;
import com.baidu.mywork.util.SpringContextUtil;
import com.taobao.api.request.TmcUserPermitRequest;



/**
 * MySQL示例，通过该示例可熟悉BAE平台MySQL的使用（CRUD）
 */
public class UserRegister {
	
	private static Logger logger = Logger.getLogger("java");

	public static void registerUser(String userJson) {
		logger.info("logger UserRegister");
		String sql = "insert into taobao_user(taobao_user_json,create_date) values (:userJson,:createDate) ";
		Date nowDate = new Date();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("createDate", nowDate);
		params.put("userJson", userJson);
		try {
			MySQLDB mySQLDB = SpringContextUtil.getBeanByType(MySQLDB.class);
			mySQLDB.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void permitTmc() {
		//TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		TmcUserPermitRequest req=new TmcUserPermitRequest();
		req.setTopics("taobao_trade_TradeCreate,taobao_refund_RefundCreate");
		//TmcUserPermitResponse response = client.execute(req , sessionKey);
	}
}
