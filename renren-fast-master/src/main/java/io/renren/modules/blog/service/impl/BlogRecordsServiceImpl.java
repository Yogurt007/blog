package io.renren.modules.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.blog.dao.BlogRecordsDao;
import io.renren.modules.blog.dto.ArtDto;
import io.renren.modules.blog.dto.RecordsDto;
import io.renren.modules.blog.entity.BlogArtEntity;
import io.renren.modules.blog.entity.BlogRecordsEntity;
import io.renren.modules.blog.service.BlogArtService;
import io.renren.modules.blog.service.BlogRecordsService;
import io.renren.modules.oss.service.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/29 10:14
 */
@Service
public class BlogRecordsServiceImpl extends ServiceImpl<BlogRecordsDao, BlogRecordsEntity> implements BlogRecordsService {

    @Autowired
    private BlogArtService blogArtService;

    @Autowired
    private SysOssService sysOssService;

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
            art.setParentId(id);
            boolean saveArt = blogArtService.save(art);
            if (!saveArt){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<ArtDto> listDto() {
        LambdaQueryWrapper<BlogRecordsEntity> Wrapper = new LambdaQueryWrapper<>();
        Wrapper.orderByDesc(BlogRecordsEntity::getCreateTime);
        List<BlogRecordsEntity> recordsList = this.list(Wrapper);
        List<ArtDto> artDtoList = new ArrayList<>();
        for (BlogRecordsEntity records : recordsList) {
            ArtDto artDto = new ArtDto();
            artDto.setBlogRecordsEntity(records);
            QueryWrapper<BlogArtEntity> queryWrapper = new QueryWrapper<>();
            System.out.println("id：" + records.getId());
            queryWrapper.eq("parent_id",records.getId());
            List<BlogArtEntity> artList = blogArtService.list(queryWrapper);
            artDto.setArtList(artList);
            artDtoList.add(artDto);
        }
        return artDtoList;
    }

    //删除照片，删除数据库是同时删除阿里云，节约资源
    @Override
    public boolean deleteArt(Long id) {
        boolean artRemoveResult = blogArtService.removeById(id);
        boolean sysRevoceResult = sysOssService.removeById(id);
        return artRemoveResult || sysRevoceResult;
    }

    @Override
    public boolean deleteRecords(Long id) {
        LambdaQueryWrapper<BlogArtEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogArtEntity::getParentId,id);
        blogArtService.remove(queryWrapper);
        boolean result = this.removeById(id);
        return result;
    }
}
