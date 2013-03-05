package spring.ftp.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UnRARFile {

	/**
	 * 
	 * ϵͳ��װ��winRARλ��
	 */

	private static final String WINRAR_PATH = "C:\\Program Files\\WinRAR\\WinRAR.exe";

	/**
	 * 
	 * ��ѹ����
	 * 
	 * @param rarFilePath
	 *            rarѹ���ļ���·��
	 * 
	 * @param unFilePath
	 *            Ҫ��ѹ��ָ����·��
	 * 
	 * @throws IOException
	 *             IO�쳣
	 */

	public static void unRARFile(String rarFilePath, String unFilePath)
			throws IOException

	{

		File f = new File(unFilePath);

		if (!f.exists()) // �������ָ����ѹ��·�������ڣ�����Ŀ¼

		{

			f.mkdirs();

		}

		String cmd = WINRAR_PATH + " e -o+ " + rarFilePath + " " + unFilePath; // ��Ҫִ�е�����

		Runtime runtime = Runtime.getRuntime(); // �õ��������

		Process process = runtime.exec(cmd); // ��ȡִ����������з��ص���

		/**
		 * 
		 * �����Ǵ�ӡ���������ݣ��鿴�Ƿ����쳣
		 */

		InputStreamReader isr = new InputStreamReader(process.getInputStream());

		BufferedReader br = new BufferedReader(isr);

		String str = null;

		br.close();

	}

}
