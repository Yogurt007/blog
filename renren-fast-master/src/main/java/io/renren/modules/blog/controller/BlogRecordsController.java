package io.renren.modules.blog.controller;

import io.renren.common.utils.R;
import io.renren.modules.blog.dto.ArtDto;
import io.renren.modules.blog.dto.RecordsDto;
import io.renren.modules.blog.entity.BlogRecordsEntity;
import io.renren.modules.blog.service.BlogArtService;
import io.renren.modules.blog.service.BlogRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private BlogArtService blogArtService;

    @ApiOperation("添加")
    @PostMapping("/save")
    public R save(@RequestBody RecordsDto recordsDto){
        blogRecordsService.saveDto(recordsDto);
        log.info("要保存的内容为:{}",recordsDto);
        return R.ok();
    }

    @ApiOperation("列表")
    @GetMapping("/list")
    public R list(){
        List<ArtDto> artDtoList = blogRecordsService.listDto();
        return R.ok().put("artDtoList",artDtoList);
    }

}
