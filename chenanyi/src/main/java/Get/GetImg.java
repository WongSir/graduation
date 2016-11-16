package Get;

import java.util.ArrayList;
import java.util.List;

import Helper.DownloadPicture;
import Helper.GetWeb;
import Helper.RegContent;

public class GetImg {
	/**
	 * @author �°�һ��
	 * @param cate
	 *            ���6��ZpTp
	 * @param pagecount
	 *            ��ȡ��ҳ��
	 * @return List<String> ͼƬ����
	 * ���ݻ�ȡ��url�����ص�html���룬��ȡС˵����ͼƬ���浽�ļ���
	 */
	public static void Getimg(int cate, int pagecount, String path) {
		int count = 0;
		for (int m = 1; m <= pagecount; m++) {
			// article-list-id-16-page- 16��С˵- RQС˵��
			// 6��ͼƬ---ZPTP
			String url = "http://www.laossee.com/article-list-id-" + cate + "-page-" + m + ".html";
			String info = GetWeb.getContentFormUrl(url);
			String reg = "article-show-id-\\d{6}";
			List<String> result = RegContent.GetallURL(RegContent.GetCon(reg, info));
			for (int i = 0; i < result.size(); i++) {
				String cont = GetWeb.getContentFormUrl(result.get(i));
				List<String> img_urls = RegContent.GetCon("<img src=\"(.*?)/>", cont);
				for (int j = 0; j < img_urls.size(); j++) {
					count++;
					String temp = img_urls.get(j).substring(10);
					int index = temp.indexOf("\"");
					temp = temp.substring(0, index);
					DownloadPicture.down(temp, path, count);
					System.out.println(count + "\tOK");
				}
			}
		}
	}

	/**
	 * @author �°�һ��
	 * @param cate
	 *            ���6��ZpTp
	 * @param pagecount
	 *            ��ȡ��ҳ��
	 * @return List<String> ͼƬ����
	 */
	public static List<String> GetOnePageimg(int cate, int page) {
		List<String> img_url = new ArrayList<String>();
		// article-list-id-16-page- 16��С˵- RQС˵��
		// 6��ͼƬ---ZPTP
		String url = "http://www.laossee.com/article-list-id-" + cate + "-page-" + page + ".html";
		String info = GetWeb.getContentFormUrl(url);
		String reg = "article-show-id-\\d{6}";
		List<String> result = RegContent.GetallURL(RegContent.GetCon(reg, info));
		for (int i = 0; i < result.size(); i++) {
			String cont = GetWeb.getContentFormUrl(result.get(i));
			List<String> img_urls = RegContent.GetCon("<img src=\"(.*?)/>", cont);
			for (int j = 0; j < img_urls.size(); j++) {
				String temp = img_urls.get(j).substring(10);
				int index = temp.indexOf("\"");
				temp = temp.substring(0, index);
				System.out.println(temp);
				img_url.add(temp);
			}
		}
		return img_url;
	}
}