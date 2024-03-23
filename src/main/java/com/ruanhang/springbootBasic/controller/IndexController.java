package com.ruanhang.springbootBasic.controller;

import com.ruanhang.springbootBasic.service.IndexService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页控制层
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @ApiOperation(value = "盈利分页查询", notes = "盈利查询")
    @RequestMapping(value = "/listSumYinliData", method = RequestMethod.GET)
    public Map listSumYinliData(Integer pageNum,Integer pageSize) {
        return indexService.listSumYinliData(pageNum, pageSize);
    }






}
