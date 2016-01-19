package chapter02.practice;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JUnitTest {

	static JUnitTest testObject;
	
	@Test public void test1() {
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
	}
	
	@Test public void test2() {
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
	}
	
	@Test public void test3() {
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
	}
}
