package io.renren.modules.blog.dto;

import io.renren.modules.blog.entity.BlogArtEntity;
import io.renren.modules.blog.entity.BlogRecordsEntity;
import lombok.Data;

import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/30 10:17
 */
@Data
public class ArtDto {

    private BlogRecordsEntity blogRecordsEntity;

    private List<BlogArtEntity> artList;

}
