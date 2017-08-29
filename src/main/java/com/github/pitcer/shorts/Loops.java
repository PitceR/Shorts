package com.github.pitcer.shorts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Loops
{
	private Loops()
	{}

	public static void infinity(Runnable action)
	{
		Objects.requireNonNull(action);
		while(true)
		{
			action.run();
		}
	}

	public static void loop(boolean condition, Runnable action)
	{
		Objects.requireNonNull(action);
		while(condition)
		{
			action.run();
		}
	}

	public static void loop(int times, Runnable action)
	{
		Objects.requireNonNull(action);
		for(int index = 0; index < times; index++)
		{
			action.run();
		}
	}

	public static void loop(int times, Consumer<Integer> action)
	{
		Objects.requireNonNull(action);
		for(int index = 0; index < times; index++)
		{
			action.accept(index);
		}
	}

	public static <T> void forEach(T[] array, Consumer<T> action)
	{
		Objects.requireNonNull(array);
		Objects.requireNonNull(action);
		for(T element : array)
		{
			action.accept(element);
		}
	}

	public static <T> void forEach(Iterable<T> iterable, Consumer<T> action)
	{
		Objects.requireNonNull(iterable);
		Objects.requireNonNull(action);
		for(T element : iterable)
		{
			action.accept(element);
		}
	}

	public static <T> void forEach(T[] array, Predicate<T> condition, Consumer<T> action)
	{
		Objects.requireNonNull(array);
		Objects.requireNonNull(condition);
		Objects.requireNonNull(action);
		for(T element : array)
		{
			if(condition.test(element))
			{
				action.accept(element);
			}
		}
	}

	public static <T> void forEach(Iterable<T> iterable, Predicate<T> condition, Consumer<T> action)
	{
		Objects.requireNonNull(iterable);
		Objects.requireNonNull(condition);
		Objects.requireNonNull(action);
		for(T element : iterable)
		{
			if(condition.test(element))
			{
				action.accept(element);
			}
		}
	}

	public static <T> boolean contains(T[] array, Predicate<T> filter)
	{
		Objects.requireNonNull(array);
		Objects.requireNonNull(filter);
		for(T element : array)
		{
			if(filter.test(element))
			{
				return true;
			}
		}
		return false;
	}

	public static <T> boolean contains(Iterable<T> iterable, Predicate<T> filter)
	{
		Objects.requireNonNull(iterable);
		Objects.requireNonNull(filter);
		for(T element : iterable)
		{
			if(filter.test(element))
			{
				return true;
			}
		}
		return false;
	}

	public static <T> Optional<T> find(T[] array, Predicate<T> filter)
	{
		Objects.requireNonNull(array);
		Objects.requireNonNull(filter);
		for(T element : array)
		{
			if(filter.test(element))
			{
				return Optional.of(element);
			}
		}
		return Optional.empty();
	}

	public static <T> Optional<T> find(Iterable<T> iterable, Predicate<T> filter)
	{
		Objects.requireNonNull(iterable);
		Objects.requireNonNull(filter);
		for(T element : iterable)
		{
			if(filter.test(element))
			{
				return Optional.of(element);
			}
		}
		return Optional.empty();
	}

	public static <T> List<T> collect(T[] array, Predicate<T> filter)
	{
		Objects.requireNonNull(array);
		Objects.requireNonNull(filter);
		List<T> collected = new ArrayList<>(array.length);
		for(T element : array)
		{
			if(filter.test(element))
			{
				collected.add(element);
			}
		}
		return collected;
	}

	public static <T> List<T> collect(List<T> list, Predicate<T> filter)
	{
		Objects.requireNonNull(list);
		Objects.requireNonNull(filter);
		List<T> collected = new ArrayList<>(list.size());
		for(T element : list)
		{
			if(filter.test(element))
			{
				collected.add(element);
			}
		}
		return collected;
	}

	public static <T, R> List<R> transform(T[] array, Function<T, R> transformer)
	{
		Objects.requireNonNull(array);
		Objects.requireNonNull(transformer);
		List<R> transformed = new ArrayList<>(array.length);
		for(T element : array)
		{
			transformed.add(transformer.apply(element));
		}
		return transformed;
	}

	public static <T, R> List<R> transform(List<T> list, Function<T, R> transformer)
	{
		Objects.requireNonNull(list);
		Objects.requireNonNull(transformer);
		List<R> transformed = new ArrayList<>(list.size());
		for(T element : list)
		{
			transformed.add(transformer.apply(element));
		}
		return transformed;
	}

	public static <T> String build(T[] array, Function<T, String> transformer)
	{
		Objects.requireNonNull(array);
		Objects.requireNonNull(transformer);
		StringBuilder builder = new StringBuilder();
		for(T element : array)
		{
			builder.append(transformer.apply(element));
		}
		return builder.toString();
	}

	public static <T> String build(Iterable<T> iterable, Function<T, String> transformer)
	{
		Objects.requireNonNull(iterable);
		Objects.requireNonNull(transformer);
		StringBuilder builder = new StringBuilder();
		for(T element : iterable)
		{
			builder.append(transformer.apply(element));
		}
		return builder.toString();
	}

	public static <T> String join(T[] array, Function<T, String> transformer, CharSequence delimiter)
	{
		return join(array, transformer, delimiter, "", "");
	}

	public static <T> String join(T[] array, Function<T, String> transformer, CharSequence delimiter, CharSequence prefix, CharSequence suffix)
	{
		Objects.requireNonNull(array);
		Objects.requireNonNull(transformer);
		StringJoiner joiner = new StringJoiner(delimiter, prefix, suffix);
		for(T element : array)
		{
			joiner.add(transformer.apply(element));
		}
		return joiner.toString();
	}

	public static <T> String join(Iterable<T> iterable, Function<T, String> transformer, CharSequence delimiter)
	{
		return join(iterable, transformer, delimiter, "", "");
	}

	public static <T> String join(Iterable<T> iterable, Function<T, String> transformer, CharSequence delimiter, CharSequence prefix, CharSequence suffix)
	{
		Objects.requireNonNull(iterable);
		Objects.requireNonNull(transformer);
		StringJoiner joiner = new StringJoiner(delimiter, prefix, suffix);
		for(T element : iterable)
		{
			joiner.add(transformer.apply(element));
		}
		return joiner.toString();
	}
}
