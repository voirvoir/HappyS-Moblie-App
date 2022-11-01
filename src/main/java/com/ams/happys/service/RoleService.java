package com.ams.happys.service;

import java.util.List;
import com.ams.happys.domain.Role;
import com.ams.happys.dto.RoleDto;

public interface RoleService extends GenericService<Role, Long> {
	
	Role findByName(String name);

	Role findById(Long roleId);

	RoleDto createRole(RoleDto roleDto);
	
	RoleDto updateRole(RoleDto roleDto, Long roleId);

	public List<RoleDto> findAll();
}
