package io.renren.modules.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.blog.dao.BlogRecordsDao;
import io.renren.modules.blog.dto.RecordsDto;
import io.renren.modules.blog.entity.BlogArtEntity;
import io.renren.modules.blog.entity.BlogRecordsEntity;
import io.renren.modules.blog.service.BlogArtService;
import io.renren.modules.blog.service.BlogRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/29 10:14
 */
@Service
public class BlogRecordsServiceImpl extends ServiceImpl<BlogRecordsDao, BlogRecordsEntity> implements BlogRecordsService {

    @Autowired
    private BlogArtService blogArtService;

    @Override
    public boolean saveDto(RecordsDto recordsDto) {
        //存大纲
        BlogRecordsEntity records = new BlogRecordsEntity();
        records.setTitle(recordsDto.getTitle());
        records.setContent(recordsDto.getContent());
        boolean save = this.save(records);
        if (!save){
            return false;
        }
        //获取id
        QueryWrapper<BlogRecordsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",records.getTitle());
        BlogRecordsEntity blogRecords = this.getOne(queryWrapper);
        Long id = blogRecords.getId();
        List<String> urlList = recordsDto.getUrl();
        for (String url : urlList) {
            BlogArtEntity art = new BlogArtEntity();
            art.setUrl(url);
            art.setParent_id(id);
            boolean saveArt = blogArtService.save(art);
            if (!saveArt){
                return false;
            }
        }
        return true;
    }
}