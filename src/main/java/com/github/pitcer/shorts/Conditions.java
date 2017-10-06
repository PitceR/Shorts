package com.github.pitcer.shorts;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Conditions
{
	private Conditions()
	{}

	public static void ifThen(boolean condition, Runnable action)
	{
		Objects.requireNonNull(action);
		if(condition)
		{
			action.run();
		}
	}

	public static <T> void ifThen(T object, Predicate<T> condition, Consumer<T> action)
	{
		Objects.requireNonNull(condition);
		Objects.requireNonNull(action);
		if(condition.test(object))
		{
			action.accept(object);
		}
	}

	public static <T extends Throwable> void ifThenThrow(boolean condition, Supplier<T> throwAction) throws T
	{
		Objects.requireNonNull(throwAction);
		if(condition)
		{
			throw throwAction.get();
		}
	}

	public static <T> Optional<T> ifThen(boolean condition, Supplier<T> action)
	{
		Objects.requireNonNull(action);
		return condition ? Optional.of(action.get()) : Optional.empty();
	}

	public static <T, R> Optional<R> ifThen(T object, Predicate<T> condition, Function<T, R> action)
	{
		Objects.requireNonNull(condition);
		Objects.requireNonNull(action);
		return condition.test(object) ? Optional.of(action.apply(object)) : Optional.empty();
	}

	public static void ifElse(boolean condition, Runnable ifAction, Runnable elseAction)
	{
		Objects.requireNonNull(ifAction);
		Objects.requireNonNull(elseAction);
		if(condition)
		{
			ifAction.run();
		}
		else
		{
			elseAction.run();
		}
	}

	public static <T> T ifElse(boolean condition, Supplier<T> ifAction, Supplier<T> elseAction)
	{
		Objects.requireNonNull(ifAction);
		Objects.requireNonNull(elseAction);
		return condition ? ifAction.get() : elseAction.get();
	}

	public static void ifElseIf(boolean ifCondition, Runnable ifAction, boolean elseIfCondition, Runnable elseIfAction)
	{
		Objects.requireNonNull(ifAction);
		Objects.requireNonNull(elseIfAction);
		if(ifCondition)
		{
			ifAction.run();
		}
		else if(elseIfCondition)
		{
			elseIfAction.run();
		}
	}

	public static <T> Optional<T> ifElseIf(boolean ifCondition, Supplier<T> ifAction, boolean elseIfCondition, Supplier<T> elseIfAction)
	{
		Objects.requireNonNull(ifAction);
		Objects.requireNonNull(elseIfAction);
		return ifCondition ? Optional.of(ifAction.get()) : elseIfCondition ? Optional.of(elseIfAction.get()) : Optional.empty();
	}

	public static void ifElseIfElse(boolean ifCondition, Runnable ifAction, boolean elseIfCondition, Runnable elseIfAction, Runnable elseAction)
	{
		Objects.requireNonNull(ifAction);
		Objects.requireNonNull(elseIfAction);
		Objects.requireNonNull(elseAction);
		if(ifCondition)
		{
			ifAction.run();
		}
		else if(elseIfCondition)
		{
			elseIfAction.run();
		}
		else
		{
			elseAction.run();
		}
	}

	public static <T> T ifElseIfElse(boolean ifCondition, Supplier<T> ifAction, boolean elseIfCondition, Supplier<T> elseIfAction, Supplier<T> elseAction)
	{
		Objects.requireNonNull(ifAction);
		Objects.requireNonNull(elseIfAction);
		Objects.requireNonNull(elseAction);
		return ifCondition ? ifAction.get() : elseIfCondition ? elseIfAction.get() : elseAction.get();
	}

	public static ConditionsRunnerBuilder builder(boolean ifCondition, Runnable ifAction)
	{
		Objects.requireNonNull(ifAction);
		return new ConditionsRunnerBuilder(ifCondition, ifAction);
	}

	public static <T> ConditionsGetterBuilder<T> builder(boolean ifCondition, Supplier<T> ifAction)
	{
		Objects.requireNonNull(ifAction);
		return new ConditionsGetterBuilder<>(ifCondition, ifAction);
	}

	public static final class ConditionsRunnerBuilder
	{
		private boolean ifCondition;
		private Runnable ifAction;
		private boolean elseIfCondition;
		private Runnable elseIfAction;
		private Runnable elseAction;

		private ConditionsRunnerBuilder(boolean ifCondition, Runnable ifAction)
		{
			this.ifCondition = ifCondition;
			this.ifAction = ifAction;
		}

		public ConditionsRunnerBuilder addElseIf(boolean elseIfCondition, Runnable elseIfAction)
		{
			Objects.requireNonNull(elseIfAction);
			this.elseIfCondition = elseIfCondition;
			this.elseIfAction = elseIfAction;
			return this;
		}

		public ConditionsRunnerBuilder addElse(Runnable elseAction)
		{
			Objects.requireNonNull(elseAction);
			this.elseAction = elseAction;
			return this;
		}

		public void build()
		{
			if(this.ifCondition)
			{
				this.ifAction.run();
			}
			else if(this.elseIfCondition)
			{
				this.elseIfAction.run();
			}
			else if(Objects.nonNull(this.elseAction))
			{
				this.elseAction.run();
			}
		}
	}

	public static final class ConditionsGetterBuilder<T>
	{
		private boolean ifCondition;
		private Supplier<T> ifAction;
		private boolean elseIfCondition;
		private Supplier<T> elseIfAction;
		private Supplier<T> elseAction;

		private ConditionsGetterBuilder(boolean ifCondition, Supplier<T> ifAction)
		{
			this.ifCondition = ifCondition;
			this.ifAction = ifAction;
		}

		public ConditionsGetterBuilder<T> addElseIf(boolean elseIfCondition, Supplier<T> elseIfAction)
		{
			Objects.requireNonNull(elseIfAction);
			this.elseIfCondition = elseIfCondition;
			this.elseIfAction = elseIfAction;
			return this;
		}

		public ConditionsGetterBuilder<T> addElse(Supplier<T> elseAction)
		{
			Objects.requireNonNull(elseAction);
			this.elseAction = elseAction;
			return this;
		}

		public Optional<T> build()
		{
			return this.ifCondition ? Optional.of(this.ifAction.get()) : this.elseIfCondition ? Optional.of(this.elseIfAction.get()) : Objects.nonNull(this.elseAction) ? Optional.of(this.elseAction.get()) : Optional.empty();
		}
	}
}
