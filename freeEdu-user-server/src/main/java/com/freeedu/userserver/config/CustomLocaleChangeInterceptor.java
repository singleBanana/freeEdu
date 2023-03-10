package com.freeedu.userserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wd
 */
@Slf4j
public class CustomLocaleChangeInterceptor extends LocaleChangeInterceptor {


    /**
     * try to get locale from header, if not exist then get it from request parameter.
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return bool
     * @throws ServletException ServletException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        String header = request.getHeader(getParamName());
        if (header != null) {
            if (checkHttpMethods(request.getMethod())) {
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
                }
                try {
                    localeResolver.setLocale(request, response, parseLocaleValue(header));
                } catch (IllegalArgumentException ex) {
                    if (isIgnoreInvalidLocale()) {
                        log.debug("Ignoring invalid locale value [" + header + "]: " + ex.getMessage());
                    } else {
                        throw ex;
                    }
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean checkHttpMethods(String currentMethod) {
        String[] httpMethods = getHttpMethods();
        if (ObjectUtils.isEmpty(httpMethods)) {
            return true;
        }
        for (String method : httpMethods) {
            if (method.equalsIgnoreCase(currentMethod)) {
                return true;
            }
        }
        return false;
    }

}
