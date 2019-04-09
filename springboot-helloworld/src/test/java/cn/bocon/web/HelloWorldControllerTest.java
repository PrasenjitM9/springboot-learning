package cn.bocon.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Spring Boot HelloWorldController 测试 - {@link HelloWorldController}
 *
 * Created by wj on 17/8/5.
 */
public class HelloWorldControllerTest {

    @Test
    public void testSayHello() {
        assertEquals("Hello,World!",new HelloWorldController().sayHello());
    }
}
