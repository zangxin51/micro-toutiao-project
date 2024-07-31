package com.xxx.toutiao.controller;

import com.mysql.jdbc.StringUtils;
import com.xxx.toutiao.common.Result;
import com.xxx.toutiao.common.ResultCodeEnum;
import com.xxx.toutiao.pojo.NewsHeadline;
import com.xxx.toutiao.pojo.vo.HeadlineDetailVo;
import com.xxx.toutiao.service.NewsHeadlineService;
import com.xxx.toutiao.service.impl.NewsHeadlineServiceImpl;
import com.xxx.toutiao.util.JwtHelper;
import com.xxx.toutiao.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController {
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    /**
     * 用户在客户端输入发布的新闻信息完毕后
     * 发布前先请求后端的登录校验接口验证登录
     * 登录通过则提交新闻信息
     * 后端将新闻信息存入数据库
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        // 接收请求体参数
        HeadlineDetailVo vo = WebUtil.readJson(req, HeadlineDetailVo.class);
        // 从token中获取用户的userId
        String token = req.getHeader("token");
        if (!StringUtils.isNullOrEmpty(token) && !JwtHelper.isExpiration(token)) {
            Long userId = JwtHelper.getUserId(token);
            vo.setPublisher(userId.intValue());
            int rows = headlineService.save(vo);
            if (rows > 0) {
                result = Result.ok(null);
            } else {
                result = Result.fail(null);
            }
        } else {
            result = Result.build(null, ResultCodeEnum.NOT_LONGIN);
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * + 客户端将新闻信息修改后,提交前先请求登录校验接口校验登录状态
     * + 登录校验通过则提交修改后的新闻信息,后端接收并更新进入数据库
     *
     * @param req  req
     * @param resp resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        HeadlineDetailVo vo = WebUtil.readJson(req, HeadlineDetailVo.class);
        String token = req.getHeader("token");
        if (!StringUtils.isNullOrEmpty(token) && !JwtHelper.isExpiration(token)) {
            int rows = headlineService.save(vo);
            if (rows > 0) {
                result = Result.ok(null);
            } else {
                result = Result.fail(null);
            }
        } else {
            result = Result.build(null, ResultCodeEnum.NOT_LONGIN);
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * + 前端先调用登录校验接口,校验登录是否过期
     * + 登录校验通过后 ,则根据新闻id查询新闻的完整信息并响应给前端
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        String token = req.getHeader("token");
        if (!StringUtils.isNullOrEmpty(token) && !JwtHelper.isExpiration(token)) {
            String hid = req.getParameter("hid");
            NewsHeadline headline = headlineService.findHeadlineByHid(Integer.valueOf(hid));
            Map<String, Object> map = new HashMap<>();
            map.put("headline", headline);
            result = Result.ok(map);
        } else {
            result = Result.build(null, ResultCodeEnum.NOT_LONGIN);
        }
        WebUtil.writeJson(resp, result);
    }

    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        String token = req.getHeader("token");
        if (!StringUtils.isNullOrEmpty(token) && !JwtHelper.isExpiration(token)) {
            String hid = req.getParameter("hid");
            int rows = headlineService.removeByHid(Integer.valueOf(hid));
            if (rows > 0) {
                result = Result.ok(null);
            } else {
                result = Result.fail(null);
            }
        } else {
            result = Result.build(null, ResultCodeEnum.NOT_LONGIN);
        }
        WebUtil.writeJson(resp, result);
    }
}

