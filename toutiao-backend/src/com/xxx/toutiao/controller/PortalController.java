package com.xxx.toutiao.controller;

import com.mysql.jdbc.StringUtils;
import com.xxx.toutiao.common.Result;
import com.xxx.toutiao.pojo.vo.HeadlineDetailVo;
import com.xxx.toutiao.pojo.vo.HeadlineQueryVo;
import com.xxx.toutiao.service.NewsHeadlineService;
import com.xxx.toutiao.service.impl.NewsHeadlineServiceImpl;
import com.xxx.toutiao.util.WebUtil;
import com.xxx.toutiao.pojo.NewsType;
import com.xxx.toutiao.service.NewsTypeService;
import com.xxx.toutiao.service.impl.NewsTypeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门户控制器
 * 那些不需要登录,不需要做增删改查的门户的请求都放在这里
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private NewsTypeService typeService = new NewsTypeServiceImpl();
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    /**
     * 进入新闻首页,查询所有分类并动态展示新闻类别栏位
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<NewsType> typeList = typeService.findAll();
            Result result = Result.ok(typeList);
            WebUtil.writeJson(resp, result);
        } catch (Exception e) {
            WebUtil.writeJson(resp, Result.fail(null));
        }
    }

    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        // 将参数传递给服务层,进行分页查询
        Map<String, Object> pageInfo = headlineService.findPage(headlineQueryVo);
        // 将分页查询的结果转换json响应给客户端
        Map data = new HashMap();
        data.put("pageInfo", pageInfo);
        Result result = Result.ok(data);
        WebUtil.writeJson(resp, result);
    }

    /**
     * 用户点击"查看全文"时,向服务端发送新闻id
     * 后端根据新闻id查询完整新闻文章信息并返回
     * 后端要同时让新闻的浏览量+1
     * param: hid=1
     * response: HeadlineDetailVo的json数据
     *
     * @param req  req
     * @param resp resp
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        String hid = req.getParameter("hid");
        try {
            if (StringUtils.isNullOrEmpty(hid)) {
                throw new RuntimeException("hid is null");
            }
            HeadlineDetailVo vo = headlineService.findDetail(Integer.valueOf(hid));
            result = Result.ok(vo);
        } catch (Exception e){
            e.printStackTrace();
            result = Result.fail(null);
        }
        WebUtil.writeJson(resp,result);
    }
}
