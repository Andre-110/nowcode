package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlphaService {
    public AlphaService(){
        System.out.println("实例化");
    }
    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁");
    }
    @Autowired
    private AlphaDao alphaDao;
    public String find(){
        return alphaDao.select();
    }
}
