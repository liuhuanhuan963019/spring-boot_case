package com.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.web.multipart.MultipartFile;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import sun.misc.BASE64Encoder;

public class DocExclPDFUtils {
	private static final Integer WORD_TO_PDF_CALL = 17;
	private static final Integer WORD_TO_PDF_QUIT = 0;

    /**
     * 无彈框提示
     * @param wordFile
     * @param targetPath
     * @return
     * @throws Exception
     */
    public static String wordToPdf2(String wordFile, String targetPath, String allpath) throws Exception {
		ActiveXComponent app = null;
		try {
			app = new ActiveXComponent("Word.Application");
			Dispatch documents = app.getProperty("Documents").toDispatch();
			Dispatch document = Dispatch.call(documents, "Open", wordFile, false, true).toDispatch();
			File target = new File(targetPath);
			if (target.exists()) {
				target.delete();
			}
			if(!target.exists()) {
				target.mkdirs();
			}
			Dispatch.call(document, "SaveAs", allpath, WORD_TO_PDF_CALL);
			Dispatch.call(document, "Close", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			app.invoke("Quit", WORD_TO_PDF_QUIT);
		}
		return allpath;
	}

    public static String base64ForFile(File file) throws Exception {
    	FileInputStream in = new FileInputStream(file);
    	byte[] b = new byte[(int) file.length()];
    	in.read(b);
    	in.close();
    	return new BASE64Encoder().encode(b);
    }


}
