package com.moneyhub.web.adm;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhub.web.brd.ArticleCtrl;
import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.cmm.IFunction;
import com.moneyhub.web.utl.Printer;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/admins")
@Log4j
public class AdminCtrl {
	private static final Logger logger = LoggerFactory.getLogger(AdminCtrl.class);
//	@Autowired Map<String, Object> adminMap;
	@Autowired AdminMapper adminMapper;
	@Autowired Printer printer;
	
	@PostMapping("/")
	public Map<?, ?> resister(Admin param){
		HashMap<String, Object> map = new HashMap<>();
		IConsumer<Admin> a = t -> adminMapper.insertAdmin(param);
		a.accept(param);
		return map;
	}
	
	@PostMapping("/{aid}/access")
	public Map<?, ?> access(@PathVariable String aid, @RequestBody Admin param) {
	HashMap<String, Object> map = new HashMap<>();
	printer.accept("access 들어옴");
	printer.accept(param.toString());
	IFunction<Admin, Admin> f = t -> adminMapper.selectAdminByIdPw(param);
	String r = (f.apply(param)!=null) ? "SUCCESS" : "FAIL";
	printer.accept("리턴값 : "+r);
	map.clear(); 
	map.put("msg", r);
	return map;
}

	@PutMapping("/{aid}")
	public String update(@PathVariable String aid, @RequestBody Admin param) {
		IConsumer<Admin> c = t -> adminMapper.updateAdmin(param);
		c.accept(param);
		return "";
	}
	@DeleteMapping("/{aid}")
	public String delete(@PathVariable String aid) {
		IConsumer<String> c = t -> adminMapper.deleteAdmin(aid);
		c.accept(aid);
		return "";
	}
	
	
}
