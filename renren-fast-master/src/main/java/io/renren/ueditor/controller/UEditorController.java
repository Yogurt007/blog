package io.renren.ueditor.controller;

import ch.qos.logback.core.net.server.Client;
import io.renren.modules.oss.cloud.OSSFactory;
import io.renren.ueditor.ActionEnter;
import io.renren.ueditor.utils.ResponUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import net.sf.json.JSONObject;
/**
 * @author 花甲
 * @create 2022/12/21 11:36
 */

@CrossOrigin
@Controller
@Slf4j
@Api("ueditor")
@RequestMapping("/ueditor")
public class UEditorController {

    @ApiOperation("上传")
    @ResponseBody
    @RequestMapping(value="/ueditorUpload.do", method={RequestMethod.GET, RequestMethod.POST})
    public void editorUpload(HttpServletRequest request, HttpServletResponse response, String action) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            if("config".equals(action)){    //如果是初始化
                log.info("uedito初始化请求");
                String exec = new ActionEnter(request, rootPath).exec();
                PrintWriter writer = response.getWriter();
                writer.write(exec);
                writer.flush();
                writer.close();
            }else if("uploadimage".equals(action)){    //如果是上传图片、视频、和其他文件
                log.info("uedito上传文件");
                CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
                commonsMultipartResolver.setDefaultEncoding("utf-8");
                MultipartHttpServletRequest mulReq = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> files = mulReq.getFileMap();
                for(MultipartFile pic: files.values()) {
                    JSONObject jo = new JSONObject();
                    long size = pic.getSize();    //文件大小
                    log.info("文件大小为：{}",size);
                    String originalFilename = pic.getOriginalFilename();  //原来的文件名
                    //上传文件
                    String suffix = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
                    String suffix_url = OSSFactory.build().uploadSuffix(pic.getBytes(), suffix);
                    String url = "https://edu-po.oss-cn-beijing.aliyuncs.com" + suffix_url;
                    log.info("阿里云文件地址为：{}",url);
                    log.info("文件名字为：{}",originalFilename);
                    jo.put("state", "SUCCESS");
                    jo.put("original", originalFilename);//原来的文件名
                    jo.put("size", size);
                    jo.put("title", "helloworld");
                    jo.put("type", FilenameUtils.getExtension(pic.getOriginalFilename()));
                    jo.put("url", url);//这里的url字段表示的是上传后的图片在图片服务器的完整地址（http://ip:端口/***/***/***.jpg）
                    ResponUtils.renderJson(response,jo.toString());
                }
            }
        } catch (Exception e) {
        }
    }

}
