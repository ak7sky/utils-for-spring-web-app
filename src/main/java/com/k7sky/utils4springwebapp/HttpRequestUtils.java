package com.k7sky.utils4springwebapp;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

public final class HttpRequestUtils {

    private HttpRequestUtils() {
    }

    public static String getParam(String parameterName) {
        HttpServletRequest request = getRequest();
        return request == null || request.getParameter(parameterName) == null
                ? null : request.getParameter(parameterName);
    }

    public static String getParamOrDefault(String parameterName, String defaultValue) {
        HttpServletRequest request = getRequest();
        return request == null || request.getParameter(parameterName) == null
                ? defaultValue : request.getParameter(parameterName);
    }

    public static String[] getMultiValParam(String parameterName) {
        Map<String, String[]> requestParameters = getRequestParametersMap();
        return requestParameters.getOrDefault(parameterName, null);
    }

    public static String[] getMultiValParamOrDefault(String parameterName, String... defaultValues) {
        Map<String, String[]> requestParameters = getRequestParametersMap();
        return requestParameters.getOrDefault(parameterName, defaultValues);
    }

    public static String getHeader(String headerName) {
        HttpServletRequest request = getRequest();
        return request == null || request.getHeader(headerName) == null
                ? null : request.getHeader(headerName);
    }

    public static String getHeaderOrDefault(String headerName, String defaultValue) {
        HttpServletRequest request = getRequest();
        return request == null || request.getHeader(headerName) == null
                ? defaultValue : request.getHeader(headerName);
    }

    public static Object getAttribute(String attributeName) {
        HttpServletRequest request = getRequest();
        return request == null || request.getAttribute(attributeName) == null
                ? null : request.getAttribute(attributeName);
    }

    public static Object getAttributeOrDefault(String attributeName, Object defaultValue) {
        HttpServletRequest request = getRequest();
        return request == null || request.getAttribute(attributeName) == null
                ? defaultValue : request.getAttribute(attributeName);
    }

    public static void setAttribute(String attributeName, Object value) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            request.setAttribute(attributeName, value);
        }
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null
                ? null
                : ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    private static Map<String, String[]> getRequestParametersMap() {
        HttpServletRequest request = getRequest();
        return request == null ? Collections.emptyMap() : request.getParameterMap();
    }
}
