package com.xiyifen.myshop.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyifen.myshop.system.entity.Manager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiyifen
 */
public interface ManagerMapper extends BaseMapper<Manager> {

    @Select("<script>select m.*,r.role_name from sp_manager m LEFT JOIN sp_role r on m.role_id=r.role_id <where> <if test='query!=null'> username like CONCAT('%',#{query},'%') </if></where></script>")
    IPage<Manager> getMappersInfo(Page<Manager> page, String query);
}
