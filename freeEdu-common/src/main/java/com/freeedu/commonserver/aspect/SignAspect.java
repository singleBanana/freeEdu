package com.freeedu.commonserver.aspect;

import com.freeedu.commonserver.exception.BusinessException;
import com.freeedu.commonserver.utils.SignUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class SignAspect {

    private static final String SIGN_HEADER = "X-SIGN";

    @Pointcut("execution(@com.freeedu.commonserver.config.Signature * *(..))")
    private void verifySignPointCut() {

    }



    @Before("verifySignPointCut()")
    public void verify() {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String sign = request.getHeader(SIGN_HEADER);
        if (StringUtils.isEmpty(sign)) {
            throw new RuntimeException("no signature in header: " + SIGN_HEADER);
        }
        // check signature
        try {
            String generatedSign = generatedSignature(request);
            if (!sign.equals(generatedSign)) {
                throw new RuntimeException("invalid signature");
            }
        } catch (Throwable throwable) {
            throw new RuntimeException("invalid signature");
        }
    }


    private String generatedSignature(HttpServletRequest request) throws Exception {
        String bodyParam = null;
        if (request instanceof ContentCachingRequestWrapper) {
            bodyParam = new String(((ContentCachingRequestWrapper) request).getContentAsByteArray(), StandardCharsets.UTF_8);
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] paths = null;
        ServletWebRequest servletWebRequest = new ServletWebRequest(request, null);
        Map<String, String> uriTemplateVars = (Map<String, String>) servletWebRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        if (!CollectionUtils.isEmpty(uriTemplateVars)) {
            paths = uriTemplateVars.values().toArray(new String[0]);
        }

        return SignUtil.sign(bodyParam, parameterMap, paths);
    }
}
