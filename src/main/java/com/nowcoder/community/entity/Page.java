package com.nowcoder.community.entity;

//封装分页相关数据，方便复用
//页面传入，服务器查出，计算得出
public class Page {
    // 当前页码，用户传入
    private int current = 1;

    //显示上限，用户传入
    private int limit = 10;

    //显示数据总数，用于计算中总页数
    private int rows;

    //查询路径，复用分页链接
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        //限制数据范围
        if(current >= 1){
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        //限制数据范围
        if(limit >= 1&&limit<=100){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //补充条件
    //根据页码计算当前页的起始行
    public int getOffset(){
        return (current-1)*limit;
    }
    //显示总的页数
    public int getTotal(){
        if(rows % limit == 0){
            return rows/limit;
        }
        else{
            return rows/limit+1;
        }
    }
    //根据当前页显示临近页码
    public int getFrom(){
        int from = current-2;
        return from < 1 ? 1 : from;
    }
    public int getTo(){
        int to=current+2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
