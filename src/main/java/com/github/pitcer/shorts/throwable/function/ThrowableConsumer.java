package com.github.pitcer.shorts.throwable.function;

@FunctionalInterface
public interface ThrowableConsumer<T>
{
	void accept(T t) throws Throwable;
}
