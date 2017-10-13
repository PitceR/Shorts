package pl.pitkour.shorts.throwable.function;

@FunctionalInterface
public interface ThrowableRunnable
{
	void run() throws Throwable;
}