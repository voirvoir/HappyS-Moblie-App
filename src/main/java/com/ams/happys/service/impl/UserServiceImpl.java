package com.ams.happys.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.happys.domain.Person;
import com.ams.happys.domain.Role;
import com.ams.happys.domain.User;
import com.ams.happys.domain.UserGroup;
import com.ams.happys.dto.PersonDto;
import com.ams.happys.dto.RoleDto;
import com.ams.happys.dto.UserDto;
import com.ams.happys.dto.UserGroupDto;
import com.ams.happys.repository.PersonRepository;
import com.ams.happys.repository.RoleRepository;
import com.ams.happys.repository.UserGroupRepository;
import com.ams.happys.repository.UserRepository;
import com.ams.happys.service.UserService;
import com.ams.happys.utils.CommonUtils;
import com.ams.happys.utils.SecurityUtils;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserGroupRepository groupRepos;

	@Autowired
	private RoleRepository roleRepos;

	@Autowired
	private PersonRepository personRepos;

	@Override
	@Transactional(readOnly = true)
	public UserDto findByUserId(Long userId) {
		User user = userRepository.getOne(userId);

		if (user != null) {
			return new UserDto(user);
		} else {
			return null;
		}
	}

	@Override
	public UserDto deleteById(Long userId) {
		User user = userRepository.getOne(userId);
		if (user != null) {
			String contentLog = "Delete User:" + user.getUsername();
			userRepository.delete(user);
			return new UserDto(user);
		} else {
			return null;
		}
	}

	//
	@Override
	@Transactional(readOnly = true)
	public UserDto findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return new UserDto(user);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findByEmail(String email) {
		User retUser = userRepository.findByEmail(email);

		if (retUser != null) {
			UserDto dto = new UserDto(retUser);
			dto.setPassword(null);

			return dto;
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserDto> findByPage(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);

		Page<User> page = userRepository.findAll(pageable);

		List<User> _content = page.getContent();
		List<UserDto> content = new ArrayList<UserDto>();

		for (User entity : _content) {

			// No password disclosed
			// entity.setPassword(null);

			content.add(new UserDto(entity));
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserDto> findByPageBasicInfo(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);

		Page<User> page = userRepository.findByPageBasicInfo(pageable);

		List<User> _content = page.getContent();
		List<UserDto> content = new ArrayList<UserDto>();

		for (User entity : _content) {

			// No password disclosed
			// entity.setPassword(null);

			content.add(new UserDto(entity));
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserDto> searchByPageBasicInfo(int pageIndex, int pageSize, String username) {
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);

		Page<User> page = userRepository.searchByPageBasicInfo(pageable, username);

		List<User> _content = page.getContent();
		List<UserDto> content = new ArrayList<UserDto>();

		for (User entity : _content) {

			// No password disclosed
			// entity.setPassword(null);

			content.add(new UserDto(entity));
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// User user = (User) authentication.getPrincipal();
		Object principal = authentication.getPrincipal();
		String userName = null;
		if (principal instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) principal;
			userName = userDetail.getUsername();
		} else {
			userName = principal.toString();
		}

		if (userName != null) {
			User entity = userRepository.findByUsernameAndPerson(userName);
			if (entity != null) {
				return new UserDto(entity);
			}
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public User getCurrentEntityUser() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		User user = (User) authentication.getPrincipal();

		Object principal = SecurityUtils.getPrincipal();
		String userName = null;
		if (principal instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) principal;
			userName = userDetail.getUsername();
		} else {
			userName = principal.toString();
		}
		if (userName != null) {
			User entity = userRepository.findByUsernameAndPerson(userName);
			return entity;
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public byte[] getProfilePhoto(String username) {
		if (username == null || username.trim().isEmpty()) {
			return null;
		}

		User user = userRepository.findByUsernameAndPerson(username);

		if (user == null || user.getPerson() == null || user.getPerson().getPhoto() == null) {
			if (user.getPerson() != null && user.getPerson().getImagePath() != null) {
				String filePath = user.getPerson().getImagePath();
				try {
					File file = new File(filePath);
					byte[] data = new byte[(int) file.length()];
					InputStream is = new FileInputStream(file);
					is.read(data);
					is.close();
					return data;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		return user.getPerson().getPhoto();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User saveUser(UserDto userDto) {

		if (userDto == null) {
			throw new IllegalArgumentException();
		}

		User user = null;

		if (CommonUtils.isPositive(userDto.getId(), true)) {
			user = userRepository.getOne(userDto.getId());
		}

		if (user == null) {
			user = userDto.toEntity();

			user.setJustCreated(true);

			if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
				user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
			}

		} else {
			user.setUsername(userDto.getUsername());// Nếu muốn cho đổi username thì bỏ đoạn rem này ra
			user.setEmail(userDto.getEmail());
			if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
				user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
			}
		}

		if (userDto.getRoles() != null) {
			List<Role> rs = new ArrayList<Role>();

			for (RoleDto d : userDto.getRoles()) {
				Role r = roleRepos.getOne(d.getId());

				if (r != null) {
					rs.add(r);
				}
			}

			user.getRoles().clear();
			user.getRoles().addAll(rs);
		}

		if (userDto.getGroups() != null) {
			List<UserGroup> gs = new ArrayList<>();

			for (UserGroupDto d : userDto.getGroups()) {
				UserGroup g = groupRepos.getOne(d.getId());

				if (g != null) {
					gs.add(g);
				}
			}

			user.getGroups().clear();
			user.getGroups().addAll(gs);
		}

		PersonDto personDto = userDto.getPerson();
		Person person = null;

		if (personDto != null && personDto.getId() != null) {
			person = personRepos.getOne(personDto.getId());
		}

		if (person != null) {
			person.setFirstName(personDto.getFirstName());
			person.setLastName(personDto.getLastName());
			person.setDisplayName(personDto.getDisplayName());
			person.setBirthDate(personDto.getBirthDate());
			person.setBirthPlace(personDto.getBirthPlace());
			person.setEmail(personDto.getEmail());
			person.setEndDate(personDto.getEndDate());
			person.setGender(personDto.getGender());
			person.setIdNumber(personDto.getIdNumber());
			person.setIdNumberIssueBy(personDto.getIdNumberIssueBy());
			person.setIdNumberIssueDate(personDto.getIdNumberIssueDate());
		} else if (personDto != null) {
			person = personDto.toEntity();
		}
		if (person != null) {
			user.setPerson(person);
			person.setUser(user);
		}
		if (userDto.getActive() != null) {
			user.setActive(userDto.getActive());
		} else {
			user.setActive(false);
		}
		user = userRepository.save(user);

		return user;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto save(UserDto userDto) {

		if (userDto == null) {
			throw new IllegalArgumentException();
		}

		User user = null;

		if (userDto.getId() != null) {
			user = userRepository.getOne(userDto.getId());
		}

		if (user == null) {
			user = userDto.toEntity();

			user.setJustCreated(true);

			if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
				user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
			}

		} else {
			user.setUsername(userDto.getUsername());// Nếu muốn cho đổi username thì bỏ đoạn rem này ra
			user.setEmail(userDto.getEmail());
			if (userDto.getPassword() != null && userDto.getPassword().length() > 0 && userDto.getChangePass()) {
				user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
			}
		}
		if (userDto.getRoles() != null) {
			List<Role> rs = new ArrayList<Role>();

			for (RoleDto d : userDto.getRoles()) {
				Role r = roleRepos.getOne(d.getId());

				if (r != null) {
					rs.add(r);
				}
			}

			user.getRoles().clear();
			user.getRoles().addAll(rs);
		}

		if (userDto.getGroups() != null) {
			List<UserGroup> gs = new ArrayList<>();

			for (UserGroupDto d : userDto.getGroups()) {
				UserGroup g = groupRepos.getOne(d.getId());

				if (g != null) {
					gs.add(g);
				}
			}

			user.getGroups().clear();
			user.getGroups().addAll(gs);
		}

		PersonDto personDto = userDto.getPerson();
		Person person = null;

		if (personDto != null && personDto.getId() != null) {
			person = personRepos.getOne(personDto.getId());
		}

		if (person != null) {
			person.setFirstName(personDto.getFirstName());
			person.setLastName(personDto.getLastName());
			person.setDisplayName(personDto.getDisplayName());
			person.setBirthDate(personDto.getBirthDate());
			person.setBirthPlace(personDto.getBirthPlace());
			person.setEmail(personDto.getEmail());
			person.setEndDate(personDto.getEndDate());
			person.setGender(personDto.getGender());
			person.setIdNumber(personDto.getIdNumber());
			person.setIdNumberIssueBy(personDto.getIdNumberIssueBy());
			person.setIdNumberIssueDate(personDto.getIdNumberIssueDate());
		} else if (personDto != null) {
			person = personDto.toEntity();
		}
		user.setPerson(person);

		if (person != null)
			person.setUser(user);

		user.setActive(userDto.getActive());
		user = userRepository.save(user);

		if (user != null) {
			return new UserDto(user);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto savePhoto(UserDto dto) {

		if (dto == null) {
			throw new RuntimeException();
		}

		User user = null;

		if (dto.getId() != null && dto.getId() > 0) {
			user = userRepository.getOne(dto.getId());
		}

		if (user == null) {
			throw new RuntimeException();
		}

		Person person = user.getPerson();
		if (person == null) {
			person = new Person();
		}

		person.setPhoto(dto.getPerson().getPhoto());
		person.setPhotoCropped(dto.getPerson().getPhotoCropped());

		person.setUser(user);
		user.setPerson(person);
		// Save
		user = userRepository.save(user);

		if (user != null) {
			return new UserDto(user);
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean passwordMatch(UserDto dto) {

		if (dto == null || !CommonUtils.isPositive(dto.getId(), true)) {
			return false;
		}

		User user = userRepository.getOne(dto.getId());

		if (user != null) {
			return SecurityUtils.passwordsMatch(user.getPassword(), dto.getPassword());
		} else {
			return false;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto changePassword(UserDto dto) {

		if (dto == null || !CommonUtils.isPositive(dto.getId(), true) || CommonUtils.isEmpty(dto.getPassword())) {
			return null;
		}

		User user = userRepository.getOne(dto.getId());

		if (user == null) {
			return null;
		}

		user.setPassword(SecurityUtils.getHashPassword(dto.getPassword()));
		user = userRepository.save(user);

		if (user == null) {
			return null;
		} else {
			return new UserDto(user);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean emailAlreadyUsed(UserDto dto) {

		if (dto == null || CommonUtils.isEmpty(dto.getEmail())) {
			return false;
		}

		User user = userRepository.findByEmail(dto.getEmail());

		return (user != null);
	}

	@Override
	@Transactional
	public User updateUserLastLogin(Long userId) {
		User user = userRepository.getOne(userId);
		user.setLastLoginTime(new Date());
		return userRepository.save(user);
	}

	@Override
	public User findEntityByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public void recordLoginFailure(Authentication authentication) {
		User user = userRepository.findByUsername(authentication.getPrincipal().toString());
		LocalDateTime now = LocalDateTime.now();
		if (user != null) {
			Long totalLoginFailures = user.getTotalLoginFailures();
			if (totalLoginFailures == null) {
				totalLoginFailures = 0L;
			}
			totalLoginFailures++;
			user.setTotalLoginFailures(totalLoginFailures);
			user.setLastLoginTime(now.toDate());
			userRepository.save(user);
		}

	}

	@Override
	public Page<UserDto> findByPageUsername(String username, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
