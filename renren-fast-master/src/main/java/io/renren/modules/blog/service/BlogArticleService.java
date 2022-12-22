package io.renren.modules.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.blog.entity.BlogArticleEntity;

import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/16 10:41
 */

public interface BlogArticleService extends IService<BlogArticleEntity> {
    List<BlogArticleEntity> articleList();
}
