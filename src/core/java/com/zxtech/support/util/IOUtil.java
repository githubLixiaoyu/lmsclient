package com.zxtech.support.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {

	private static Logger log = LoggerFactory.getLogger(IOUtil.class);

	public static void writeFile(File file, InputStream is) throws IOException {
		FileOutputStream fout = new FileOutputStream(file);
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] cacheArray = new byte[2048];
		int size;
		while ((size = bis.read(cacheArray)) != -1) {
			fout.write(cacheArray, 0, size);
		}

		fout.close();
		bis.close();
	}
}
