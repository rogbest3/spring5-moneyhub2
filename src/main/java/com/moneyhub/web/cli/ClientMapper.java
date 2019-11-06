package com.moneyhub.web.cli;

import org.springframework.stereotype.Repository;

@Repository
public interface ClientMapper {
	public void insertClient(Client client);
	public Client selectClientByIdPw(Client client);
	public int existId(String cid);
	
}
