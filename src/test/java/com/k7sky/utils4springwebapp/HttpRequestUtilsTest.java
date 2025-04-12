package com.k7sky.utils4springwebapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

public class HttpRequestUtilsTest {

    public HttpRequestUtilsTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("a", "1");
        request.setParameter("b", "2", "3", "4");
        request.addHeader("any-header", "any-header-value");
        request.setAttribute("any-str-attr", "any-str-attr-value");
        request.setAttribute("any-map-attr", Map.of("1", "one"));
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);
    }

    @Test
    void getParam() {
        String reqParam = HttpRequestUtils.getParam("a");
        Assertions.assertEquals("1", reqParam);

        reqParam = HttpRequestUtils.getParam("c");
        Assertions.assertNull(reqParam);
    }

    @Test
    void getMultiValParam() {
        String[] reqParam = HttpRequestUtils.getMultiValParam("b");
        Assertions.assertArrayEquals(new String[]{"2", "3", "4"}, reqParam);

        reqParam = HttpRequestUtils.getMultiValParam("c");
        Assertions.assertNull(reqParam);
    }

    @Test
    void getParamOrDefault() {
        String reqParam = HttpRequestUtils.getParamOrDefault("c", "default");
        Assertions.assertEquals("default", reqParam);
    }

    @Test
    void getMultiValParamOrDefault() {
        String[] reqParam = HttpRequestUtils.getMultiValParamOrDefault("d", "5", "6");
        Assertions.assertArrayEquals(new String[]{"5", "6"}, reqParam);
    }

    @Test
    void getHeader() {
        String header = HttpRequestUtils.getHeader("any-header");
        Assertions.assertEquals("any-header-value", header);

        header = HttpRequestUtils.getHeader("unknown-header");
        Assertions.assertNull(header);
    }

    @Test
    void getHeaderOrDefault() {
        String header = HttpRequestUtils.getHeaderOrDefault("unknown-header", "default");
        Assertions.assertEquals("default", header);
    }

    @Test
    void getAttribute() {
        Object attribute = HttpRequestUtils.getAttribute("any-map-attr");
        Assertions.assertEquals(Map.of("1", "one"), attribute);

        attribute = HttpRequestUtils.getAttribute("any-str-attr");
        Assertions.assertEquals("any-str-attr-value", attribute);
    }

    @Test
    void getAttributeOrDefault() {
        Object attribute = HttpRequestUtils.getAttributeOrDefault("unknown-attr", 1);
        Assertions.assertEquals(1, attribute);
    }

    @Test
    void setAttribute() {
        HttpRequestUtils.setAttribute("any-long-attr", 111L);
        Object attribute = HttpRequestUtils.getAttribute("any-long-attr");
        Assertions.assertEquals(111L, attribute);
    }
}
