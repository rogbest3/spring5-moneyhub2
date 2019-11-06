package com.moneyhub.web.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneyhub.web.pxy.Proxy;

@Transactional
@Service
public class TxService {	// POJO
	@Autowired TxMapper mapper;
//	@Autowired HashMap<String, Object> map;
	@Autowired Proxy pxy;
//	@Autowired List<String> txServiceList;
	
	@SuppressWarnings("unchecked")
	public List<?> crawling(Map<?, ?> paramMap){
		List<String> txServiceList = new ArrayList<>();
		txServiceList.clear();
		txServiceList = (List<String>) pxy.crawl(paramMap);
		return txServiceList;
	}
}
