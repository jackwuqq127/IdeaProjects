package com.zit.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class JsonView implements View {

    @Override //resp.setContentType()
    public String getContentType() {
        return "text/json";
    }

    @Override
    public void render(Map<String, ?> map,HttpServletRequest req,HttpServletResponse resp)
            throws Exception {
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(map.toString());
    }
}
