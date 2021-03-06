package com.accenture.web.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.accenture.web.fibonacciAndJoseph.Problem;
import com.accenture.web.fibonacciAndJoseph.SetFile;
import com.accenture.web.forxml.FibonacciForXml;
import com.accenture.web.forxml.FibonacciSequenceRequest;

public class Fibonacci implements Problem {
	
	 private static final String POSITIVE_NUMBER_REGEX = "^\\d+$";
	Logger logger  =  Logger.getLogger(Fibonacci.class ); 

	@Override
	public void solve(String inputFilePath, String outputDirPath) {
		
		FibonacciForXml ffx = new FibonacciForXml();
		List<BigInteger> value = new ArrayList<>();
		
		SetFile stf = new SetFile();
		BufferedReader in =null;
		PrintWriter out=null;
	     
		try{		
            
			in = new BufferedReader(new FileReader(inputFilePath));
			
			out = new PrintWriter(new FileWriter(stf.setTxtFile(outputDirPath)));
			
	        JAXBContext context = JAXBContext.newInstance(com.accenture.web.forxml.FibonacciSequenceRequest.class);  //read xml file
	        Unmarshaller unmarshaller = context.createUnmarshaller(); 
	        Object object = unmarshaller.unmarshal(in);
	        
	        FibonacciSequenceRequest fsr = (FibonacciSequenceRequest)object;
	         	        
		    String s = fsr.getLength();
		    
	        if(s.matches( POSITIVE_NUMBER_REGEX )){
	        	
	          for(int i=0;i<Integer.parseInt(fsr.getLength());i++){
	        	  if(i<=92){
	        		  Long val =fibonacciFunction(i);
	        		  BigInteger big = new BigInteger(val.toString());
	        		  value.add(big);
	        	  }else{
	        		  value.add(fibonacciBigN(i));
	        	  }	        		 	         
			     }
	        }else{
	        	logger.error("IllegalArgumentException");
	        	throw new IllegalArgumentException("The input data for Fibonacci is wrong!");
	        }		
		     
		     ffx.setSequence(value);
		     
		     logger.debug("length:" + Integer.parseInt(fsr.getLength()));
		     logger.debug("The fibonacci sequence" +value);
		     
	         JAXBContext jaxbContext = JAXBContext.newInstance(FibonacciForXml.class);  
	         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
	            // output pretty printed  
	         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
	         jaxbMarshaller.marshal(ffx, out); 		   
			
		} catch (JAXBException e) {
			logger.warn(e);
		} catch ( FileNotFoundException e) {
			logger.warn(e);
		} catch (IOException e) {
			logger.warn(e);
		}finally{      
			if(in !=null){
		    	  try {   		  
					in.close();
				} catch (IOException e) {			
					logger.warn(e);
				}
		    	  }
		    	  if(out!=null){
		    	  out.close();
		    	  }
			}			
		}
    
	/**
	 * Achieve fibonacci sequence,just input a length,but the max length of fibonacciFunction is 92.When n>92,we use another function to achieve the rest of sequence.
	 * @param n the length of fibonacci sequence
	 * @return a fibonacci sequence
	 */
	public long fibonacciFunction(int n){
		if(n==0){
			return 0;
		}
		else if(n>1){
			long a, b = 1;  
            n--;  
            a = n & 1;  
            n /= 2;  
            while (n-- > 0) {  
                a += b;  
                b += a;  
            }  
			return b;
		}
		return n;
	}	
	
	/**
	 * Achieve fibonacci sequence when n>92.
	 * @param n the length of fibonacci sequence
	 * @return a fibonacci sequence
	 */
    public BigInteger fibonacciBigN(int n) {  
        if (n > 92) {  
            int m = (n / 2) + (n & 1);  
            BigInteger fm = fibonacciBigN(m);  
            BigInteger fm_1 = fibonacciBigN(m - 1);  
            if ((n & 1) == 1) {  
                return fm.pow(2).add(fm_1.pow(2));  
            } else {  
                return fm_1.shiftLeft(1).add(fm).multiply(fm);  
            }  
        }  
        return BigInteger.valueOf(fibonacciFunction(n));  
    }

}
