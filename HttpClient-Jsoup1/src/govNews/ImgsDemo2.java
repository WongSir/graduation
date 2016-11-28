package govNews;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

/** 
* @Description: TODO 
* @author Wongsir
* @date 2016年11月28日 下午3:57:01 
*  
*/
public class ImgsDemo2 {

	private static String DIR   =   "E:\\TEMP\\";  
    private static String URL   =   "http://www.sina.com.cn/";  
    private static String PIC   =   ".jpg";  
    private static String FILENAME  =   "jpg";  
      
    public static void main(String[] args) throws ClientProtocolException, IOException {  
//        HttpClient httpClient = new DefaultHttpClient();  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URL);  
        HttpResponse response = httpClient.execute(httpGet);  
        HttpEntity entity =response.getEntity();  
        StringBuilder builder = new StringBuilder();  
        if(entity != null){  
            InputStream inputStream = entity.getContent();  
            int l;  
            byte[] temp = new byte[2048];  
            while((l = inputStream.read(temp))!=-1){  
                builder.append(new String(temp));  
            }  
              
        }  
        ArrayList<String> imgs = analyseImgs(builder.toString());  
        savetodisk(imgs,makedir(ImgsDemo2.URL));  
    }  
      
      
    /** 
     * 根据URL创建目录 
     * @param url 
     */  
    private static String makedir(String url) {  
        String dirname = url.substring(url.indexOf(".")+1,url.lastIndexOf("."));  
        File file = new File(ImgsDemo2.DIR+dirname);  
        if(!file.exists()){  
            file.mkdirs();  
        }  
        return file.getPath();  
    }  
      
      
    /** 
     * 存储图片到硬盘 
     * @param imgs 
     * @param diskuri 
     */  
    private static void savetodisk(ArrayList<String> imgs,String diskuri) {  
        for(String tt:imgs){  
            System.out.println("图片地址: "+tt);  
            ImgsDemo2.writeImageLocal(diskuri+tt.substring(tt.lastIndexOf("/"),tt.lastIndexOf(PIC))+PIC, ImgsDemo2.loadImageUrl(tt));  
        }  
          
    }  
  
  
    /** 
     * 根据获取到的HTML页面分析图片 
     * @param string 
     * @return 
     */  
    private static ArrayList<String> analyseImgs(String string) {  
        ArrayList<String> list = new ArrayList<String>();  
        String[] temp = string.split(PIC);  
        for(String a:temp){  
            list.add(a.substring(a.lastIndexOf("http://"))+PIC);  
        }  
        return list;  
    }  
  
  
  
    /** 
     * 通过URL地址获取图片 
     * @param imgName 
     * @return 
     */  
    public static BufferedImage loadImageUrl(String imgName)       
    {       
        try      
        {       
            URL url = new URL(imgName);       
            return ImageIO.read(url);       
        } catch (IOException e)       
        {       
            System.out.println(e.getMessage());       
        }       
        return null;       
    }       
          
    /**    
    * 生成新图片到本地    
    */      
    public static void writeImageLocal(String newImage, BufferedImage img)       
    {       
        if (newImage != null && img != null)       
        {       
            try      
            {       
                File outputfile = new File(newImage);       
                ImageIO.write(img, FILENAME, outputfile);   
                System.out.println("one jpg saved");  
            } catch (IOException e)       
            {       
                System.out.println(e.getMessage());       
            }       
        }       
    }
}
