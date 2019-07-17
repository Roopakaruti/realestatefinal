package com.training.dataproviders;

import org.testng.annotations.DataProvider;

import com.training.readexcel.ApachePOIExcelRead;

public class RETC064DataProvider {


	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RETC_064TestData.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
	


}
