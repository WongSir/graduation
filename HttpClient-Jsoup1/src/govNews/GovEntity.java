package govNews;

/** 
* @Description: gov实体类
* @author Wongsir
* @date 2016年11月24日 上午11:24:02 
*  
*/
public class GovEntity {
	private String section;//版块标题
	private String bigTitle;//新闻大标题
	private String smallTitle;//新闻小标题
	private String secLink;//版块详情链接
	private String href;//原文详情链接
	private String url;//本地路径（重新生成的本地链接）
	private String time;//时间
	private String from;//新闻来源
	private String content;//新闻内容
	
	
	public GovEntity(String section, String bigTitle, String smallTitle, String secLink, String href, String url,
			String time, String from, String content) {
		super();
		this.section = section;
		this.bigTitle = bigTitle;
		this.smallTitle = smallTitle;
		this.secLink = secLink;
		this.href = href;
		this.url = url;
		this.time = time;
		this.from = from;
		this.content = content;
	}




public GovEntity(String smallTitle, String time, String from, String content) {
		super();
		this.smallTitle = smallTitle;
		this.time = time;
		this.from = from;
		this.content = content;

	public GovEntity(String smallTitle) {
		super();
		this.smallTitle = smallTitle;
	}



//	public GovEntity(String smallTitle) {
//		super();
//		this.smallTitle = smallTitle;
//	}



	public GovEntity() {
		
	}



	public String getBigTitle() {
		return bigTitle;
	}


	public void setBigTitle(String bigTitle) {
		this.bigTitle = bigTitle;
	}


	public String getSmallTitle() {
		return smallTitle;
	}


	public void setSmallTitle(String smallTitle) {
		this.smallTitle = smallTitle;
	}


	public String getHref() {
		return href;
	}


	public void setHref(String href) {
		this.href = href;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}



	public String getSecLink() {
		return secLink;
	}



	public void setSecLink(String secLink) {
		this.secLink = secLink;
	}
	
	
	
	
}
