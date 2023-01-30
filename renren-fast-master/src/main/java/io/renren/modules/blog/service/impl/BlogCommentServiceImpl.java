package io.renren.modules.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.blog.dao.BlogCommentDao;
import io.renren.modules.blog.entity.BlogCommentEntity;
import io.renren.modules.blog.service.BlogCommentService;
import org.springframework.stereotype.Service;

/**
 * @author 花甲
 * @create 2023/1/30 9:43
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentDao, BlogCommentEntity> implements BlogCommentService {
}
