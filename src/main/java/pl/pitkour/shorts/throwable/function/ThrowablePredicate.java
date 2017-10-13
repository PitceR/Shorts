package pl.pitkour.shorts.throwable.function;

@FunctionalInterface
public interface ThrowablePredicate<T>
{
	boolean test(T t) throws Throwable;
}
