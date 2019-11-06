package com.moneyhub.web.cli;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.cmm.IFunction;
import com.moneyhub.web.cmm.IPredicate;
import com.moneyhub.web.utl.Printer;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/clients")	// s 복수
@Log4j
public class ClientCtrl {
	private static final Logger logger = LoggerFactory.getLogger(ClientCtrl.class);
	@Autowired Map<String, Object> map;
	@Autowired Client client;
	@Autowired Printer printer;
	@Autowired ClientMapper clientMapper;	// 클래식 자바에서는 바로 mapper 연결하면 안되지만 모던자바에선 사용
	
	@GetMapping("/{cid}/exist")
	public Map<?, ?> existId(@PathVariable String cid){
		IFunction<String, Integer> f = t -> clientMapper.existId(cid);
		map.clear();
		map.put("msg", f.apply(cid) == 0 ? "Success" : "Fail");
		printer.accept("map : "+ map);
		return map;
	}
	
	@PostMapping("/")	//	create - 파라미터 없으면
	public Map<?, ?> join(@RequestBody Client param) {	
		printer.accept("join 들어옴"+param.toString());
		IConsumer<Client> c = t -> clientMapper.insertClient(param);
		c.accept(param); 
		map.clear();
		map.put("msg", "Success");
		return map;
	}
	
	@PostMapping("/{cid}/login")
	public Client login(@PathVariable String cid, @RequestBody Client param){
		printer.accept("login 들어옴"+ cid+", "+ param.toString());
		IFunction<Client, Client> f = t -> clientMapper.selectClientByIdPw(param);
		return f.apply(param);
	}	
	@GetMapping("/{cid}")
	public Client searchClientById(@PathVariable String cid, @RequestBody Client param) {
		IFunction<Client, Client> s = t -> clientMapper.selectClientByIdPw(param);
		return s.apply(param);
	}
	
	@PutMapping("/{cid}")
	public String updataClient(@PathVariable String cid, @RequestBody Client param) {
		IConsumer<Client> c = t -> clientMapper.insertClient(param);
		c.accept(param);
		return "Success";
	}
	@DeleteMapping("/{cid}")
	public String removeClient(@PathVariable String cid, @RequestBody Client param) {
		IConsumer<Client> c = t -> clientMapper.insertClient(param);
		c.accept(param);
		return "Success";
	}
	
	
}
