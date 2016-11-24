package govNews;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/** 
* @Description: 中国政府网新闻信息抓取业务处理 类
* @author Wongsir
* @date 2016年11月24日 下午1:47:03 
*  
*/
public class GovService {

	/**
	 * 单例业务处理类
	 */
	public GovService(){
		
	}
	
	/**
	 * 获取新闻信息
	 * @param url
	 * @return govNews
	 */
	public static GovEntity getNews(String url){
		
		String html = pickData(url);
		GovEntity govNews = analyzeHtml(html);
		return govNews;
	}
	
	/**
	 * 爬取网页信息
	 * @param url
	 * @return entity服务器返回的响应实体信息
	 */
	public static String pickData(String url){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			//HttpResponse:服务器在接收和解释请求之后返回一个HTTP响应信息
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			try {
				//获取响应的实体
				HttpEntity entity = httpResponse.getEntity();
				//判断响应状态是否为空，并返回响应状态以便打印
				if(entity!=null){
					return EntityUtils.toString(entity);
				}
			} finally {
				httpResponse.close();
			}
			
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//关闭连接，释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	/**
	 * 使用Jsoup解析页面信息
	 * @param html
	 * @return 返回解析后的数据
	 */
	public static GovEntity analyzeHtml(String html){
		String section,bigTitle,smallTitle,href,url,time,from,content="";
		/**
		 * 将HTML解析成一个Document之后，就可以使用类似于DOM的方法进行操作
		 * 一旦拥有了一个Document，你就可以使用Document中适当的方法或它父类 Element和Node中的方法来取得相关数据。
		 */
		Document document = Jsoup.parse(html);
		//版块标题
		Element gSection = document.select("div.lmtit").first();
		section = gSection.child(0).text().toString().trim();
		
		//大标题
		Elements gBigTitle = document.select("ul.").first();
//		bigTitle = gBigTitle.
		
		//小标题
		
		//原文链接
		
		//本地路径
		
		//时间
		
		//新闻来源
		
		//新闻内容
		
		GovEntity govEntity = new GovEntity(section);
//		GovEntity govEntity = new GovEntity(section,bigTitle,smallTitle,href,time,from,content);
		return govEntity;
		
	}
}
