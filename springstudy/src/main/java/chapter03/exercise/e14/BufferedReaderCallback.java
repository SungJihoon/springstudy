package chapter03.exercise.e14;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
	Integer doSomthingWithReader(BufferedReader br) throws IOException;
}
