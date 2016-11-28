package govNews;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/** 
* @Description: TODO 
* @author Wongsir
* @date 2016年11月28日 下午3:09:03 
*  
*/
public class GetImgsDemo {
	public void getImg() throws IOException{
		File file = new File("E://imgs");
		if(!(file.exists())){
			file.mkdirs();
		}
		//以网易为例，Jsoup获取网页图片
		Document doc = Jsoup.connect("http://www.163.com/").get();
		//获取后缀为png和jpg的图片的元素集合
		Elements pngs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
		//遍历元素
		for(Element e : pngs){
			//获取img中的src路径
			String src = e.attr("src");
			//获取后缀名
			String imageName = src.substring(src.lastIndexOf("/"), src.length());
			//连接url
			URL url = new URL(src);
			URLConnection uri = url.openConnection();
			//获取数据流
			InputStream is = uri.getInputStream();
			//写入数据流
			OutputStream os = new FileOutputStream(new File("E://imgs",imageName));
			byte[] buf = new byte[1024];
			int h=0;
			while((h=is.read(buf))!=-1){
				os.write(buf, 0, h);
			}
		}	
	}
	public static void main(String[] args) throws IOException{
//		new Imgs.getImg();
		GetImgsDemo imgs = new GetImgsDemo();
		imgs.getImg();
	}

}
