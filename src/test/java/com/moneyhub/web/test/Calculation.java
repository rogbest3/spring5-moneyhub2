package com.moneyhub.web.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.moneyhub.web.brd.ArticleMapper;
import com.moneyhub.web.cmm.ISupplier;

public class Calculation {
	
	private final static int BLOCK_SIZE = 5;
	public static void main(String[] args) {

		int totalCount = 65;
		int pageSize = 5;
		
		int startRow = 0;
		
		int pageNum = 11;
		

		// row 기준 - page 구성

		int pageCount = ( totalCount % pageSize == 0 ) 
						? totalCount / pageSize 
						:  totalCount / pageSize + 1;
		startRow = ( pageNum - 1 ) * pageSize;
		int endRow = pageNum == pageCount ?	totalCount - 1 : startRow + pageSize - 1;
		
		// page 기준 - block 구성
		int blockCount = ( pageCount % BLOCK_SIZE == 0 ) 
						? pageCount / BLOCK_SIZE 
						: pageCount / BLOCK_SIZE + 1;	
		int blockNum = ( pageNum - 1 ) / BLOCK_SIZE;
		int startPage = blockNum * BLOCK_SIZE + 1;
		int endPage = blockNum == ( blockCount - 1) ? pageCount : ( blockNum + 1 ) * BLOCK_SIZE ;
		
		boolean existPrev = blockNum > 0 ? true : false;
		boolean existNext = blockNum < ( blockCount - 1) ? true : false;
		
		System.out.println("pageCount : " + pageCount + ", blockNum : " + blockNum + ", blockCount : " + blockCount 
							+ ", endPage : " + endPage + ", existPrev : " + existPrev+ ", existNext : " + existNext);
	}

}
