package com.github.pitcer.shorts.throwable.function;

@FunctionalInterface
public interface ThrowablePredicate<T>
{
	boolean test(T t) throws Throwable;
}
