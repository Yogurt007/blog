package io.renren.modules.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 花甲
 * @create 2022/12/29 14:21
 */

@Data
public class RecordsDto {

    private String title;

    private String content;

    private List<String> describe;

    private List<String> url;


}
