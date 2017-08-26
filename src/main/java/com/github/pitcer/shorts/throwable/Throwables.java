package com.github.pitcer.shorts.throwable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import com.github.pitcer.shorts.throwable.function.ThrowableRunnable;
import com.github.pitcer.shorts.throwable.function.ThrowableSupplier;

public final class Throwables
{
	private Throwables()
	{}

	public static void tryCatch(ThrowableRunnable tryAction)
	{
		Objects.requireNonNull(tryAction);
		try
		{
			tryAction.run();
		}
		catch(Throwable ignored)
		{
		}
	}

	public static <T> Optional<T> tryCatch(ThrowableSupplier<T> tryAction)
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

	public static void tryCatch(ThrowableRunnable tryAction, Consumer<Throwable> catchAction)
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

	public static <T> Optional<T> tryCatch(ThrowableSupplier<T> tryAction, Consumer<Throwable> catchAction)
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

	public static void tryCatchFinally(ThrowableRunnable tryAction, Consumer<Throwable> catchAction, Runnable finallyAction)
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

	public static <T> Optional<T> tryCatchFinally(ThrowableSupplier<T> tryAction, Consumer<Throwable> catchAction, Runnable finallyAction)
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

	public static ThrowablesRunnerBuilder builder(ThrowableRunnable tryAction)
	{
		Objects.requireNonNull(tryAction);
		return new ThrowablesRunnerBuilder(tryAction);
	}

	public static <T> ThrowablesGetterBuilder<T> builder(ThrowableSupplier<T> tryAction)
	{
		Objects.requireNonNull(tryAction);
		return new ThrowablesGetterBuilder<>(tryAction);
	}

	public static final class ThrowablesRunnerBuilder
	{
		private ThrowableRunnable tryAction;
		private Consumer<Throwable> catchAction;
		private Runnable finallyAction;

		private ThrowablesRunnerBuilder(ThrowableRunnable tryAction)
		{
			this.tryAction = tryAction;
		}

		public ThrowablesRunnerBuilder addCatch(Consumer<Throwable> catchAction)
		{
			Objects.requireNonNull(catchAction);
			this.catchAction = catchAction;
			return this;
		}

		public ThrowablesRunnerBuilder addFinally(Runnable finallyAction)
		{
			Objects.requireNonNull(finallyAction);
			this.finallyAction = finallyAction;
			return this;
		}

		public void build()
		{
			try
			{
				this.tryAction.run();
			}
			catch(Throwable throwable)
			{
				if(Objects.nonNull(this.catchAction))
				{
					this.catchAction.accept(throwable);
				}
			}
			finally
			{
				if(Objects.nonNull(this.finallyAction))
				{
					this.finallyAction.run();
				}
			}
		}
	}

	public static final class ThrowablesGetterBuilder<T>
	{
		private ThrowableSupplier<T> tryAction;
		private Consumer<Throwable> catchAction;
		private Runnable finallyAction;

		private ThrowablesGetterBuilder(ThrowableSupplier<T> tryAction)
		{
			this.tryAction = tryAction;
		}

		public ThrowablesGetterBuilder<T> addCatch(Consumer<Throwable> catchAction)
		{
			Objects.requireNonNull(catchAction);
			this.catchAction = catchAction;
			return this;
		}

		public ThrowablesGetterBuilder<T> addFinally(Runnable finallyAction)
		{
			Objects.requireNonNull(finallyAction);
			this.finallyAction = finallyAction;
			return this;
		}

		public Optional<T> build()
		{
			try
			{
				return Optional.of(this.tryAction.get());
			}
			catch(Throwable throwable)
			{
				if(Objects.nonNull(this.catchAction))
				{
					this.catchAction.accept(throwable);
				}
				return Optional.empty();
			}
			finally
			{
				if(Objects.nonNull(this.finallyAction))
				{
					this.finallyAction.run();
				}
			}
		}
	}
}
