package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;


import com.training.bean.RegisterBean;
import com.training.dao.RealestateDAO;

public class RegisterDataProvider {
	

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<RegisterBean> list = new RealestateDAO().getRegister();
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(RegisterBean temp : list){
			Object[]  obj = new Object[3]; 
			obj[0] =temp.getEmailId();
			obj[1]= temp.getFirstname();
			obj[2] =temp.getLastName(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	

}
