package spring.ftp.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import spring.ftp.I.FtpI;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;

public class FtpImp implements FtpI {
	/**
	 * ����ftp�ϱ���ļ�������Ŀ¼
	 */
	private String SourceFilePath = "/";
	/**
	 * ftp����ļ����ص�Ӧ�����ڻ�����Ŀ¼
	 */
	private String destinationFilePath = "D:/ftpTest";
	/**
	 * ������ļ�����ѹ����Ŀ¼
	 */
	private String unRarPath = "D:/ftpTestUnRar";
	private FtpClient ftpclient;
	private String ipAddress;
	private int ipPort;
	private String userName;
	private String PassWord;

	public String getDestinationFilePath() {
		return destinationFilePath;
	}

	public void setDestinationFilePath(String destinationFilePath) {
		this.destinationFilePath = destinationFilePath;
	}

	public String getUnRarPath() {
		return unRarPath;
	}

	public void setUnRarPath(String unRarPath) {
		this.unRarPath = unRarPath;
	}

	public String getSourceFilePath() {
		return SourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		SourceFilePath = sourceFilePath;
	}

	public FtpClient getFtpclient() {
		return ftpclient;
	}

	public void setFtpclient(FtpClient ftpclient) {
		this.ftpclient = ftpclient;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getIpPort() {
		return ipPort;
	}

	public void setIpPort(int ipPort) {
		this.ipPort = ipPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	/**
	 * ����ftp��ip���˿ڣ��û��������룻
	 */
	public void setFtp(String ip, int port, String username, String password)
			throws IOException {

		ipAddress = new String(ip);

		ipPort = port;

		ftpclient = new FtpClient(ipAddress, ipPort);

		// ftpclient = new FtpClient(ipAddress);

		userName = new String(username);

		PassWord = new String(password);

	}

	/**
	 * ��¼����
	 */
	public void login() throws IOException {

		ftpclient.login(userName, PassWord);

	}

	/**
	 * ��������Ƿ����
	 */
	public String queryChangeNo(String changeNo) {
		try {
			this.login();
			ftpclient.ascii();
			TelnetInputStream tis = ftpclient.nameList(SourceFilePath);
			byte[] bnames = new byte[1024];
			String names = "";
			while (tis.read(bnames) != -1) {
				names = names + new String(bnames, "UTF-8");
			}
			 ftpclient.closeServer();
			if (names.indexOf(changeNo) >= 0) {
				return "exists";
			}

		} catch (IOException io) {
			io.printStackTrace();
		}

		return "notExists";
	}

	/**
	 * ����ftp�еı���ĵ�����ȡ�ĵ���ִ�е�sql
	 */
	public String[] getSql(String changeNo) {
		String sql = "";
		// TODO Auto-generated method stub
		String names = "";
		String nameList[] = null;
		ArrayList<String> loadNameList = new ArrayList<String>();
		try {
			 this.login();
			ftpclient.ascii();
			TelnetInputStream tis = ftpclient.nameList(SourceFilePath);
			byte[] bnames = new byte[1024];

			while (tis.read(bnames) != -1) {
				names = names + new String(bnames, "UTF-8");
			}
			nameList = names.split("\n");
			for (int i = 0, j = 0; i < nameList.length; i++) {
				if (!nameList[i].equals("")&&nameList[i].indexOf(changeNo) >= 0) {
					this.downFile(this.getSourceFilePath() + nameList[i],
							this.destinationFilePath + nameList[i]);
					loadNameList.add(nameList[i]);
					j++;

				}
			}
			for (int j = 0; j < loadNameList.size(); j++) {
				UnRARFile.unRARFile(
						this.destinationFilePath + loadNameList.get(j),
						this.unRarPath);
			}
			for (int m = 0; m < loadNameList.size(); m++) {
				Word word = new Word();
				String temps = "";
				temps = word.getSQL(this.unRarPath,changeNo);
				sql = sql + temps;
			}

		} catch (IOException io) {
			io.printStackTrace();
			return new String[]{io.getMessage()};
		}

		return sql.replace("��", ";").split(";");
	}

	/**
	 * ����ָ���������Ӧ�ó��򱾵�
	 */
	public void downFile(String SourceFileName, String destinationFileName) {
		// TODO Auto-generated method stub
		try {
			
			File f = new File(destinationFilePath);

			if (!f.exists()) // �������ָ����ѹ��·�������ڣ�����Ŀ¼

			{

				f.mkdirs();

			}
			ftpclient.binary(); // һ��Ҫʹ�ö�����ģʽ
			TelnetInputStream ftpIn = ftpclient.get(SourceFileName);

			byte[] buf = new byte[204800];

			int bufsize = 0;

			FileOutputStream ftpOut = new FileOutputStream(destinationFileName);

			while ((bufsize = ftpIn.read(buf, 0, buf.length)) != -1) {

				ftpOut.write(buf, 0, bufsize);

			}

			ftpOut.close();

			ftpIn.close();

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

}
