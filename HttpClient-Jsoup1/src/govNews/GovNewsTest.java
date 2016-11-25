package govNews;

import java.util.List;

/** 
* @Description: 测试 
* @author Wongsir
* @date 2016年11月24日 下午3:41:44 
*  
*/
public class GovNewsTest {
	
	
	public static void main(String[] args){
//		List<GovEntity> extracts = GovService.getNewsHead("http://www.gov.cn/index.htm");
//		System.out.println("大标题："+ govEntity.getBigTitle());
//		printf(extracts);
		
		GovEntity govNewsBody = GovService.getNewsBody("http://www.gov.cn/premier/2016-11/25/content_5137628.htm");
		System.out.println("小标题：" + govNewsBody.getSmallTitle());
		System.out.println("时间：" + govNewsBody.getTime());
		System.out.println("新闻来源：" + govNewsBody.getFrom());
		System.out.println("新闻内容：" + govNewsBody.getContent());
	}
	
	public static void printf(List<GovEntity> datas){
		for(GovEntity data : datas){
//			if(data.getSection()==null){
//				continue;
//			}
//			System.out.println("版块标题："+ data.getSection());
//			System.out.println("版块标题链接："+ data.getSecLink());
//			System.out.println("***********************************");
//			if(data.getSection()==null){
//				continue;
//			}
//			System.out.println("大标题："+ data.getBigTitle());
//			System.out.println("原文详情链接："+ data.getHref());
//			System.out.println("***********************************");
			
		}
	}
}
