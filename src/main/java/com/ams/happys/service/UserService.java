package com.ams.happys.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import com.ams.happys.domain.User;
import com.ams.happys.dto.UserDto;

public interface UserService extends GenericService<User, Long> {

	public User findEntityByUsername(String username);

	public UserDto findByUsername(String username);

	public UserDto findByEmail(String email);

	public Page<UserDto> searchByPageBasicInfo(int pageIndex, int pageSize, String username);

	public Page<UserDto> findByPageBasicInfo(int pageIndex, int pageSize);

	public UserDto save(UserDto user);

	public Page<UserDto> findByPage(int pageIndex, int pageSize);

	public UserDto getCurrentUser();

	public byte[] getProfilePhoto(String username);

	public UserDto savePhoto(UserDto dto);

	public boolean passwordMatch(UserDto dto);

	public UserDto changePassword(UserDto dto);

	public Page<UserDto> findByPageUsername(String username, int pageIndex, int pageSize);

	public boolean emailAlreadyUsed(UserDto dto);

	public UserDto findByUserId(Long userId);

	public UserDto deleteById(Long userId);

	User updateUserLastLogin(Long userId);

	User saveUser(UserDto userDto);

	User getCurrentEntityUser();

	public void recordLoginFailure(Authentication authentication);

}
