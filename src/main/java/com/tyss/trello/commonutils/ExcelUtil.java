package com.tyss.trello.commonutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.util.WebActionUtil;

/**
 * Description: Implements generic methods to perform operations on Excel
 * Workbook.
 * 
 * @author: Sajal, Vikas
 */
public class ExcelUtil {
	/* Private constructor */
	private ExcelUtil() {

	}

	/**
	 * Description: Writes single row data to Excel
	 * 
	 * @author:Sajal, Vikas
	 * @param xlPath
	 * @param sheetName
	 * @param mapData
	 */
	public static synchronized void writeSingleRowDataToExcel(String xlPath, String sheetName,
			Map<String, String> mapData) {

		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(new File(xlPath));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
		Sheet sh = wb.getSheet(sheetName);

		Row r = null;
		Cell c = null;

		if (sh != null) {
			r = sh.getRow(sh.getLastRowNum() + 1);
			if (r == null) {
				r = sh.createRow(sh.getLastRowNum() + 1);
			}

			Set<String> keyData = mapData.keySet();
			int i = 0;
			for (String singledata : keyData) {
				String format = mapData.get(singledata);

				c = r.getCell(i);

				if (c == null) {
					c = r.createCell(i);
				}
				if (format.equalsIgnoreCase("number")) {

					c.setCellType(CellType.NUMERIC);
					c.setCellValue(Integer.parseInt(singledata));
				} else if (format.equalsIgnoreCase("date")) {
					CellStyle cs = wb.createCellStyle();
					CreationHelper ch = wb.getCreationHelper();
					cs.setDataFormat(ch.createDataFormat().getFormat("M/d/yyyy"));

					try {
						Date date = new SimpleDateFormat("M/d/yyyy").parse(singledata);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					c.setCellValue(singledata);
					c.setCellStyle(cs);

				} else if (format.equalsIgnoreCase("string")) {
					c.setCellValue(singledata);
				}

				i++;
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(xlPath));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			wb.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		String arr[] = getRowData1(xlPath, sheetName, 1);
		WebActionUtil.info(Arrays.toString(arr));

	}

	/**
	 * Description: Write data to Excel
	 * 
	 * @author:Sajal, Vikas
	 * @param fileWithPath
	 * @param data
	 */
	public static synchronized void writeToTextFile(String fileWithPath, String data) {
		try {
			Files.write(Paths.get(fileWithPath), data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description: Convert Array To Formatted String
	 * 
	 * @author:Sajal, Vikas
	 * @param twoDArray
	 */
	public static synchronized String convertArrayToFormattedString(String[][] twoDArray) {
		String data = "";
		for (int i = 0; i < twoDArray.length; i++) {
			for (int j = 0; j < twoDArray[i].length; j++) {
				if (twoDArray[i].length - j != 1) {
					data = data + twoDArray[i][j].trim() + "\t";
				} else {
					data = data + twoDArray[i][j];
				}
			}
			data = data + "\n";
		}

		return data;
	}

	/**
	 * Description: Method to get the sheet name
	 * 
	 * @author:Sajal, Vikas
	 * @param xlPath
	 */
	public synchronized static String getSheetName(String xlPath) {
		String sheetName = null;
		try {
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			sheetName = wb.getSheetName(0);
			wb.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheetName;
	}

	/**
	 * Description: Method to get row data from excel using fillo
	 * 
	 * @author:Sajal, Vikas
	 * @param xlPath
	 * @param sql
	 */
	public static synchronized String[][] getRowDataFromExcelUsingFillo(String xlPath, String sql) {

		Fillo fillo = new Fillo();
		String[][] tableValues = null;
		Connection connection;
		try {
			connection = fillo.getConnection(xlPath);
			Recordset recordset = connection.executeQuery(sql);
			// WebActionUtil.info("No of rows are " + recordset.getCount());
			ArrayList<String> lstFieldNames = recordset.getFieldNames();
			// WebActionUtil.info("No of cols are " + lstFieldNames.size());
			tableValues = new String[recordset.getCount()][lstFieldNames.size()];
			int rowno = 0;
			while (recordset.next()) {
				int colno = 0;
				for (String fieldName : lstFieldNames) {
					tableValues[rowno][colno++] = recordset.getField(fieldName);
					// WebActionUtil.info(recordset.getField(fieldName) + " ");
				}
				rowno++;
			}
			recordset.close();
			connection.close();
		} catch (FilloException e) {
			WebActionUtil.error("Fillo exception");
			e.printStackTrace();
		}
		return tableValues;
	}

	/**
	 * Description:Fetches the row count in the specified sheet
	 * 
	 * @author:Sajal, Vikas
	 * @param sPath
	 * @param sSheet
	 */
	public static synchronized int getRowCount(String sPath, String sSheet) {
		int iRowNum = 0;
		try {
			FileInputStream fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			iRowNum = sht.getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRowNum;
	}

	/**
	 * Description:Fetches the column count in the specified sheet
	 * 
	 * @author:Sajal, Vikas
	 * @param sSheet
	 * @parm sPath
	 */
	public static synchronized int getColumnCount(String sPath, String sSheet) {
		int colnum = 0;
		try {
			FileInputStream fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			colnum = sht.getRow(0).getPhysicalNumberOfCells();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colnum;
	}

	/**
	 * Description:Fetches the data from the specified cell
	 * 
	 * @author:Sajal, Vikas
	 * @param xlPAth
	 * @param sheetName
	 * @param rowNo
	 * @param colNo
	 */
	public static synchronized String getCellData(String xlPAth, String sheetName, int rowNo, int colNo) {
		DataFormatter dataFormatter = new DataFormatter();

		int iRowNum = 0;
		String data = null;
		try {
			FileInputStream fis = new FileInputStream(xlPAth);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sheetName);
			iRowNum = sht.getLastRowNum();
			if (rowNo <= iRowNum) {
				Cell cell = sht.getRow(rowNo).getCell(colNo);
				data = dataFormatter.formatCellValue(cell);
			} else {
				BaseTest.logger.info("Please provide the valid row count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Description : Fetches the specified row data from the Excel sheet
	 * 
	 * @author:Sajal, Vikas
	 * @param sFilePath
	 * @param sSheet
	 * @param rowNo
	 */
	public static synchronized String[] getRowData1(String sFilePath, String sSheet, int rowNo) {
		DataFormatter dataFormatter = new DataFormatter();
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iCellNum = 0;
			try {
				iCellNum = sht.getRow(rowNo).getPhysicalNumberOfCells();
			} catch (Exception e) {
				return null;
			}
			sData = new String[iCellNum];
			for (int j = 0; j < iCellNum; j++) {
				Cell cell = sht.getRow(rowNo).getCell(j);

				if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					if (DateUtil.isCellDateFormatted(cell)) {

						SimpleDateFormat DateFor = new SimpleDateFormat("d/M/yyyy");
						String stringDate = DateFor.format(cell.getDateCellValue());

						sData[j] = stringDate;
					} else {
						sData[j] = dataFormatter.formatCellValue(cell);
					}
				} else {
					sData[j] = dataFormatter.formatCellValue(cell);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	/**
	 * Description : Fetches the specified row data from the Excel sheet
	 * 
	 * @author:Sajal, Vikas
	 * @param sFilePath
	 * @param sSheet
	 * @param rowNo
	 */
	public static synchronized String[] getRowData(String sFilePath, String sSheet, int rowNo) {
		DataFormatter dataFormatter = new DataFormatter();
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iCellNum = sht.getRow(rowNo).getPhysicalNumberOfCells();
			sData = new String[iCellNum];
			for (int j = 0; j < iCellNum; j++) {
				Cell cell = sht.getRow(rowNo).getCell(j);
				sData[j] = dataFormatter.formatCellValue(cell);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return sData;
	}

	/**
	 * Description :Fetches the specified column data from the Excel sheet
	 * 
	 * @author:Sajal, Vikas
	 * @param sFilePath
	 * @param sSheet
	 * @param colno
	 */
	public static synchronized String[] getColoumData(String sFilePath, String sSheet, int colno) {
		DataFormatter dataFormatter = new DataFormatter();
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			sData = new String[iRowNum];

			for (int i = 1; i <= iRowNum; i++) {
				Cell cell = sht.getRow(i).getCell(colno);
				sData[i - 1] = dataFormatter.formatCellValue(cell);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	/**
	 * Description Fetches the column index for a given value of a cell value from
	 * the first/header row.
	 * 
	 * @author:Sajal, Vikas
	 * @param filepath
	 * @param sSheet
	 * @param colName
	 */
	public static synchronized int getColoumIndex(String filepath, String sSheet, String colName) {
		String[] firstrow = getRowData(filepath, sSheet, 0);
		int index = 0;
		for (int i = 0; i < firstrow.length; i++) {
			if (firstrow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Description: Checks if an array contains blank/default value.
	 * 
	 * @author:Sajal, Vikas
	 * @param data
	 * @return blank
	 */
	public static synchronized boolean doesArrayContainsBlank(String[] data) {
		boolean blank = false;
		for (int i = 0; i < data.length; i++) {
			if (data[i].isEmpty() || data[i] == null) {
				blank = true;
				break;
			}
		}
		return blank;
	}

	/**
	 * Description :Reads the Excel data from a specified Excel sheet based on
	 * TestCaseId.
	 * 
	 * @author:Sajal, Vikas
	 * @param sFilePath
	 * @param sSheet
	 * @param sTestCaseId
	 * @return SData
	 */
	public static synchronized String[] toReadExcelData(String sFilePath, String sSheet, String sTestCaseId) {
		DataFormatter dataFormatter = new DataFormatter();
		String sData[] = null;
		try {
			// File Read
			FileInputStream fis = new FileInputStream(sFilePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseId)) {
					int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
					sData = new String[iCellNum];
					for (int j = 0; j < iCellNum; j++) {
						Cell cell = sht.getRow(i).getCell(j);
						sData[j] = dataFormatter.formatCellValue(cell);
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return sData;

	}

	/**
	 * Description :Fetches the column index
	 * 
	 * @author:Sajal, Vikas
	 * @param filePath
	 * @param sSheet
	 * @param colName
	 * @param firstRowName
	 * @return index
	 */
	public static synchronized int getColoumIndex(String filepath, String sSheet, String colName, String firstRowName) {
		String[] firstRow = ExcelUtil.toReadExcelData(filepath, sSheet, firstRowName);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Description: Fetches the cell count in the specified row.
	 * 
	 * @author:Sajal, Vikas
	 * @param sPath
	 * @param sSheet
	 * @param row
	 * @return column
	 */
	public static synchronized int getCellCount(String sPath, String sSheeet, int row) {
		int colnum = 0;
		try {
			FileInputStream fis = new FileInputStream(sPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheeet);
			colnum = sht.getRow(row).getPhysicalNumberOfCells();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colnum;
	}

	/**
	 * Description: This method is used to delete the file if at all it exists to
	 * avoid interaction when New ExcelFile is generated
	 * 
	 * @author Sajal, Vikas
	 * @param filePath
	 * @param fileName
	 */
	private static void deleteFileIfExists(String filePath, String fileName) {
		String[] fileNameSplit = fileName.split("[.]");
		String newFileName = fileNameSplit[0] + "_1" + ".xlsx";
		Path path = Paths.get(filePath + "\\" + newFileName);
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description: This method returns the row data from Excel sheet for specified
	 * entity
	 * 
	 * @author: Sajal
	 * @param xlPath
	 * @param sheetName
	 * @param entity
	 */
	public static void getRowDataFromSheetEntity(String xlPath, String sheetName, String entity) {
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(xlPath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			try {
				wb = (Workbook) WorkbookFactory.create(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		Sheet sht = wb.getSheet(sheetName);
		if (sht != null) {
		}
	}

	/**
	 * Description: This method returns the no of data of the specified row
	 * 
	 * @author:Sajal
	 * @param xlPath
	 * @param sheetName
	 * @param rowno
	 */
	public static int getEntityandDataRowCount(String xlPath, String sheetName, int rowno) {
		int rowcount = 0;
		while (true) {
			try {
				String[] values = ExcelUtil.getRowData1(xlPath, sheetName, rowno++);
				if (values == null)
					break;
				rowcount++;
			} catch (Exception e) {
				break;
			}
		}
		return rowcount;
	}

}