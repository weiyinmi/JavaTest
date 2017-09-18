package com.webtest.fibonacciAndJoseph;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Test {
	private static final String FILE_PATH = "path.properties";
	private static final String LOG4J_CONFIGURE = "log4j.properties";

	public static void main(String[] args) throws IOException{
		
	 PropertyConfigurator.configure( LOG4J_CONFIGURE );  //test log
	 Logger logger  =  Logger.getLogger(Test.class );  
	 	 
	 Properties prop = new Properties(); 
	 InputStream is = null;
	 
	 Fibonacci f = new Fibonacci();
	 Joseph j = new Joseph();
	 
	 try{
	 is = new BufferedInputStream (new FileInputStream(FILE_PATH));
     prop.load(is);
     
     String inputFilePath1 = (prop.getProperty("finput"));
     String inputFilePath2 = (prop.getProperty("jinput"));
     String outputDirPath = (prop.getProperty("toutput"));
     
    for(int i=0;i<args.length;i++){
	  switch(args[i]){
	    case "f":    	
		   f.solve(inputFilePath1,outputDirPath);
		   logger.debug( " The fibonacci is done ! " ); 
		   break;
	    case "j":
		   j.solve(inputFilePath2,outputDirPath);
		   logger.debug( " The Joseph is done ! " ); 
		   break;
	    default:
		   System.out.println("This codes can not run!");
	 }
     }
	 }catch(Exception e){
		 e.printStackTrace();
	 }finally{
		 is.close();
	 }	        
	}	
}
