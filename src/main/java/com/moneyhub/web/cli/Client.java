package com.moneyhub.web.cli;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Lazy
@Data 
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Client {

	private String cstate, level, hubAccount, cid, pwd, reg, wdd;

}
