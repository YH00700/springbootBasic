package com.ruanhang.springbootBasic.service.impl;

import com.ruanhang.springbootBasic.service.IndexService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    /**
     * 盈利列表数据
     *
     * @return
     */
    @Override
    public Map listSumYinliData(Integer pageNum, Integer pageSize) {
        Map map = new HashMap();
        return map;
    }
}
