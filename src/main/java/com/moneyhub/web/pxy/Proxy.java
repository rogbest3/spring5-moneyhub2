package com.moneyhub.web.pxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.moneyhub.web.brd.ArticleMapper;
import com.moneyhub.web.cmm.ISupplier;
import com.moneyhub.web.utl.Printer;

import lombok.Data;

@Component @Data @Lazy
public class Proxy {
	private int pageNum, pageSize, startRow, blockNum, startPage, endPage;
	private String search;
	private boolean existPrev, existNext;
	private final int BLOCK_SIZE = 5;
	
	@Autowired Printer p;
	@Autowired ArticleMapper articleMapper;
	
	@SuppressWarnings("unused")
	public void paging() {
		ISupplier<String> s = ()-> articleMapper.countArticle();
		// row 기준 - page 구성
		int totalCount = Integer.parseInt(s.get());
		int pageCount = ( totalCount % pageSize == 0 ) 
						? totalCount / pageSize 
						:  totalCount / pageSize + 1;
		startRow = ( pageNum - 1 ) * pageSize;
		int endRow = pageNum == pageCount ?	totalCount - 1 : startRow + pageSize - 1;
		
		// page 기준 - block 구성
		int blockCount = ( pageCount % BLOCK_SIZE == 0 ) 
						? pageCount / BLOCK_SIZE 
						: pageCount / BLOCK_SIZE + 1;	
		blockNum = ( pageNum - 1 ) / BLOCK_SIZE;
		startPage = blockNum * BLOCK_SIZE + 1;
		endPage = blockNum != ( blockCount - 1) ? ( blockNum + 1 ) * BLOCK_SIZE : pageCount ;
		
		existPrev = blockNum > 0;
		existNext = blockNum < ( blockCount - 1);

	}
	
	public int parseInt(String param) {
		Function<String, Integer> f = s -> Integer.parseInt(s);
		return f.apply(param);
	}
	
	public List<?> crawl(Map<?, ?> paramMap){	//	정형화되지 않은 data값 반환
		List<String> proxyList = new ArrayList<>();
		String url = "http://" + paramMap.get("site") + "/";
		proxyList.clear();
		try {
			Connection.Response response = Jsoup.connect(url)
												.method(Connection.Method.GET)
												.execute();
			Document document = response.parse();
		//	String html = document.html();
			String text = document.text();
		//	System.out.println(html);
			p.accept("크롤링한 텍스트 \n" + text);
			proxyList.add(text);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}		
		return proxyList;
	}
	
	public int random(int x, int y) {
		BiFunction<Integer, Integer, Integer> f = (i, j) -> (int)(Math.random()*j-i) + i;
		return f.apply(x, y);
	}

}
