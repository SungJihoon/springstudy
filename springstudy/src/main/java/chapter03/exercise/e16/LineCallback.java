package chapter03.exercise.e16;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);
}
