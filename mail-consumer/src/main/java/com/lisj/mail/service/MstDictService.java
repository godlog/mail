package com.lisj.mail.service;

import com.lisj.mail.config.datebase.ReadOnlyConnection;
import com.lisj.mail.entity.MstDict;
import com.lisj.mail.mapper.MstDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/19 17:03
 * @version:1.0.0
 */
@Service
public class MstDictService {
    @Autowired
    private MstDictMapper mstDictMapper;

    @ReadOnlyConnection
    public List<MstDict> findByStatus(String status){
        return  this.mstDictMapper.findByStatus(status);
    }
}
