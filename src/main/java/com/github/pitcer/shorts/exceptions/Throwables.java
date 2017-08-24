package com.github.pitcer.shorts.exceptions;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class Throwables
{
	private Throwables()
	{}

	public static void tryCatch(ThrowsRunnable tryAction)
	{
		Objects.requireNonNull(tryAction);
		try
		{
			tryAction.run();
		}
		catch(Throwable ignored)
		{}
	}

	public static <T> Optional<T> tryCatch(ThrowsSupplier<T> tryAction)
	{
		Objects.requireNonNull(tryAction);
		try
		{
			return Optional.of(tryAction.get());
		}
		catch(Throwable throwable)
		{
			return Optional.empty();
		}
	}

	public static void tryCatch(ThrowsRunnable tryAction, Consumer<Throwable> catchAction)
	{
		Objects.requireNonNull(tryAction);
		Objects.requireNonNull(catchAction);
		try
		{
			tryAction.run();
		}
		catch(Throwable throwable)
		{
			catchAction.accept(throwable);
		}
	}

	public static <T> Optional<T> tryCatch(ThrowsSupplier<T> tryAction, Consumer<Throwable> catchAction)
	{
		Objects.requireNonNull(tryAction);
		Objects.requireNonNull(catchAction);
		try
		{
			return Optional.of(tryAction.get());
		}
		catch(Throwable throwable)
		{
			catchAction.accept(throwable);
			return Optional.empty();
		}
	}

	public static void tryFinally(Runnable tryAction, Runnable finallyAction)
	{
		Objects.requireNonNull(tryAction);
		Objects.requireNonNull(finallyAction);
		try
		{
			tryAction.run();
		}
		finally
		{
			finallyAction.run();
		}
	}

	public static <T> T tryFinally(Supplier<T> tryAction, Runnable finallyAction)
	{
		Objects.requireNonNull(tryAction);
		Objects.requireNonNull(finallyAction);
		try
		{
			return tryAction.get();
		}
		finally
		{
			finallyAction.run();
		}
	}

	public static void tryCatchFinally(ThrowsRunnable tryAction, Consumer<Throwable> catchAction, Runnable finallyAction)
	{
		Objects.requireNonNull(tryAction);
		Objects.requireNonNull(catchAction);
		Objects.requireNonNull(finallyAction);
		try
		{
			tryAction.run();
		}
		catch(Throwable throwable)
		{
			catchAction.accept(throwable);
		}
		finally
		{
			finallyAction.run();
		}
	}

	public static <T> Optional<T> tryCatchFinally(ThrowsSupplier<T> tryAction, Consumer<Throwable> catchAction, Runnable finallyAction)
	{
		Objects.requireNonNull(tryAction);
		Objects.requireNonNull(catchAction);
		Objects.requireNonNull(finallyAction);
		try
		{
			return Optional.of(tryAction.get());
		}
		catch(Throwable throwable)
		{
			catchAction.accept(throwable);
			return Optional.empty();
		}
		finally
		{
			finallyAction.run();
		}
	}
}
