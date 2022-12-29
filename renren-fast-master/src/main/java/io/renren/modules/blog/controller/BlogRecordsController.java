package io.renren.modules.blog.controller;

import io.renren.common.utils.R;
import io.renren.modules.blog.dto.RecordsDto;
import io.renren.modules.blog.service.BlogRecordsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 花甲
 * @create 2022/12/29 16:19
 */

@RestController
@Api("博客-记录")
@RequestMapping("/blog/records")
@Slf4j
public class BlogRecordsController {

    @Autowired
    private BlogRecordsService blogRecordsService;

    @PostMapping("/save")
    public R save(@RequestBody RecordsDto recordsDto){
        blogRecordsService.saveDto(recordsDto);
        log.info("要保存的内容为:{}",recordsDto);
        return R.ok();
    }

}