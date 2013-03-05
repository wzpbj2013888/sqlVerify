package spring.ftp.imp;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Word {
	public String getSQL(String path, String changeNo) {

		String sql = "";
		try {
			File file = new File(path);
			File files[] = file.listFiles();

			for (File f : files) {
				if (f.getName().indexOf(changeNo) >= 0) {
					sql = sql + this.get(f);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	public String get(File file) {
		String fileSql = "";
		try {
			FileInputStream in = new FileInputStream(file);// 载入文档
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			HWPFDocument hwpf = new HWPFDocument(pfs);
			Range range = hwpf.getRange();// 得到文档的读取范围
			TableIterator it = new TableIterator(range);
			// 迭代文档中的表格
			while (it.hasNext()) {
				Table tb = (Table) it.next();
				// 迭代行，默认从1开始
				for (int i = 1; i < tb.numRows(); i++) {
					TableRow tr = tb.getRow(i);
					// 迭代列，默认从2开始
					for (int j = 2; j < tr.numCells(); j++) {
						TableCell td = tr.getCell(j);// 取得单元格
						// 取得单元格的内容
						for (int k = 0; k < td.numParagraphs(); k++) {
							Paragraph para = td.getParagraph(k);
							String s = para.text().trim();
							if (!s.toUpperCase().startsWith("SELECT") && k == 0)
								break;
							else if (k != (td.numParagraphs() - 1))
								fileSql = fileSql + s;
							else if (s.endsWith(";")
									&& k == (td.numParagraphs() - 1))
								fileSql = fileSql + s;
							else
								fileSql = fileSql + s + ";";
						} // end for
					} // end for
				} // end for
			} // end while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileSql;
	}
}
