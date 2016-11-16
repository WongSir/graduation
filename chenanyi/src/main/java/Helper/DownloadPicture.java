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
	 * @author �°�һ
	 * @���� ����url������·����count(����ı��⣬ֱ�������ֱ���)
	 * ͼƬ��Ĵ���--����ͼƬ���浽����
	 */
	public static void down(String url, String path, int count) {
		// ����URL
		URL img_url;
		URLConnection con;
		try {
			img_url = new URL(url);
			con = img_url.openConnection();
			// ��������ʱΪ5s
			con.setConnectTimeout(5 * 1000);
			// ������
			InputStream is = con.getInputStream();
			// 1K�����ݻ���
			byte[] bs = new byte[1024];
			// ��ȡ�������ݳ���
			int len;
			// ������ļ���
			File sf = new File(path);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			String filename = count + ".jpg";
			OutputStream os;
			try {
				os = new FileOutputStream(sf.getPath() + "\\" + filename);
				// ��ʼ��ȡ
				while ((len = is.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
				// ��ϣ��ر���������
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