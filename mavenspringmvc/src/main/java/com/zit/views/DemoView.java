package com.zit.views;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class DemoView implements View {
    /*视图类型*/
    @Override
    public String getContentType() {
        return "text";
    }
    @Override
    public void render(Map<String, ?> map, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.getWriter().print("自定义视图示例！");
    }
}
