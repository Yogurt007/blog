package io.renren.modules.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.blog.dao.BlogArtDao;
import io.renren.modules.blog.entity.BlogArtEntity;
import io.renren.modules.blog.service.BlogArtService;
import org.springframework.stereotype.Service;

/**
 * @author 花甲
 * @create 2022/12/29 10:34
 */
@Service
public class BlogArtServiceImpl extends ServiceImpl<BlogArtDao, BlogArtEntity> implements BlogArtService {
}
