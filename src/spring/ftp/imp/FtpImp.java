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
	 * 下载ftp上变更文件包所在目录
	 */
	private String SourceFilePath = "/";
	/**
	 * ftp变更文件下载到应用所在机器的目录
	 */
	private String destinationFilePath = "D:/ftpTest";
	/**
	 * 将变更文件包解压到的目录
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
	 * 设置ftp的ip，端口，用户名，密码；
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
	 * 登录链接
	 */
	public void login() throws IOException {

		ftpclient.login(userName, PassWord);

	}

	/**
	 * 检查变更号是否存在
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
	 * 下载ftp中的变更文档，获取文档中执行的sql
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

		return sql.replace("；", ";").split(";");
	}

	/**
	 * 下载指定变更需求到应用程序本地
	 */
	public void downFile(String SourceFileName, String destinationFileName) {
		// TODO Auto-generated method stub
		try {
			
			File f = new File(destinationFilePath);

			if (!f.exists()) // 如果发现指定解压的路径不存在，创建目录

			{

				f.mkdirs();

			}
			ftpclient.binary(); // 一定要使用二进制模式
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
