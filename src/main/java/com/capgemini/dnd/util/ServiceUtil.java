package com.capgemini.dnd.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.dao.Constants;

public class ServiceUtil {
	
	static Logger logger = Logger.getRootLogger();
	
	public static Date stringtoDate(java.util.Date m_d) {
		return new Date(m_d.getTime());

	}

	public static long diffBetweenDays(java.util.Date afterDate, java.util.Date beforeDate) {
		long diff = afterDate.getTime() - beforeDate.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException {
		boolean datecheck = false;
		Date today = new Date();
		if (manufacturing_date.before(today)) {
			datecheck = true;
			return datecheck;
		}
		else {
			logger.error(Constants.INVALID_MANUFACTURING_DATE);
			throw new ManufacturingDateException(Constants.INVALID_MANUFACTURING_DATE);
		}
	}

	public static boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException {
		
		boolean datecheck = false;
		
		if (expiry_date.after(manufacturing_date)) {
			datecheck = true;
			return datecheck;
		}
		else {
			logger.error(Constants.INVALID_EXPIRY_DATE);
			throw new ExpiryDateException(Constants.INVALID_EXPIRY_DATE);
		}
		
	}

}
