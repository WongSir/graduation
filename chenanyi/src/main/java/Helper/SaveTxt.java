package Helper;

import java.io.FileWriter;
import java.io.IOException;

public class SaveTxt {
	/**
	 * @author 陈安一
	 * @功能 将小说保存到本地中
	 * @param url
	 * @param title
	 *            例如"noexists.txt"
	 * @param cont
	 * @return
	 */
	public static void Sava(String title, String cont) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(title, true);
			fileWriter.write(cont);
			fileWriter.flush();
			;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}