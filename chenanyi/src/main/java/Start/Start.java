package Start;

import Get.GetImg;

public class Start {
	/**
	 * @author �°�һ
	 * @see ��������
	 * @���� ��ȡ***** ������������
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			java.util.Scanner scanner = new java.util.Scanner(System.in);
			System.out.println("**************************************************************");
			System.out.println("**************************************************************");
			System.out.println("��һ�����������ࣨ6��7��13����С˵��14��15��16��");
			System.out.println("**************************************************************");
			System.out.println("�ڶ�����������ȡ����ҳ��������ҳ����>=1");
			System.out.println("**************************************************************");
			System.out.println("����������������ĵ�ַ �� ��ʽ f:\\\\image4\\\\");
			System.out.println("**************************************************************");
			System.out.println("�������һ������");
			int value = scanner.nextInt();
			System.out.println("������ڶ�������");
			int value1 = scanner.nextInt();
			System.out.println("���������������");
			String line = scanner.next();
			System.out.println("��ʼִ��");
			GetImg.Getimg(value, value1, line);
			System.out.println("ִ�����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
