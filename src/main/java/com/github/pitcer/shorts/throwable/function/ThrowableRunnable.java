package com.github.pitcer.shorts.throwable.function;

@FunctionalInterface
public interface ThrowableRunnable
{
	void run() throws Throwable;
}