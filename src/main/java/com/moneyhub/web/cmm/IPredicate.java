package com.moneyhub.web.cmm;

@FunctionalInterface
public interface IPredicate <T>{
	public boolean test(T t);
}
