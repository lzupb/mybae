package taobao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import taobao.service.UserRegister;

import com.taobao.api.internal.util.WebUtils;


/**
 * 淘宝oauth校验类 参考以下地址：
 * http://open.taobao.com/doc/detail.htm?spm=0.0.0.0.Dl3PzF&id=118
 */
public class TaoBaoAuthServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 543230989482923437L;

	private static String APPKEY;

	private static String APPSECRET;

	/**
	 * taobao OAUTH2.0 地址
	 */
	private static String OAUTH2_URL;
	/**
	 * taobao OAUTH 授权成功回调地址
	 */
	private static String REDIRECT_URL;

	private static final Logger logger = LoggerFactory
			.getLogger(TaoBaoAuthServlet.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String oauthError = request.getParameter("error");
		if (oauthError != null && oauthError.equals("access_denied")) {// 认证失败
			logger.info("logger error!!");
			response.getWriter().write("oauth fail!!!");
		} else {
			String oauthCode = request.getParameter("code");
			Map<String, String> props = new HashMap<String, String>();
			props.put("grant_type", "authorization_code");
			props.put("code", oauthCode);
			props.put("client_id", APPKEY);
			props.put("client_secret", APPSECRET);
			props.put("redirect_uri", REDIRECT_URL);
			props.put("view", "web");
			try {
				String s = WebUtils.doPost(OAUTH2_URL, props, 30000, 30000);
				logger.info("logger access token:" + s);
				//request.getRequestDispatcher("/taobao").forward(request, response);;
				UserRegister.registerUser(s);
				response.sendRedirect(REDIRECT_URL);
			} catch (IOException e) {
				e.printStackTrace();
				response.sendRedirect("http://www.baidu.com");
			}catch (Exception e) {
				e.printStackTrace();
				logger.info("logger exception");
				response.sendRedirect("http://www.360.cn/");
			}
		}
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		APPKEY = ResourceBundle.getBundle("conf").getString("taobao.appkey");
		APPSECRET = ResourceBundle.getBundle("conf").getString(
				"taobao.appSecret");
		OAUTH2_URL = ResourceBundle.getBundle("conf").getString(
				"taobao.oauth_url");
		REDIRECT_URL = ResourceBundle.getBundle("conf").getString(
				"taobao.redirect_url");
		logger.info("TaoBaoBaseServlet init finished!!");
	}

	public static void main(String[] args) {
		APPKEY = ResourceBundle.getBundle("conf").getString("taobao.appkey");
		APPSECRET = ResourceBundle.getBundle("conf").getString(
				"taobao.appSecret");
		OAUTH2_URL = ResourceBundle.getBundle("conf").getString(
				"taobao.oauth_url");
		REDIRECT_URL = ResourceBundle.getBundle("conf").getString(
				"taobao.redirect_url");

		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", "authorization_code");
		props.put("code", "pCIn1JdGvestoISXIbvUIoWt2666");
		props.put("client_id", APPKEY);
		props.put("client_secret", APPSECRET);
		props.put("redirect_uri", REDIRECT_URL);
		props.put("view", "web");
		String s = "";
		try {
			s = WebUtils.doPost(OAUTH2_URL, props, 30000, 30000);
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
