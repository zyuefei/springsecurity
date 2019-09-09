package com.portjs.base.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息工具类
 * @Author:zhangyuefei
 * @Date:2019/8/5 15:20
 */

public class Page<T> implements IPage<T> {

    private long pageNum;

    private long pageSize;


    private List<OrderItem> orders;

    private long total;

    private List records;

    public Page() {
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param orderBy 排序字段
     * @param asc 是否升序
     */
    public Page(long pageNum, long pageSize, String orderBy, boolean asc) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;

        OrderItem orderItem = new OrderItem();
        orderItem.setColumn(orderBy);
        orderItem.setAsc(asc);
        List<OrderItem> list = new ArrayList<>();
        list.add(orderItem);
        this.orders = list;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    @Override
    public List<OrderItem> orders() {
        return orders;
    }

    @Override
    public List getRecords() {
        return records;
    }

    @Override
    public IPage setRecords(List records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public IPage setTotal(long total) {
        this.total = total;
        return this;
    }


    @Override
    public long getSize() {
        return pageSize;
    }

    @Override
    public IPage setSize(long size) {
        this.pageSize = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return pageNum;
    }

    @Override
    public IPage setCurrent(long current) {
        this.pageNum = current;
        return this;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", orders=" + orders +
                ", total=" + total +
                ", records=" + records +
                '}';
    }
}
