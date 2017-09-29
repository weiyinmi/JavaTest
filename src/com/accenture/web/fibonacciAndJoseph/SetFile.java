package com.accenture.web.fibonacciAndJoseph;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetFile {
	private static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";

	public String setTxtFile(String outputDirPath){
       SimpleDateFormat form = new SimpleDateFormat(DATE_FORMAT);
       Date date = new Date();
       String time = form.format(date);
   
 //      File file = new File(outputDirPath+Thread.currentThread().getStackTrace()[2].getClassName()+time+".txt");
       File file = new File(outputDirPath+Thread.currentThread().getStackTrace()[2].getClassName()+time+".xml");
       String fileString =file.toString();
       return fileString;
	}
}
