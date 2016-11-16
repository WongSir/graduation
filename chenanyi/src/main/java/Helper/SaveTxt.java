package Helper;

import java.io.FileWriter;
import java.io.IOException;

public class SaveTxt {
	/**
	 * @author �°�һ
	 * @���� ��С˵���浽������
	 * @param url
	 * @param title
	 *            ����"noexists.txt"
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