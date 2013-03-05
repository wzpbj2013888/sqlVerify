package spring.ftp.I;

import java.io.IOException;

public interface FtpI {
	public void setFtp(String ip, int port, String username, String password)
			throws IOException;

	public void login() throws IOException;

	public String queryChangeNo(String changeNo);

	public String[] getSql(String changeNo);

	public void downFile(String SourceFileName, String destinationFileName);
}
