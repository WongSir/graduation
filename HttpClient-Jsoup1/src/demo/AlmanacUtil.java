package demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Administrator on 2016-11-14.
 * 2345万年历信息爬取工具
 */
public class AlmanacUtil {
    /**
     *单例工具类
     */
    public AlmanacUtil(){

    }

    /**
     * 获取万年历信息
     * @return
     */
    public static Almanac getAlmanac(){
        String url="http://tools.2345.com/rili.htm";
        String html=pickData(url);
        Almanac almanac=analyzeHtmlByString(html);
        return almanac;
    }

    /**
     * 爬取网页信息
     * @return
     */
    private static String pickData(String url){
        /**
         * CloseableHttpClient:
         * 实现接口：Closeable, AutoCloseable, HttpClient；子类：AbstractHttpClient
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**
         * HttpGet:
         * 非线程安全；HttpGet有三个构造方法：HttpGet()、HttpGet(String uri)、HttpGet(URI uri)
         */
        HttpGet httpGet= new HttpGet(url);

        try {
            /**
             * HttpResponse:
             *      服务器在接收和解释请求之后返回一个HTTP响应信息
             *      Response      = Status-Line
             *      *(( general-header
             *      | response-header
             *      | entity-header ) CRLF)
             *      CRLF
             *      [ message-body ]
             */
            CloseableHttpResponse httpResponse=httpClient.execute(httpGet);

            try{
                //获取响应实体
                HttpEntity entity = httpResponse.getEntity();
                //判断响应状态是否为空（打印响应状态）
                if(entity!=null){
                    return EntityUtils.toString(entity);
                }
            }finally {
                httpResponse.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭链接，释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 使用Jsoup解析网页信息
     * @param html
     * @return
     */
    private static Almanac analyzeHtmlByString(String html){
        String solarDate,lunarDate,chineseAra,should,avoid ="";
        Document document = Jsoup.parse(html);
        //公历时间
        Element eSolarDate =document.getElementById("info_all");
        solarDate = eSolarDate.child(0).html().substring(0,16);
        //农历时间
        Element eLunarDate = document.getElementById("info_nong");
        lunarDate = eLunarDate.child(0).html().substring(1,3)+eLunarDate.html().substring(11);
        //天干地支纪年法
        Element eChineseAra = document.getElementById("info_chang");
        chineseAra = eChineseAra.text().toString();
        //宜
        should=getSuggestion(document,"yi");
        //忌
        avoid=getSuggestion(document,"ji");
        Almanac almanac = new Almanac(solarDate,lunarDate,chineseAra,should,avoid);
        return almanac;
    }

    /**
     * 获取宜/忌信息
     * @param id
     * @return
     */
    private static String getSuggestion(Document doc,String id){
        Element element=doc.getElementById(id);
        Elements elements=element.getElementsByTag("a");
        StringBuffer stringBuffer = new StringBuffer();
        for(Element e : elements){
            stringBuffer.append(e.text()+" ");
        }
        return stringBuffer.toString();
    }

}
