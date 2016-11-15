package demo;

/**
 * Created by Administrator on 2016-11-14.
 */
public class AlmanacUtilTest {
    public static void main(String args[]){
        Almanac almanac = AlmanacUtil.getAlmanac();
        System.out.println("公历时间："+almanac.getSolar());
        System.out.println("农历时间："+almanac.getLunar());
        System.out.println("天干地支："+almanac.getChineseAra());
        System.out.println("宜："+almanac.getShould());
        System.out.println("忌："+almanac.getAvoid());
    }
}
