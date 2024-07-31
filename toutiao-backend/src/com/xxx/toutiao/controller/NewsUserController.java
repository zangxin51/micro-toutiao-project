package com.xxx.toutiao.controller;

import com.mysql.jdbc.StringUtils;
import com.xxx.toutiao.common.Result;
import com.xxx.toutiao.common.ResultCodeEnum;
import com.xxx.toutiao.service.NewsUserService;
import com.xxx.toutiao.service.impl.NewsUserServiceImpl;
import com.xxx.toutiao.util.JwtHelper;
import com.xxx.toutiao.util.MD5Util;
import com.xxx.toutiao.util.WebUtil;
import com.xxx.toutiao.pojo.NewsUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController {
    private NewsUserService userService = new NewsUserServiceImpl();

    /**
     * 用户在客户端输入用户名密码并向后端提交,后端根据用户名和密码判断登录是否成功,用户有误或者密码有误响应不同的提示信息
     *
     * @param req  req
     * @param resp resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将前端json数据反序列化user对象
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        // 调用服务层方法查询user是否存在
        NewsUser loginUser = userService.findByUsername(paramUser.getUsername());
        Result result = null;
        if (loginUser != null) {
            // 密码是否匹配
            if (MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())) {
                // 登录成功后, 放入token到响应数据中
                Integer uid = loginUser.getUid();
                String token = JwtHelper.createToken(Long.valueOf(uid));
                Map<String, Object> map = new HashMap<>();
                map.put("token", token);
                result = Result.ok(map);
            } else {
                // 密码不匹配
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        } else {
            // 当前登录用户名有误
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 客户端发送请求,提交token请求头,后端根据token请求头获取登录用户的详细信息并响应给客户端进行存储
     *
     * @param req  req
     * @param resp resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取token
        String token = req.getHeader("token");
        Result result = null;
        if (token != null && token != "" && !JwtHelper.isExpiration(token)) {
            Long userId = JwtHelper.getUserId(token);
            NewsUser loginUser = userService.findByUid(userId.intValue());
            if (loginUser != null) {
                Map<String, Object> map = new HashMap<>();
                // 发给前端时要把密码设置为空
                loginUser.setUserPwd("");
                map.put("loginUser", loginUser);
                result = Result.ok(map);
            } else {
                result = Result.build(null, ResultCodeEnum.NOT_LONGIN);
            }
        } else {
            result = Result.build(null, ResultCodeEnum.NOT_LONGIN);
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 用户在注册时输入用户名时,立刻将用户名发送给后端,后端根据用户名查询用户名是否可用并做出响应
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        NewsUser user = userService.findByUsername(username);
        Result result = null;
        if (user != null) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        } else {
            result = Result.ok(null);
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 客户端将新用户信息发送给服务端,服务端将新用户存入数据库,
     * 存入之前做用户名是否被占用校验,
     * 校验通过响应成功提示,否则响应失败提示
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        // 户名是否被占用校验
        NewsUser user = userService.findByUsername(paramUser.getUsername());
        if (user != null) {
            // 被占用
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        } else {
            // 可以使用,保存到数据库
            int rows = userService.save(paramUser);
            if (rows > 0) {
                result = Result.ok(null);
            } else {
                result = Result.fail(null);
            }
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 客户端在进入发布页前、发布新闻前、进入修改页前、修改前、删除新闻前先向服务端发送请求携带token请求头
     * 后端接收token请求头后,校验用户登录是否过期并做响应
     * 前端根据响应信息提示用户进入登录页还是进入正常业务页面
     *
     * @param req  req
     * @param resp resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        String token = req.getHeader("token");
        if (!StringUtils.isNullOrEmpty(token) && !JwtHelper.isExpiration(token)) {
            result = Result.ok(null);
        } else {
            result = Result.build(null, ResultCodeEnum.NOT_LONGIN);
        }
        WebUtil.writeJson(resp, result);
    }
}
