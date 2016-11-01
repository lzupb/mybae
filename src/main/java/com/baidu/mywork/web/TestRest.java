package com.baidu.mywork.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test")
public class TestRest {

	@RequestMapping(method = RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String query = request.getParameter("query");
		
		
		try {
			String style = MobadsTools.getADStyle(query);
			if (style != null) {
				response.getWriter().write(style);
			} else {
				response.getWriter().write("undefined");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("undefined");
		}	

	}
}

class MobadsTools {
	private static char[] base64EncodeChars = "0KajD7AZcF2QnPr5fwiHRNygmupUTIXx69BWb-hMCGJo_V8Eskz1YdvL34letqSO"
			.toCharArray();

	private static final byte[] base64DecodeChars = new byte[''];

	static {
		int i = 0;
		while (i < base64EncodeChars.length) {
			base64DecodeChars[base64EncodeChars[i]] = ((byte) i);
			i += 1;
		}
	}

	public static String encode(byte[] data) {
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;
		while (i < len) {
			b1 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				sb.append("==");
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4)
						| ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				sb.append("=");
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4)
					| ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2)
					| ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}
		return sb.toString();
	}

	public static byte[] decode(String str)
			throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		byte[] data = str.getBytes("US-ASCII");
		int len = data.length;
		int i = 0;
		int b1, b2, b3, b4;
		while (i < len) {
			/* b1 */
			do {
				b1 = base64DecodeChars[data[i++]];
			} while (i < len && b1 == -1);
			if (b1 == -1)
				break;
			/* b2 */
			do {
				b2 = base64DecodeChars[data[i++]];
			} while (i < len && b2 == -1);
			if (b2 == -1)
				break;
			sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));
			/* b3 */
			do {
				b3 = data[i++];
				if (b3 == 61)
					return sb.toString().getBytes("iso8859-1");
				b3 = base64DecodeChars[b3];
			} while (i < len && b3 == -1);
			if (b3 == -1)
				break;
			sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
			/* b4 */
			do {
				b4 = data[i++];
				if (b4 == 61)
					return sb.toString().getBytes("iso8859-1");
				b4 = base64DecodeChars[b4];
			} while (i < len && b4 == -1);
			if (b4 == -1)
				break;
			sb.append((char) (((b3 & 0x03) << 6) | b4));
		}
		return sb.toString().getBytes("iso8859-1");
	}

	public static String getADStyle(String str) {
		String decodeQuery = "";
		try {
			decodeQuery = new String(decode(str));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("decodeQuery:" + decodeQuery);
		Pattern pattern = Pattern.compile("&prod=([a-z]+)&");
		Matcher matcher = pattern.matcher(decodeQuery);
		if (matcher.find()) {
			System.out.println(matcher.group(1));
			return matcher.group(1);
		}
		return null;
	}

	public static void main(String[] args)
			throws UnsupportedEncodingException {
		String query = "IHdbuyu9IykYFh--5HDhIAYqPHDzFhPV5HRknBuVujYkFh3qnH0hmgfqnBuv5y78uZFEpywhuyNbg1f8njwxPa3sQW0hIZFhIZ0qTvwog1f8njfhmLnqFMKo5yPEUi4WUAN9Uhd9TLw-TB4VuLN9Thwxmv3hTHdWuhRvPyDYr7qWTZchmgKspyfqmvu-PWN9Pj6huAN85Hn8nau1I1Yknj6sFMPC5HD4nW0hI1YzPHnhpjYYrj0hUZTqnWRsFhkC5HcdnauYTjdHfY6ViHbdriuBThfqTv7VTLN8uzuET1d9UhwzUv-bFhq1IWYYQWf8nBuBuZcqnHbhpyd-pHdKnj0snj0YPDFAfY77riuVmynqPj0lnDRlrjRln1blnjnlnRchTv3qfH0snj0sPjwawbPKwHbhmy4bThqGu7qGujYvrA7hP1F-uj9hnH--nWR3Fh-V5y4dUAshmLNGujYLPYmYnbfswWmsf1fYPHmdwHcYwWKawRDkPDu7rRnznLs4wR7jwbcYPj0snj0sfiuzug7xpyfqP1Khn1RknWc3uHcLrjbYPHRkmWfkP17huhPBrA7WuWDhUhqs5H0snj0sFMPLpHYkFhPGujYYrHTvPNtYPHcvg10hu1YhIvbquHw9mHNbPAw9PH-bg1RzxARYmyDdujwbmHR4m-tdnLk-PA79PyfYuADdrH7xPW7tuHw9mHNbPAw9PHbzg1mkxARYmyDdujwbmHR4P7tvniu9mLfqwDs_H70hIAN_5iuGTjYhIy_qFRRvFRc3FRc3FRRvFH63FH9AFHFjFRR3FH6kFH9jFRRdFH-jFRFKFMP-XjYhXh-s5iuYmycqnau1uAnqPWRsPBs4nHc4QjmdnWm_rHDYriusThqb5yu-uyfhmgKY5H0hmg0qnau9TA-b5HcYnHfvn1nhIgP-uZnqFh71TvNY5HDdFhPCpyfqFh4Y5y4dUAkxnau8ugfqnH0sFMP-T1YkPjTsn1mYPjfsrjn4FMKxIhNz5H0hmgKGUhuE5gcvwjwERA-MQyuVTZF9fLcVIWT4UY_1rZ79UW-zRZ-1Tym1Xad9HvkxIhdMThu4TZ7hnMPxiL-sgvuZTLKhn1uEi1n3Ty78rgFfXgPkuWP3Tg7mriYLXWNxR7tYQNtLr7qfXWf3uMCVPL7dUMK-mgFsi1FsTZ7gTZRLXgwdIy7_TWTzTZNkXN9kPhFdpyuJPadfpWwxRZ-QphqdihGhiDbdRa38";
		// String decodeQuery = new String(decode(query));
		System.out.println(getADStyle(query));

	}
}
