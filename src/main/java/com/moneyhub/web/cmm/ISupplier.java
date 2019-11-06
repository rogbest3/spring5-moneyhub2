package com.moneyhub.web.cmm;

@FunctionalInterface
public interface ISupplier<T> {
	public T get();
}
