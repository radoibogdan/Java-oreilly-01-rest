package com.oreilly.beginner;

import com.oreilly.beginner.json.Greeting;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeginnerApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertNotNull(context);
		System.out.println(context.getClass().getName());
		int count = context.getBeanDefinitionCount();
		System.out.println("There are " + count + " beans in the app context.");
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

	@Test
	@Disabled // ignore this test for the moment
	void noGreetingInAppCtx() {
		assertThrows(
				NoSuchBeanDefinitionException.class, // exception expected
				() -> context.getBean(Greeting.class) // when calling this line
		);
	}

	@Test
	void getBeanTwice() {
		Greeting greeting1 = context.getBean("defaultGreeting", Greeting.class);
		Greeting greeting2 = context.getBean("defaultGreeting", Greeting.class);
		greeting1.setMessage("Hello from message 1");
		System.out.println(greeting2.getMessage());
		assertSame(greeting1, greeting2);
	}
}
