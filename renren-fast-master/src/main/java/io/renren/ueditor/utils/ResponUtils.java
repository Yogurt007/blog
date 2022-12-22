package io.renren.ueditor.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 花甲
 * @create 2022/12/21 16:40
 */
public class ResponUtils {
    public static void render(HttpServletResponse response, String contentType, String text){
        response.setContentType(contentType);
        try {
            response.getWriter().write(text);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //发送的是Json
    public static void renderJson(HttpServletResponse response,String text){
        render(response,"application/json;charset=UTF-8",text);
    }
    //发送xml
    public static void renderXml(HttpServletResponse response,String text){
        render(response,"text/xml;charset=UTF-8",text);
    }
    //发送text
    public static void renderText(HttpServletResponse response,String text){
        render(response,"text/plain;charset=UTF-8",text);
    }
}