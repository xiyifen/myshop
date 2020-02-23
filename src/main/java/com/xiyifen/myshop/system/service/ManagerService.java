package com.xiyifen.myshop.system.service;

import com.xiyifen.myshop.domain.QueryRequest;
import com.xiyifen.myshop.system.entity.Manager;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiyifen
 */
public interface ManagerService extends IService<Manager> {

     Set<String> getUserPermissions(List<String> pId);

     Map<String,Object> findManagers(String query, QueryRequest request);
}
