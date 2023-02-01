package io.renren.modules.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.utils.R;
import io.renren.modules.blog.entity.BlogCommentEntity;
import io.renren.modules.blog.service.BlogCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * @author 花甲
 * @create 2023/1/30 9:55
 */
@RestController
@Api("博客-评论")
@RequestMapping("/blog/comment")
@Slf4j
public class BlogCommentController {

    @Autowired
    private BlogCommentService blogCommentService;

    @ApiOperation("保存")
    @PostMapping("/save")
    public R save(@RequestBody BlogCommentEntity blogCommentEntity){
        boolean save = blogCommentService.save(blogCommentEntity);
        return save ? R.ok() : R.error();
    }

    @ApiOperation("所有评论")
    @GetMapping("/list")
    public R list(){
        List<BlogCommentEntity> list = blogCommentService.list();
        return R.ok().put("list",list);
    }

    @ApiOperation("删除评论")
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
        boolean remove = blogCommentService.removeById(id);
        return remove ? R.ok() : R.error();
    }

    @ApiOperation("审核pass")
    @GetMapping("/pass/{id}")
    public R pass(@PathVariable("id") Long id){
        log.info("留言审核通过，id {}",id);
        BlogCommentEntity comment = blogCommentService.getById(id);
        comment.setExamined(1);
        boolean update = blogCommentService.updateById(comment);
        return update ? R.ok() : R.error();
    }

    @ApiOperation("审核fail")
    @GetMapping("/fail/{id}")
    public R fail(@PathVariable("id") Long id){
        log.info("留言审核失败，id {}",id);
        BlogCommentEntity comment = blogCommentService.getById(id);
        comment.setExamined(-1);
        boolean update = blogCommentService.updateById(comment);
        return update ? R.ok() : R.error();
    }

    @ApiOperation("分页列表")
    @GetMapping("/page/{page}/{pageSize}")
    public R page(@PathVariable("page") int page,@PathVariable("pageSize") int pageSize){
        Page<BlogCommentEntity> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<BlogCommentEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogCommentEntity::getExamined,1);
        queryWrapper.orderByDesc(BlogCommentEntity::getCreateTime);
        blogCommentService.page(pageInfo,queryWrapper);
        return R.ok().put("pageInfo",pageInfo);
    }

    @ApiOperation("根据浏览器指纹查询评论时间")
    @GetMapping("/fingerprint/{uniqueId}")
    public R fingerprint(@PathVariable("uniqueId") String uniqueId){
        log.info("指纹id：{}",uniqueId);
        LambdaQueryWrapper<BlogCommentEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogCommentEntity::getUniqueId,uniqueId);
        queryWrapper.orderByDesc(BlogCommentEntity::getCreateTime);
        List<BlogCommentEntity> list = blogCommentService.list(queryWrapper);
        //如果没有留过言，可以直接留言
        if (list.size() == 0){
            return R.ok();
        }
        BlogCommentEntity blogComment = list.get(0);
        Date createTime = blogComment.getCreateTime();
        Date nowTime = new Date();
        long create = createTime.getTime();
        long now = nowTime.getTime();
        if (now - 3600000 < create){
            log.info("留言时间距离上次还在一小时内");
            return R.error().put("error","距离上次还在一小时内");
        }else{
            log.info("可以留言了");
            return R.ok();
        }
    }




}
