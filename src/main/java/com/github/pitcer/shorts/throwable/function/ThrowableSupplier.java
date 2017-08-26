package com.github.pitcer.shorts.throwable.function;

@FunctionalInterface
public interface ThrowableSupplier<T>
{
	T get() throws Throwable;
}
