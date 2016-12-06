package Get;

import java.util.List;

import Helper.GetWeb;
import Helper.RegContent;
import Helper.SaveTxt;

public class GetTxt {
	/**
	 * @author �°�һ
	 * @param cate
	 *            ���࣬16��RQ
	 * @param pagecount
	 *            ��ȡ����ҳ��
	 *  ���ݻ�ȡ��url�����ص�html���룬��ȡС˵����ͼƬ���浽�ļ���
	 */
	public static void Gettxt(int cate, int pagecount) {
		for (int m = 1; m < pagecount; m++) {
			int count = 0;
			// article-list-id-16-page- 16��С˵- RQС˵��
			// 6��ͼƬ---ZPTP
			String url = "http://www.laossee.com/article-list-id-" + cate + "-page-" + m + ".html";
			String info = GetWeb.getContentFormUrl(url);
			String reg = "article-show-id-\\d{6}";
			List<String> result = RegContent.GetallURL(RegContent.GetCon(reg, info));
			for (int i = 0; i < result.size(); i++) {
				String cont = GetWeb.getContentFormUrl(result.get(i));
				List<String> titles = RegContent.GetCon("<title>.*?</title>", cont);
				String reggg = "<br />.*?<br />";
				List<String> Content = RegContent.GetCon(reggg, cont);
				String conts = "";
				for (int f = 0; f < Content.size(); f++) {
					conts += Content.get(f);
				}
				conts = conts.replace("<br />", "");
				for (int j = 0; j < titles.size(); j++) {
					count++;
					String title = RegContent.GetOneCon(">.*?<", titles.get(j));
					title = title.replace("/", "").replace(" ", "");
					title = "txt/" + title.substring(1, title.length() - 1).replace('(', ' ').trim().replace(')', ' ')
							.trim().replace('��', ' ').trim().replace(' ��', ' ').trim() + ".txt";
					SaveTxt.Sava(title, conts);
					System.out.println("��" + m + "ҳ��" + count + "��" + title);
				}
			}
		}
	}
}