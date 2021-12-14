package com.chombo.ms.springbootserviciozuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("Entrando a post");

        Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        Long tiempoFinal = System.currentTimeMillis();
        Long tiemTranscurrido = tiempoFinal - tiempoInicio;

        log.info(String.format("Tiempo transcurrido en segundos %s", tiemTranscurrido.doubleValue()/1000.0));
        log.info(String.format("Tiempo transcurrido en milisegundos %s", tiemTranscurrido));

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
        return "post";
    }
}
