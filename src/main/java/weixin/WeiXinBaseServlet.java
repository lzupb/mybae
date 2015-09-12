package weixin;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 校验微信公众平台验证信息
 * 加密/校验流程如下：
1. 将token、timestamp、nonce三个参数进行字典序排序
2. 将三个参数字符串拼接成一个字符串进行sha1加密
3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
 */
public class WeiXinBaseServlet extends HttpServlet {
	
	private static final Logger logger = LoggerFactory
			.getLogger(WeiXinBaseServlet.class);
	
	private static final long serialVersionUID = 142599325512717383L;
	private static String TOKEN;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {  
        	 // 随机字符串
            String echostr = request.getParameter("echostr");
    		logger.info("echostr:"+echostr);
            
        	// 微信加密签名
            String signature = request.getParameter("signature"); 
    		logger.info("signature:"+signature);

            // 时间戳
            String timestamp = request.getParameter("timestamp");
    		logger.info("timestamp:"+timestamp);

            // 随机数
            String nonce = request.getParameter("nonce");
    		logger.info("nonce:"+nonce);
            
            if (signature != null && timestamp != null && nonce != null) {
            	AuthWeiXin authWeiXin = new AuthWeiXin();
            	authWeiXin.setNonce(nonce);
            	authWeiXin.setSignature(signature);
            	authWeiXin.setTimestamp(timestamp);
            	if (auth(authWeiXin) == true) {
            		 // 确认请求来至微信
                    response.getWriter().print(echostr);
                    return;
            	}
            }   
            response.getWriter().print("error!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class AuthWeiXin {
		private String signature=null;
		private String timestamp = null;
		private String nonce= null;
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public String getNonce() {
			return nonce;
		}
		public void setNonce(String nonce) {
			this.nonce = nonce;
		}		
	}	
	
	private boolean auth(AuthWeiXin auth) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		boolean flag = false;
		 String[] str = { TOKEN, auth.getTimestamp(), auth.getNonce()};
         Arrays.sort(str); // 字典序排序
         String bigStr = str[0] + str[1] + str[2];         
         String testSignature = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();        
         // 确认请求来至微信
         if (testSignature.equals(auth.getSignature())) {
            flag = true;
         }
		return flag;
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		TOKEN = ResourceBundle.getBundle("conf").getString("weixin.token");
		logger.info("TOKEN init finished:"+TOKEN);
	}	
}
