package com.lisj.mail.mapper;

import com.lisj.mail.config.datebase.BaseMapper;
import com.lisj.mail.entity.MstDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface MstDictMapper extends BaseMapper<MstDict> {

    List<MstDict> findByStatus(String mstDict);
}