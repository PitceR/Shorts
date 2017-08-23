package com.github.pitcer.shorts;

import java.util.Objects;
import java.util.Optional;
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

	public static <T> Optional<T> ifThen(boolean condition, Supplier<T> action)
	{
		Objects.requireNonNull(action);
		return condition ? Optional.of(action.get()) : Optional.empty();
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
}
