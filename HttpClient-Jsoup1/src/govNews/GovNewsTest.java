package govNews;

/** 
* @Description: 测试 
* @author Wongsir
* @date 2016年11月24日 下午3:41:44 
*  
*/
public class GovNewsTest {

	public static void main(String[] args){
		GovEntity govEntity = GovService.getNews("http://www.gov.cn/index.htm");
		System.out.println("版块标题："+ govEntity.getSection());
	}
}
