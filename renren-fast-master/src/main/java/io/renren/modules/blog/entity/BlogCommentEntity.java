package io.renren.modules.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author 花甲
 * @create 2023/1/30 9:37
 */

@Data
@TableName("blog_comment")
public class BlogCommentEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String content;

    //是否审核
    private int examined;

    @TableLogic
    private int deleted;

    //浏览器指纹
    private String uniqueId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
