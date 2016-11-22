package com.cfm.ws.model.response;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import com.wordnik.swagger.annotations.ApiModel;

@XmlRootElement
@ApiModel(value = "BalanceResponse", description = "Balance Response")
public class BalanceResponse extends ResponseEntity {

	private int playerId;
	private BigDecimal balance;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}