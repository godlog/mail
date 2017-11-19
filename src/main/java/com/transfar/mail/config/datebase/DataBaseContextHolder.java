package com.transfar.mail.config.datebase;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/18 18:47
 * @version:1.0.0
 */
public class DataBaseContextHolder {

    public enum  DataBaseType{
        MASTER,SLAVE
    }
    //为什么用ThreadLocal
    private static final  ThreadLocal<DataBaseType> contextHolder =new ThreadLocal<>();


    public static void setDataBaseType(DataBaseType dateBaseType){
        if (dateBaseType == null){
            throw new NullPointerException();
        }
        contextHolder.set(dateBaseType);
    }
    public static DataBaseType getDataBaseType(){
        return contextHolder.get()==null? DataBaseType.MASTER:contextHolder.get();
    }
    public static void clearDataBaseType(){
        contextHolder.remove();
    }

}
