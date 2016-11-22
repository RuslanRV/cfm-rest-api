package com.cfm.ws.service.impl;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfm.ws.model.response.BalanceResponse;
import com.cfm.ws.service.ICFMRestService;
import com.cfm.ws.support.CFMRecordNotFoundException;
import com.cfm.ws.support.CFMRestUtils;
import com.db.service.DBService;
@Service("cfmRestService")
public class CFMRestService implements ICFMRestService {

	private static final Logger LOGGER = Logger.getLogger(CFMRestService.class);

	@Autowired
	private DBService dbService;

	@Override
	public Response balanceOfPlayer(final int playerId) {
		try {
			//validation layer here....		
			BalanceResponse response = dbService.balanceOfPlayer(playerId);			
			return CFMRestUtils.buildOKResponse(response);
		} catch (final CFMRecordNotFoundException arnfe) {
			LOGGER.warn(ExceptionUtils.getStackTrace(arnfe));
			return CFMRestUtils.buildNotFoundResponse(arnfe.getMessage());
		} catch (final SQLException se) {
			LOGGER.error(ExceptionUtils.getStackTrace(se));
			return CFMRestUtils.buildBadRequestResponse(se.getMessage());
		} catch (final Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			return CFMRestUtils.buildInternalServerResponse(e.getMessage());
		}
	}
}