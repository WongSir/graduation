package govNews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	 * 获取新闻信息头部信息
	 * @param url
	 * @return govNews
	 */
	public static List<GovEntity> getNewsHead(String url){
		
		String html = pickData(url);
//		System.out.println("测试点***********************************");
//		System.out.println(html);
		List<GovEntity> govNewsHead = analyzeHead(html);
		return govNewsHead;
	}
	
	/**
	 * 爬取网页信息
	 * @param url
	 * @return entity服务器返回的响应实体信息
	 */
	public static String pickData(String url){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		//设置编码格式
		String charset="utf-8";
		try {
			//HttpResponse:服务器在接收和解释请求之后返回一个HTTP响应信息
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			try {
				//获取响应的实体
				HttpEntity entity = httpResponse.getEntity();
				//判断响应状态是否为空，并返回响应状态以便打印
				if(entity!=null){
//					return EntityUtils.toString(entity);
					String tmp = EntityUtils.toString(entity);
					if(charset != null){
						tmp= new String(tmp.getBytes("ISO-8859-1"),charset);
					}
					return tmp;
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
	 * 使用Jsoup解析页面信息:版块标题、版块链接、大标题、原文链接
	 * @param html
	 * @return 返回解析后的数据
	 */
	public static List<GovEntity> analyzeHead(String html){
		
		String section,secLink,bigTitle,smallTitle,href,url,time,from,content;
		
		List<GovEntity> datas = new ArrayList<GovEntity>();
		GovEntity data =null;
		
		/**
		 * 将HTML解析成一个Document之后，就可以使用类似于DOM的方法进行操作
		 * 一旦拥有了一个Document，你就可以使用Document中适当的方法或它父类 Element和Node中的方法来取得相关数据。
		 */
		Document document = Jsoup.parse(html);
		//版块标题、版块链接
//		Element gSection = document.select("div.lmtit").first();
//		section = gSection.child(0).text().toString();
		Elements gSection = document.select("div.lmtit > a"); //取div下的class="lmtit"的a标签
		String tmpLink = "http://www.gov.cn";//进行链接拼接
		for(Element link : gSection){
			if((section = link.text())==null){
				continue;
			}
			secLink =tmpLink + link.attr("href");
			
			data =new GovEntity();
			data.setSection(section);
			data.setSecLink(secLink);
			
			datas.add(data);
		}
		
		
		//大标题、原文链接
//		Element gBigTitle = document.select("ul.h206 > li").first();
//		bigTitle = gBigTitle.child(0).text().toString();
		Elements gBigTitle = document.select("ul.h206 > li > a");
		for(Element link : gBigTitle){
			if((link.text()).isEmpty()){
				continue;
			}
			bigTitle = link.text();
			href = link.attr("href");
			
			data = new GovEntity();
			data.setBigTitle(bigTitle);
			data.setHref(href);
			
			datas.add(data);
		}
		
		
		//从大标题的链接进入抓取原文详情
		for(GovEntity tmp : datas){
			if(tmp.getHref()!=null){
				String uri =tmp.getHref();
				Entry(uri);
			}
		}
		
		
		
		
		//GovEntity govEntity = new GovEntity(bigTitle);
//		GovEntity govEntity = new GovEntity(section,bigTitle,smallTitle,href,time,from,content);
//		return govEntity;
		return datas;
	}
	
	/**
	 * 使用Jsoup解析页面信息:小标题、时间、新闻来源、新闻内容
	 * @param html
	 * @return 返回解析后的数据
	 */
	public static List<GovEntity> analyzeBody(String html){
		String smallTitle,url,time,from,content;
		List<GovEntity> datas = new ArrayList<GovEntity>();
		GovEntity data =null;
		
		Document document = Jsoup.parse(html);
		
		//小标题
		Element gSmallTitle = document.select("div.pub_border > h1").first();
		smallTitle = gSmallTitle.text().toString();
		//本地路径
		
		//时间
		Element gTime = document.select("div.pages-date").first();
		time = gTime.html().substring(0,18);
		
		//新闻来源
		Element gFrom = document.select("div.pages-date > span").first();
		from = gFrom.text().toString();
		
		//新闻内容
//		Element gContent = document.getElementById("UCAP-CONTENT");
//		content = gContent.text().toString();
		content = getNewsContent(document, "UCAP-CONTENT");
		
		//此处代码待完成
		return datas;
		
		
//		List<GovEntity> govEntity = new GovEntity(smallTitle,time,from,content);
//		return govEntity;
		
	}
	
	/**
	 * 获取新闻内容
	 * @param doc
	 * @param id
	 * @return 返回获取到达的新闻内容
	 */
	public static String getNewsContent(Document doc,String id){
		Element gContent = doc.getElementById(id);
		Elements elements = gContent.getElementsByTag("p");
		StringBuffer stringBuffer = new StringBuffer();
		for(Element e : elements){
			stringBuffer.append(e.text() + "\n");
		}
		return stringBuffer.toString();
		
	}
	
	/**
	 * 获取新闻信息主体详细信息
	 * @param url
	 * @return
	 */
	public static List<GovEntity> getNewsBody(String url){
		String html = pickData(url);
		List<GovEntity> govNewsBody = analyzeBody(html);
		return govNewsBody;
		
	}
	
	
	/**
	 * 多重链接入口
	 * @param url
	 * @return
	 */
	public static void Entry(String uri) {
		getNewsBody(uri);
	}
}
