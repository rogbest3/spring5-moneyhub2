package com.moneyhub.web.adm;

import java.io.Serializable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.moneyhub.web.cli.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Component
@AllArgsConstructor
@NoArgsConstructor
@Lazy				// tx가 걸림, lazy loading
public class Admin{
	private String 
	astate, pos, aid, pwd;
}
