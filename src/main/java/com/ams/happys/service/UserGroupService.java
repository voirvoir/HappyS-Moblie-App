package com.ams.happys.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.ams.happys.dto.UserGroupDto;

public interface UserGroupService {

	public UserGroupDto findById(Long id);

	public Page<UserGroupDto> findList(int pageIndex, int pageSize);

	public List<UserGroupDto> findAll();

	public UserGroupDto save(UserGroupDto dto);

	public void delete(UserGroupDto[] dtos);
}
