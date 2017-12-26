package com.zxtech.support.excel;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelWriterCallback {
	void doInPoi(Workbook workbook);
}
