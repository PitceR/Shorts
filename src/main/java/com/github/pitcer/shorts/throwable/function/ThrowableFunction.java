package com.github.pitcer.shorts.throwable.function;

@FunctionalInterface
public interface ThrowableFunction<T, R>
{
	R apply(T t) throws Throwable;
}
