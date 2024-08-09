package com.yc.dao;


import com.yc.bean.OpRecord;

import java.util.List;

public interface OpRecordDao {
    public void insertOpRecord(OpRecord opRecord);

    // 查询一个用户所有的日志，根据时间排序
    public List<OpRecord> findOpRecord(int accountid);

    public List<OpRecord> findOpRecord(int accountid, String optype);


    public List<OpRecord> findOpRecord(OpRecord opRecord);
}
