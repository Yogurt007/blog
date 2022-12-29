package io.renren.modules.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.blog.entity.BlogRecordsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 花甲
 * @create 2022/12/29 10:04
 */
@Mapper
public interface BlogRecordsDao extends BaseMapper<BlogRecordsEntity> {
}
