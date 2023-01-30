package io.renren.modules.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.blog.entity.BlogCommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 花甲
 * @create 2023/1/30 9:41
 */
@Mapper
public interface BlogCommentDao extends BaseMapper<BlogCommentEntity> {
}
