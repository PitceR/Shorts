package com.github.pitcer.shorts.exceptions;

@FunctionalInterface
public interface ThrowsSupplier<T>
{
	T get() throws Throwable;
}
