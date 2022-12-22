package io.renren.modules.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.blog.dao.BlogArticleDao;
import io.renren.modules.blog.entity.BlogArticleEntity;
import io.renren.modules.blog.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/16 10:41
 */

@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleDao, BlogArticleEntity> implements BlogArticleService {

    @Autowired
    private BlogArticleDao blogArticleDao;

    @Override
    public List<BlogArticleEntity> articleList() {
        List<BlogArticleEntity> articleList = blogArticleDao.articleList();
        return  articleList;
    }
}
