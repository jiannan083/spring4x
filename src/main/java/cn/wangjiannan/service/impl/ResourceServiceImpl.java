package cn.wangjiannan.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.ResourceMapper;
import cn.wangjiannan.model.Resource;
import cn.wangjiannan.service.ResourceService;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
