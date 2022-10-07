package com.tyss.trello.dataproviders;

import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

import com.codoid.products.exception.FilloException;
import com.tyss.trello.commonutils.ExcelUtil;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

/**
 * Description: This class implements data provider logic which fetches the data
 * from the Excel sheet.
 * 
 * @author Sajal, Vikas
 */
public class DataProviderFactory {

	/**
	 * Description: This data provider is used to fetch single row data from excel
	 * 
	 * @author:Sajal, Vikas
	 * @param method
	 */
	@DataProvider(name = "data")
	public static Object[][] getData(Method method) {
		DataProviderFile file = method.getAnnotation(DataProviderFile.class);
		String filepath = System.getProperty("user.dir") + file.file();
		int noofrows = ExcelUtil.getRowCount(filepath, file.sheet());
		int noofcols = ExcelUtil.getColumnCount(filepath, file.sheet());
		Object[][] workflowdata = new Object[noofrows][noofcols];
		for (int i = 1; i <= noofrows; i++) {
			String data[] = ExcelUtil.getRowData(filepath, file.sheet(), i);
			for (int j = 0; j < data.length; j++) {
				workflowdata[i - 1][j] = data[j];
			}
		}
		return workflowdata;
	}

	/**
	 * Description: This data provider is used to fetch data from excel using sql query
	 * 
	 * @author:Sajal, Vikas
	 * @param method
	 */
	@DataProvider(name = "data1")
	public static Object[][] getFilterRowData(Method method)  {
		DataProviderFileRowFilter file = method.getAnnotation(DataProviderFileRowFilter.class);
		String filepath = System.getProperty("user.dir") + file.file();
		String filterData[][] = ExcelUtil.getRowDataFromExcelUsingFillo(filepath, file.sql());
		return filterData;
	}

}
