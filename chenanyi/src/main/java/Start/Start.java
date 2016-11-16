package Start;

import Get.GetImg;

public class Start {
	/**
	 * @author 陈安一
	 * @see 网络爬虫
	 * @功能 获取***** 哈哈哈哈哈哈
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			java.util.Scanner scanner = new java.util.Scanner(System.in);
			System.out.println("**************************************************************");
			System.out.println("**************************************************************");
			System.out.println("第一个参数，分类（6，7，13），小说（14，15，16）");
			System.out.println("**************************************************************");
			System.out.println("第二个参数，获取的总页码数，总页码数>=1");
			System.out.println("**************************************************************");
			System.out.println("第三个参数，保存的地址 ： 格式 f:\\\\image4\\\\");
			System.out.println("**************************************************************");
			System.out.println("请输入第一个参数");
			int value = scanner.nextInt();
			System.out.println("请输入第二个参数");
			int value1 = scanner.nextInt();
			System.out.println("请输入第三个参数");
			String line = scanner.next();
			System.out.println("开始执行");
			GetImg.Getimg(value, value1, line);
			System.out.println("执行完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
