package com.nulltemp.twitter.db.mapper;

import com.nulltemp.twitter.db.dto.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemUserMapper {
	SystemUser findByEmail(@Param("email") String email);

	int saveToken(@Param("id") long id, @Param("token") String token);
}
