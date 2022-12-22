package io.renren.modules.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.blog.entity.BlogArticleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/16 10:41
 */

@Mapper
public interface BlogArticleDao extends BaseMapper<BlogArticleEntity> {
    List<BlogArticleEntity> articleList();
}
