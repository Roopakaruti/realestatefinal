package com.training.dataproviders;

import org.testng.annotations.DataProvider;

import com.training.readexcel.ApachePOIExcelRead;

public class RETC061DataProvider {
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RETC_061TestData.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
	

}
