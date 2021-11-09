package com.example.demo.mapper;

import com.example.demo.entity.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface RuleMapper {
    @Update("INSERT INTO `test`.`rule` (`aid`,`platform`, `download_url`, `update_version_code`, `md5`, `device_id_list`, `max_update_version_code`, `min_update_version_code`, `max_os_api`, `min_os_api`, `cpu_arch`, `channel`, `title`, `update_tips`, `status`) VALUES (#{aid}, #{platform}, #{download_url}, #{update_version_code}, #{md5}, #{device_id_list}, #{max_update_version_code}, #{min_update_version_code}, #{max_os_api}, #{min_os_api}, #{cpu_arch}, #{channel}, #{title}, #{update_tips}, #{status});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    int insert(Rule rule);

    @Select("SELECT * FROM `test`.`rule` LIMIT #{pageNum}, #{pageCount}")
    List<Rule> select(int pageNum, int pageCount);

    @Select("SELECT COUNT(*) FROM `test`.`rule`")
    int getRulesNum();
}
