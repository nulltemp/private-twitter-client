package com.nulltemp.twitter.db.mapper;

import com.nulltemp.twitter.db.dto.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemUserMapper {
	SystemUser findByEmail(@Param("email") String email);
}
