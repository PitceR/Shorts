package com.github.pitcer.shorts;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public final class Locks
{
	private Locks()
	{}

	public static void reentrant(Runnable action)
	{
		Objects.requireNonNull(action);
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try
		{
			action.run();
		}
		finally
		{
			lock.unlock();
		}
	}

	public static void reentrant(ReentrantLock lock, Runnable action)
	{
		Objects.requireNonNull(lock);
		Objects.requireNonNull(action);
		lock.lock();
		try
		{
			action.run();
		}
		finally
		{
			lock.unlock();
		}
	}

	public static <T> T reentrant(Supplier<T> action)
	{
		Objects.requireNonNull(action);
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try
		{
			return action.get();
		}
		finally
		{
			lock.unlock();
		}
	}

	public static <T> T reentrant(ReentrantLock lock, Supplier<T> action)
	{
		Objects.requireNonNull(lock);
		Objects.requireNonNull(action);
		lock.lock();
		try
		{
			return action.get();
		}
		finally
		{
			lock.unlock();
		}
	}
}
