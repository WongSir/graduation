package Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPicture {
	/**
	 * @author 陈安一
	 * @功能 根据url，保存路径，count(保存的标题，直接以数字保存)
	 * 图片类的处理--下载图片保存到本地
	 */
	public static void down(String url, String path, int count) {
		// 构造URL
		URL img_url;
		URLConnection con;
		try {
			img_url = new URL(url);
			con = img_url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(path);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			String filename = count + ".jpg";
			OutputStream os;
			try {
				os = new FileOutputStream(sf.getPath() + "\\" + filename);
				// 开始读取
				while ((len = is.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
				// 完毕，关闭所有链接
				os.close();
				is.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}