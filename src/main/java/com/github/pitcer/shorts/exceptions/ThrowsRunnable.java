package com.github.pitcer.shorts.exceptions;

@FunctionalInterface
public interface ThrowsRunnable
{
	void run() throws Throwable;
}