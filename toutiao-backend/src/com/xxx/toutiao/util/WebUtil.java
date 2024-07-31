package com.xxx.toutiao.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.toutiao.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class WebUtil {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static <T> T readJson(HttpServletRequest request, Class<T> clazz) {
        try {
            BufferedReader reader = request.getReader();
            StringBuffer sbf = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sbf.append(line);
            }
            T obj = objectMapper.readValue(sbf.toString(), clazz);
            return obj;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeJson(HttpServletResponse resp, Result result) {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            String json = objectMapper.writeValueAsString(result);
            writer = resp.getWriter();
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
