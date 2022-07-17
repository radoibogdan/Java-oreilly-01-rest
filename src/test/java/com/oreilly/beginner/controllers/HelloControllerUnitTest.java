package com.oreilly.beginner.controllers;

import com.oreilly.beginner.controllers.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.system.JavaVersion;
import org.springframework.core.SpringVersion;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.*;

public class HelloControllerUnitTest {
    @Test
    public void sayHello() {
        HelloController controller = new HelloController();
        Model model = new BindingAwareModelMap();
        String result = controller.sayHello("World", model);
        assertAll(
                () -> assertEquals("World", model.getAttribute("user")), // the model should have an attribute user whose value is World
                () -> assertEquals("hello", result),
                () -> assertEquals("1.8", JavaVersion.getJavaVersion().toString()),
                () -> assertEquals("5.3.21", SpringVersion.getVersion())
        );
    }
}
