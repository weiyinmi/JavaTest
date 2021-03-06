package com.accenture.web.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.accenture.web.exception.BusinessException;
import com.accenture.web.fibonacciAndJoseph.Problem;
import com.accenture.web.fibonacciAndJoseph.SetFile;
import com.accenture.web.forxml.JosephForXml;
import com.accenture.web.forxml.JosephProblemRequest;

public class Joseph implements Problem {

	Logger logger = Logger.getLogger(Joseph.class);

	@Override
	public void solve(String inputFilePath, String outputDirPath) {

		SetFile stf = new SetFile();
		BufferedReader in = null;
		PrintWriter out = null;

		JosephForXml jfx = new JosephForXml();

		try {
			in = new BufferedReader(new FileReader(inputFilePath));
			out = print(outputDirPath, stf);

			JAXBContext context = JAXBContext.newInstance(JosephProblemRequest.class); // read
																						// xml
																						// file
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Object object = unmarshaller.unmarshal(in);

			JosephProblemRequest jbr = (JosephProblemRequest) object;

			String last = josephFunction(jbr.getCircle().getPeoples(), jbr.getCircle().getStart(),
					jbr.getCircle().getInterval()); // call josephFunvtion
			jfx.setLastPeople(last);

			logger.debug("All peoples:" + jbr.getCircle().getPeoples());
			logger.debug("start:" + jbr.getCircle().getStart());
			logger.debug("interval:" + jbr.getCircle().getInterval());
			logger.debug("The last one:" + last);

			JAXBContext jaxbContext = JAXBContext.newInstance(JosephForXml.class); // output
																					// to
																					// xml
																					// file
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(jfx, out);

			out.flush();

		} catch (IOException e) {

			logger.warn(e);

		} catch (JAXBException e) {
			logger.warn(e);
		} catch (BusinessException e) {
			logger.warn(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.warn(e);
				}
			}
			if (out != null) {
				out.close();
			}
		}
	}

	private PrintWriter print(String outputDirPath, SetFile stf) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(stf.setTxtFile(outputDirPath)));
		return out;
	}

	/**
	 * Achieve joseph problem,when josephFunction is working, peoples would be
	 * removed one by one,until leave the last one.
	 * 
	 * @param list
	 *            input several peoples as a list
	 * @param start
	 *            start index
	 * @param interval
	 *            interval
	 * @return the last person
	 */
	public String josephFunction(List<String> list, int start, int interval) throws BusinessException {

			int a = start - 1; // index
			while (list.size() > 1) {
				a = a + interval;
				a = a % (list.size());
				if (a < 0) {
					list.remove(list.size() - 1);
					a = 0;
				} else {
					list.remove(a);
				}
			}
			return list.get(0);
		
	}
}
