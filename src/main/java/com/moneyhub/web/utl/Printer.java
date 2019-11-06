package com.moneyhub.web.utl;

import java.util.function.Consumer;
import org.springframework.stereotype.Service;
import com.moneyhub.web.cmm.IConsumer;

@Service
public class Printer implements IConsumer{

	@Override
	public void accept(Object o) {
		Consumer<String> c = System.out :: println;	//	syso
		c.accept((String)o);
	}
}
