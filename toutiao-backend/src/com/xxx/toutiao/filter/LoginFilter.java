package com.xxx.toutiao.filter;

import com.mysql.jdbc.StringUtils;
import com.xxx.toutiao.common.Result;
import com.xxx.toutiao.common.ResultCodeEnum;
import com.xxx.toutiao.util.JwtHelper;
import com.xxx.toutiao.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        if (!StringUtils.isNullOrEmpty(token) && !JwtHelper.isExpiration(token)) {
            filterChain.doFilter(request, response);
        } else {
            WebUtil.writeJson(response, Result.build(null, ResultCodeEnum.NOT_LONGIN));
        }
    }
}
