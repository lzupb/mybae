package taobao;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserGetResponse;
import com.taobao.api.response.UserSellerGetResponse;

public class APITest {
     protected static String url = "http://gw.api.tbsandbox.com/router/rest";//沙箱环境调用地址
     //正式环境需要设置为:http://gw.api.taobao.com/router/rest
     protected static String appkey = "1021811702";
     protected static String appSecret = "sandboxac1aa4e1616391a1047d0af9f";
     protected static String sessionkey = "6100523c57a80a102eca89b6110b0e315af0801e0749d4c182558410"; //如 沙箱测试帐号sandbox_c_1授权后得到的sessionkey
     public static void testUserGet() {
         TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);//实例化TopClient类
         UserSellerGetRequest req = new UserSellerGetRequest();//实例化具体API对应的Request类
         req.setFields("nick,user_id,type");
         //req.setNick("sandbox_c_1");
         UserSellerGetResponse response;
         try {
             response = client.execute(req,sessionkey); //执行API请求并打印结果
             System.out.println("result:"+response.getBody());
          
         } catch (ApiException e) {
         // deal error
         }
         
         UserGetRequest  req2 = new UserGetRequest();
         req2.setFields("user_id,nick,seller_credit,created,last_visit,buyer_credit");
         try {
        	 UserGetResponse  response2 = client.execute(req2 , sessionkey);
            System.out.println("result2:"+response2.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     public static void main(String[] args) {
         APITest.testUserGet();
     }
 
}