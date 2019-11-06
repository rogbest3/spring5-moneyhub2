package com.moneyhub.web.brd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.moneyhub.web.aop.TxService;
import com.moneyhub.web.cli.Client;
import com.moneyhub.web.cli.ClientCtrl;
import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.cmm.IFunction;
import com.moneyhub.web.cmm.IPredicate;
import com.moneyhub.web.cmm.ISupplier;
import com.moneyhub.web.pxy.Proxy;
import com.moneyhub.web.pxy.ProxyMap;
import com.moneyhub.web.utl.Printer;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/articles")	// 노출되기 때문에 약자 사용X
@Log4j
public class ArticleCtrl {
	private static final Logger logger = LoggerFactory.getLogger(ArticleCtrl.class);
//	@Autowired Map<String, Object> articleMap;
	@Autowired Client client;
	@Autowired Printer printer;
	@Autowired ArticleMapper articleMapper;
	@Autowired List<Article> list;
	@Autowired TxService txService;
	@Autowired Proxy pxy;
	@Autowired ProxyMap map;
	
	@PostMapping("/")
	public Map<?, ?> write(@RequestBody Article param){
		printer.accept("write 들어옴");
		param.setBoardType("게시판");
		
		IConsumer<Article> c = t -> articleMapper.insertArticle(param);
		c.accept(param);
		map.accept(Arrays.asList("msg"),
					Arrays.asList("SUCCESS"));
		return map.get();
	}
	@GetMapping("/page/{pageNo}/size/{pageSize}")
	public Map<?, ?> list(@PathVariable String pageNo, @PathVariable String pageSize ){
		printer.accept("list 들어옴, pageNo : " + pageNo);
		pxy.setPageNum(pxy.parseInt(pageNo));
		pxy.setPageSize(pxy.parseInt(pageSize));
		pxy.paging();
		ISupplier <List<Article>> c = () -> articleMapper.selectAll(pxy);
		printer.accept("해당 글목록 : " + c.get());

		List<String> pageNumList = new ArrayList<>();
		for(int i = pxy.getPageNum(); i <= pxy.getPageSize(); i++)
			pageNumList.add(String.valueOf(i));

		map.accept(Arrays.asList("articles", "pages", "prev", "next"),
					Arrays.asList(c.get(), pageNumList, Arrays.asList(pxy.getExistPrev()), Arrays.asList(pxy.getExistNext()) ));
		return map.get();
	}

	@GetMapping("/count")
	public Map<?, ?> count(){
		printer.accept("count 들어옴");
		ISupplier<String> c =  () -> articleMapper.countArticle();
		/*map.clear();
		map.put("count", c.get());*/
		map.accept(Arrays.asList("count"), Arrays.asList(c.get()));
		printer.accept("count : " + c.get());
		return map.get();
	}

	@PutMapping("/{artSeq}")
	public Map<?, ?> update(@PathVariable String artSeq, @RequestBody Article param) {
		printer.accept("update 들어옴");
		param.setArtSeq(artSeq);
//		param.setBoardType("게시판");
		IConsumer<Article> u =  t -> articleMapper.updateArticle(param);
		u.accept(param);
		printer.accept("update 나감 - "+param.toString());
/*		map.clear();
		map.put("title", "title");*/
		map.accept(Arrays.asList("title"), Arrays.asList("title"));
		return map.get();
	}
	
	@DeleteMapping("/{artSeq}")
	public Map<?, ?> delete(@PathVariable String artSeq) {
		printer.accept("delete로 들어옴");
		IConsumer<String> d = t -> articleMapper.deleteArticle(artSeq);
		d.accept(artSeq);
//		map.clear();
		return map.get();
	}
}
