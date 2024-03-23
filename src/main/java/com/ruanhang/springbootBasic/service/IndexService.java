package com.ruanhang.springbootBasic.service;

import java.util.Map;

public interface IndexService {

    /**
     * 盈利分页查询
     * @return
     */
    Map listSumYinliData(Integer pageNum,Integer pageSize);
}
