package com.hebust.thesismanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hebust.thesismanage.common.Result;
import com.hebust.thesismanage.dto.ThesisDto;
import com.hebust.thesismanage.entity.Thesis;
import com.hebust.thesismanage.service.ThesisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/thesis")
public class ThesisController {
    @Autowired
    private ThesisService thesisService;


    @GetMapping("/{id}")
    public Result<ThesisDto> getById(@PathVariable Long id) {
        log.info("getById被执行了！ID为：{}", id);
        ThesisDto thesisById = thesisService.getThesisDto(id);
        log.info("查询完毕，结果：{}", thesisById);
        return Result.success(thesisById);
    }

    @GetMapping("/findAll")
    public Result<List<ThesisDto>> getAll() {
        List<ThesisDto> result = thesisService.getAll();
        return Result.success(result);
    }

    @GetMapping("/page")
    public Result<Page<ThesisDto>> page(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String title) {
        Page<ThesisDto> thesisDtoPage = thesisService.page(page, pageSize, title);
        return Result.success(thesisDtoPage);
    }

    @DeleteMapping
    public Result<String> delete(@RequestParam("ids") List<Integer> ids) {
//        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        thesisService.removeByIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping
    public Result<String> add(@RequestBody ThesisDto thesisDto) {
        thesisService.save(thesisDto);
        return Result.success("添加论文成功");
    }

}
