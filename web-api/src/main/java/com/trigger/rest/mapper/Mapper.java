//package com.trigger.rest.mapper;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//public final class Mapper {
//
//    private static ModelMapper mapper;
//
//    private Mapper() {
//    }
//
//    public static ModelMapper getInstance() {
//        if(mapper == null) {
//            mapper = new ModelMapper();
//        }
//
//        return mapper;
//    }
//
//}
