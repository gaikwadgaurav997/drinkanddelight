package com.capgemini.dnd.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.RawMaterialStock;
//import com.capgemini.dnd.util.JsonUtil;

@Service
@Transactional(readOnly = true)
public class RawMaterialServiceImpl implements RawMaterialService {

	@Override
	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock) {
//		String message = rawMaterialDAO.trackRawMaterialOrder(rawMaterialStock);
//		String jsonMessage = JsonUtil.convertJavaToJson(message);
//		return jsonMessage;
		return null;
	}

	@Override
	public boolean doesRawMaterialOrderIdExist(String id) throws RMOrderIDDoesNotExistException {
//		return rawMaterialDAO.doesRawMaterialOrderIdExist(id);
		return false;
	}

	@Override
	public boolean processDateCheck(RawMaterialStock rawMaterialStock)
			throws ProcessDateException, IncompleteDataException {
//		return rawMaterialDAO.processDateCheck(rawMaterialStock);
		return false;
	}

	@Override
	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock) {
//		String message = rawMaterialDAO.updateProcessDateinStock(rawMaterialStock);
//		String jsonMessage = JsonUtil.convertJavaToJson(message);
//		return jsonMessage;
		return null;
	}

	@Override
	public String updateRawMaterialStock(RawMaterialStock rawMaterialStock) {
//		String message = rawMaterialDAO.updateRawMaterialStock(rawMaterialStock);
//		String jsonMessage = JsonUtil.convertJavaToJson(message);
//		return jsonMessage;
		return null;
	}

}
