package com.moneyhub.web.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.moneyhub.web.cfg.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ContextConfiguration(classes = {RootConfig.class} )
@Log4j
public class DataSourceTest {

	@Setter( onMethod_ = { @Autowired } )
	private DataSource dataSource;
	@Test
	public void testConnection() {
		try (Connection con = dataSource.getConnection()){
			System.out.println("성공");
			log.info(con);
		} catch (Exception e) {
			System.out.println("실패");
			fail(e.getMessage());
		}
	}
}
