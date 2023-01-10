package io.renren.modules.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.blog.dto.ArtDto;
import io.renren.modules.blog.dto.RecordsDto;
import io.renren.modules.blog.entity.BlogRecordsEntity;

import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/29 10:14
 */
public interface BlogRecordsService  extends IService<BlogRecordsEntity> {
    boolean saveDto(RecordsDto recordsDto);

    List<ArtDto> listDto();

    boolean deleteArt(Long id);

    boolean deleteRecords(Long id);
}
