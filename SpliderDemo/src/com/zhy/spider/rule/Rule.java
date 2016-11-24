package com.zhy.spider.rule;

/**
 * 规则类
 * 
 * @author zhy
 * 
 */
public class Rule
{
	/**
	 * 链接
	 */
	private String url;

	/**
	 * 参数集合
	 */
	private String[] params;
	/**
	 * 参数对应的值
	 */
	private String[] values;

	/**
	 * 对返回的HTML，第一次过滤所用的标签，请先设置type
	 */
	private String resultTagName;

	/**
	 * CLASS / ID / SELECTION
	 * 设置resultTagName的类型，默认为ID 
	 */
	private int type = ID ;
	
	/**
	 *GET / POST
	 * 请求的类型，默认GET
	 */
	private int requestMoethod = GET ; 
	
	public final static int GET = 0 ;
	public final static int POST = 1 ;
	

	public final static int CLASS = 0;
	public final static int ID = 1;
	public final static int SELECTION = 2;

	public Rule()
	{
	}

	/**
	 * 
	 * @param url  目标链接，如：http://www.gov.cn/ 
	 * @param params  参数集合 ，如：new String[]{"keyWord","time"}
	 * @param values  参数对应的值，如：new String[]{"习近平","2016年"}
	 * @param resultTagName 对返回的HTML，第一次过滤所用的标签，请先设置type，如："title"是一个类选择器，则对应的type是CLASS；其他的如“div.con-model”则对应标签选择器SELECTION
	 * @param type  设置resultTagName的类型，默认为ID（CLASS / ID / SELECTION）
	 * @param requestMoethod 请求的类型，默认GET
	 */
	public Rule(String url, String[] params, String[] values,
			String resultTagName, int type, int requestMoethod)
	{
		super();
		this.url = url;
		this.params = params;
		this.values = values;
		this.resultTagName = resultTagName;
		this.type = type;
		this.requestMoethod = requestMoethod;
	}

	
	



	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String[] getParams()
	{
		return params;
	}

	public void setParams(String[] params)
	{
		this.params = params;
	}

	public String[] getValues()
	{
		return values;
	}

	public void setValues(String[] values)
	{
		this.values = values;
	}

	public String getResultTagName()
	{
		return resultTagName;
	}

	public void setResultTagName(String resultTagName)
	{
		this.resultTagName = resultTagName;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getRequestMoethod()
	{
		return requestMoethod;
	}

	public void setRequestMoethod(int requestMoethod)
	{
		this.requestMoethod = requestMoethod;
	}


	
	

}
