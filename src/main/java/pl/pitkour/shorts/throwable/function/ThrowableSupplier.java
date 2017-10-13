package pl.pitkour.shorts.throwable.function;

@FunctionalInterface
public interface ThrowableSupplier<T>
{
	T get() throws Throwable;
}
