package com.orangehrm.tests;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class DummyClass2 extends BaseClass{
	@Test
	public void dummyTest2() {
		 String title = driver.getTitle();
		 assert title.equals("OrangeHRM"):"Test Failed - Title is Not Matching";
		 System.out.println("Test Passed - Title is Matching");
		 
		
	}

}
