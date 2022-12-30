package io.renren.modules.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author 花甲
 * @create 2022/12/29 9:53
 */
@Data
@TableName("blog_art")
public class BlogArtEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    private String url;

    @TableField("`describe`")
    private String describe;

    @TableLogic
    private int deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
