package com.kuznetsov.linoleum.util;

public final class JspHelper {
    private static final String JSPFORMAT = "/WEB-INF/jsp/%s.jsp";
    public static String getPath(String jspName){
       return String.format(JSPFORMAT,jspName);
    }
}
