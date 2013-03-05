package spring.ftp.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UnRARFile {

	/**
	 * 
	 * 系统安装的winRAR位置
	 */

	private static final String WINRAR_PATH = "C:\\Program Files\\WinRAR\\WinRAR.exe";

	/**
	 * 
	 * 解压方法
	 * 
	 * @param rarFilePath
	 *            rar压缩文件的路径
	 * 
	 * @param unFilePath
	 *            要解压到指定的路径
	 * 
	 * @throws IOException
	 *             IO异常
	 */

	public static void unRARFile(String rarFilePath, String unFilePath)
			throws IOException

	{

		File f = new File(unFilePath);

		if (!f.exists()) // 如果发现指定解压的路径不存在，创建目录

		{

			f.mkdirs();

		}

		String cmd = WINRAR_PATH + " e -o+ " + rarFilePath + " " + unFilePath; // 需要执行的命令

		Runtime runtime = Runtime.getRuntime(); // 得到命令对象

		Process process = runtime.exec(cmd); // 获取执行命令过程中返回的流

		/**
		 * 
		 * 下面是打印出流的内容，查看是否有异常
		 */

		InputStreamReader isr = new InputStreamReader(process.getInputStream());

		BufferedReader br = new BufferedReader(isr);

		String str = null;

		br.close();

	}

}
