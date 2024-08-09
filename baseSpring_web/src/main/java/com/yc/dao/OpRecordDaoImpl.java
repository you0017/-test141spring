package com.yc.dao;


import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpRecordDaoImpl implements OpRecordDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*@Autowired
    public void init(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }*/

    @Override
    public void insertOpRecord(OpRecord opRecord) {
        String sql = "insert into oprecord(accountid,optype,optime,opmoney,transferid) values(?,?,now(),?,?)";
        jdbcTemplate.update(sql,opRecord.getAccountid(),opRecord.getOptype().getValue(),opRecord.getOpmoney(),opRecord.getTransferid());
    }

    @Override
    public List<OpRecord> findOpRecord(int accountid) {
        String sql = "select * from oprecord where accountid=? order by optime desc";
        List<OpRecord> list = jdbcTemplate.query(sql,(rs, rowNum) -> {
            OpRecord opRecord = new OpRecord();
            opRecord.setId(rs.getInt(1));
            opRecord.setAccountid(rs.getInt(2));
            opRecord.setOpmoney(rs.getDouble(3));
            opRecord.setDatetime(rs.getString(4));
            String optype = rs.getString(5);//对象中用的枚举
            if (optype.equalsIgnoreCase("withdraw")){
                opRecord.setOptype(OpType.WITHDRAW);
            }else if (optype.equalsIgnoreCase("deposite")){
                opRecord.setOptype(OpType.DEPOSITE);
            }else if (optype.equalsIgnoreCase("transfer")){
                opRecord.setOptype(OpType.TRANSFER);
            }
            opRecord.setTransferid(rs.getInt(6));
            return opRecord;
        },accountid);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(int accountid, String opType) {
        String sql = "select * from oprecord where accountid=? and optype=? order by optime desc";
        List<OpRecord> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            OpRecord opRecord = new OpRecord();
            opRecord.setId(rs.getInt(1));
            opRecord.setAccountid(rs.getInt(2));
            opRecord.setOpmoney(rs.getDouble(3));
            opRecord.setDatetime(rs.getString(4));
            String optype = rs.getString(5);
            if (optype.equalsIgnoreCase("withdraw")) {
                opRecord.setOptype(OpType.WITHDRAW);
            } else if (optype.equalsIgnoreCase("deposite")) {
                opRecord.setOptype(OpType.DEPOSITE);
            } else if (optype.equalsIgnoreCase("transfer")) {
                opRecord.setOptype(OpType.TRANSFER);
            }
            opRecord.setTransferid(rs.getInt(6));
            return opRecord;
        }, accountid, opType);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(OpRecord opRecord) {
        return null;
    }
}
