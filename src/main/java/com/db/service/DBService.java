package com.db.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import com.cfm.ws.model.response.BalanceResponse;
import com.cfm.ws.support.CFMRecordNotFoundException;

@Repository("dbService")
public class DBService {

	@Autowired
	private DriverManagerDataSource dataSource;

	private Connection connection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	public BalanceResponse balanceOfPlayer(final int playerId) throws SQLException {
		
		final BalanceResponse response = new BalanceResponse();
		Connection conn = null;

		try{
			conn = connection();
			final PreparedStatement ps = conn.prepareStatement(String.format("SELECT SUM(Amount) AS balance, player_ID FROM Transactions WHERE Player_ID=%s", playerId));
			final ResultSet rs = ps.executeQuery();
			
			if(rs.first()){
				if(rs.getString("player_ID") == null){
					throw new CFMRecordNotFoundException("Record not found.");
				}					
				response.setPlayerId(rs.getInt("player_ID"));
				response.setBalance(rs.getBigDecimal("balance"));
			}
		} catch (final SQLException ex) {
			// ignore failure closing connection
			try {
				conn.close();
			} catch (final SQLException e) {
			}
			throw ex;
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
		
		return response;
	}

}
