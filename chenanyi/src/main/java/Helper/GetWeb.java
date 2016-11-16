package Helper;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class GetWeb {
	/**
	 * 根据URL抓取网页内容 此类要用到HttpClient组件
	 * 
	 * @author 陈安一
	 * @param url
	 * @return
	 */
	public static String getContentFormUrl(String url) {
		/* 实例化一个HttpClient客户端 */
		HttpClient client = new DefaultHttpClient();
		HttpGet getHttp = new HttpGet(url);

		String content = null;

		HttpResponse response;
		try {
			/* 获得信息载体 */
			response = client.execute(getHttp);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				/* 转化为文本信息 */
				content = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}

		return content;
	}
}
