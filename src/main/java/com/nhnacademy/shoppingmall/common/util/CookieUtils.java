package com.nhnacademy.shoppingmall.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtils {

    public static String getCookieValue(HttpServletRequest req, String name) {
        if(req.getCookies() == null) {
            return null;
        }

        for(Cookie cookie : req.getCookies()) {
            if(cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
