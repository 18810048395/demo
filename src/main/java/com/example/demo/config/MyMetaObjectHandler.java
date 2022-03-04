package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("==start insert ······==");
        if (Objects.isNull(getFieldValByName("createTime", metaObject))) {
            this.setFieldValByName("createTime", new Date(), metaObject);
        }

        if (Objects.isNull(getFieldValByName("updateTime", metaObject))) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("==start update ······==");

        if (Objects.isNull(getFieldValByName("updateTime", metaObject))) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}
