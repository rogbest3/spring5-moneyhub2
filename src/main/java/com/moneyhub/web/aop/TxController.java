package com.moneyhub.web.aop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhub.web.utl.Printer;

@RestController
@Transactional
@RequestMapping("/tx")
public class TxController {
//	System.out.println(text);
	@Autowired TxService txService;
	@Autowired Printer printer;
//	@Autowired HashMap<String, Object> map;
	
	@GetMapping("/crawling/{site}/{srch}")
	public Map<?, ?> getUrl(@PathVariable String site, 
							@PathVariable String srch){
		HashMap<String, Object> txMap = new HashMap<>();
		printer.accept("getUrl 들어옴 - site : " + site + ", srch : " + srch);		
		txMap.clear();
		txMap.put("site", site);
		txMap.put("srch", srch);
		txMap.put("msg", txService.crawling(txMap));
		return txMap;
	}
}
