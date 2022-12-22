package io.renren.modules.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.utils.R;
import io.renren.modules.blog.entity.BlogArticleEntity;
import io.renren.modules.blog.service.BlogArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/16 10:41
 */

@RestController
@Api("博客-文章")
@RequestMapping("/blog/article")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    @ApiOperation("添加文章")
    @PostMapping("/save")
    public R save(@RequestBody BlogArticleEntity blogArticleEntity){
        System.out.println("要保存的值为："+ blogArticleEntity);
        boolean save = blogArticleService.save(blogArticleEntity);
        return true ? R.ok() : R.error("添加失败");
    }

    @ApiOperation("文章列表")
    @GetMapping("/list")
    public R list(){
        List<BlogArticleEntity> list = blogArticleService.list();
        return R.ok().put("list",list);
    }

    @ApiOperation("分页列表")
    @GetMapping("/page/{page}/{pageSize}")
    public R page(@PathVariable("page") int page,@PathVariable("pageSize") int pageSize){
        System.out.println("正在获取分页数据" + page + "|" + pageSize);
        Page<BlogArticleEntity> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<BlogArticleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(BlogArticleEntity::getUpdateTime);
        blogArticleService.page(pageInfo,queryWrapper);
        System.out.println(pageInfo);
        return R.ok().put("pageInfo",pageInfo);
    }

    @ApiOperation("删除文章")
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
        boolean delete = blogArticleService.removeById(id);
        return delete ? R.ok("删除成功") : R.error("删除失败");
    }

    @ApiOperation("根据ID获取文章")
    @GetMapping("/get/{id}")
    public R get(@PathVariable("id")Long id){
        BlogArticleEntity blogArticle = blogArticleService.getById(id);
        return R.ok().put("article",blogArticle);
    }

    @ApiOperation("忽略deleted集合")
    @GetMapping("/articleList")
    public R articleList(){
        List<BlogArticleEntity> articleList = blogArticleService.articleList();
        return R.ok().put("articleList",articleList);
    }

    @ApiOperation("更新文章")
    @PostMapping("/update")
    public R update(@RequestBody BlogArticleEntity blogArticleEntity){
        boolean update = blogArticleService.updateById(blogArticleEntity);
        return update ? R.ok() : R.error();
    }

    @ApiOperation("浏览数+1")
    @GetMapping("/look/{id}")
    public R look(@PathVariable("id")Long id){
        BlogArticleEntity article = blogArticleService.getById(id);
        article.setLook(article.getLook() + 1);
        boolean lookResult = blogArticleService.updateById(article);
        return lookResult ? R.ok() : R.error();
    }

    @ApiOperation("点赞数+1")
    @GetMapping("/love/{id}")
    public R love(@PathVariable("id")Long id){
        BlogArticleEntity article = blogArticleService.getById(id);
        article.setLove(article.getLove() + 1);
        boolean love = blogArticleService.updateById(article);
        return love ? R.ok() : R.error();
    }
}
