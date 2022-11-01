package com.ams.happys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.happys.domain.UserGroup;
import com.ams.happys.dto.UserGroupDto;
import com.ams.happys.repository.UserGroupRepository;
import com.ams.happys.service.UserGroupService;
import com.ams.happys.utils.CommonUtils;

@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupRepository repos;

	@Override
	@Transactional(readOnly = true)
	public UserGroupDto findById(Long id) {

		if (!CommonUtils.isPositive(id, true)) {
			return null;
		}

		UserGroup entity = repos.getOne(id);

		if (entity == null) {
			return null;
		} else {
			return new UserGroupDto(entity);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserGroupDto> findList(int pageIndex, int pageSize) {

		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(new Order(Direction.ASC, "name")));
		Page<UserGroup> page = repos.findAll(pageable);

		List<UserGroup> content = page.getContent();
		List<UserGroupDto> dtoContent = new ArrayList<>();

		for (int i = 0; i < content.size(); i++) {
			dtoContent.add(new UserGroupDto(content.get(i)));
		}

		return new PageImpl<UserGroupDto>(dtoContent, pageable, repos.count());
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserGroupDto> findAll() {

		List<UserGroup> list = repos.findAll();
		List<UserGroupDto> dtoList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			dtoList.add(new UserGroupDto(list.get(i)));
		}

		return dtoList;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public UserGroupDto save(UserGroupDto dto) {

		if (dto == null) {
			throw new IllegalArgumentException();
		}

		UserGroup entity = null;

		if (CommonUtils.isPositive(dto.getId(), true)) {
			entity = repos.getOne(dto.getId());
		}

		if (entity == null) {
			entity = dto.toEntity();
		} else {
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
		}

		entity = repos.save(entity);

		if (entity == null) {
			return null;
		} else {
			return new UserGroupDto(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void delete(UserGroupDto[] dtos) {

		if (dtos == null || dtos.length <= 0) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < dtos.length; i++) {
			UserGroup entity = repos.getOne(dtos[i].getId());

			try {
				repos.delete(entity);
			} catch (Exception ex) {
				throw new RuntimeException();
			}
		}
	}

}
