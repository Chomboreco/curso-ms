package com.chombo.ms.springbootserviciozuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request enrutaod a %s", request.getMethod(), request.getRequestURL().toString()));

        Long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);

        return true;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * Tipo de filtro que se va a usar: pre, route o post.
     */
    @Override
    public String filterType() {
        return "pre";
    }
}
