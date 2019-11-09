package com.capgemini.dnd.util;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class ServiceUtil {
	public static Date stringtoDate(java.util.Date m_d) {
		return new Date(m_d.getTime());

	}

	public static long diffBetweenDays(java.util.Date afterDate, java.util.Date beforeDate) {
		long diff = afterDate.getTime() - beforeDate.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
}
