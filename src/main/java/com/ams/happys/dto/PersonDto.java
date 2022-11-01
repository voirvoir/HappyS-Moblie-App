package com.ams.happys.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.ams.happys.domain.Person;
import com.ams.happys.domain.PersonAddress;

public class PersonDto extends AuditableEntityDto {

	protected UUID id;

	protected String firstName;

	protected String lastName;

	protected String displayName;
	protected String shortName;// Ví dụ : Ô. Ổn, ô Đăng, Ô Kim, .... dùng trong lịch tuần
	protected Date birthDate;

	protected String birthPlace;

	protected String gender;

	protected Date startDate;

	protected Date endDate;

	protected String phoneNumber;

	protected String idNumber;// Số chứng mình thư

	protected String idNumberIssueBy;

	protected Date idNumberIssueDate;

	protected String email;

	protected byte[] photo;

	private Boolean photoCropped;
	private String imagePath;
	protected Set<PersonAddressDto> addresses;

	protected Long userId;
	protected Date communistYouthUnionJoinDate;// Ngày vào đoàn

	protected Date communistPartyJoinDate;// Ngày vào đảng

	protected String carrer;// nghề nghiệp/chức vụ

	private Integer maritalStatus;

	public Person toEntity() {
		Person person = new Person();
		if (id != null)
			person.setId(id);
		if (id != null) {
			person.setId(id);
		}
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setDisplayName(displayName);
		person.setBirthDate(birthDate);
		person.setBirthPlace(birthPlace);
		person.setGender(gender);
		person.setStartDate(startDate);
		person.setEndDate(endDate);
		person.setPhoneNumber(phoneNumber);
		person.setIdNumber(idNumber);
		person.setIdNumberIssueBy(idNumberIssueBy);
		person.setIdNumberIssueDate(idNumberIssueDate);
		person.setEmail(email);
		person.setCarrer(carrer);
		person.setShortName(shortName);

		if (addresses != null) {
			Set<PersonAddress> addrs = new HashSet<PersonAddress>();
			for (PersonAddressDto dto : addresses) {
				addrs.add(dto.toEntity());
			}

			person.setAddress(addrs);
		}

		person.setPhoto(photo);

		return person;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdNumberIssueBy() {
		return idNumberIssueBy;
	}

	public void setIdNumberIssueBy(String idNumberIssueBy) {
		this.idNumberIssueBy = idNumberIssueBy;
	}

	public Date getIdNumberIssueDate() {
		return idNumberIssueDate;
	}

	public void setIdNumberIssueDate(Date idNumberIssueDate) {
		this.idNumberIssueDate = idNumberIssueDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<PersonAddressDto> getAddress() {
		return addresses;
	}

	public void setAddress(Set<PersonAddressDto> address) {
		this.addresses = address;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Boolean getPhotoCropped() {
		return photoCropped;
	}

	public void setPhotoCropped(Boolean photoCropped) {
		this.photoCropped = photoCropped;
	}

	public Set<PersonAddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<PersonAddressDto> addresses) {
		this.addresses = addresses;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCommunistYouthUnionJoinDate() {
		return communistYouthUnionJoinDate;
	}

	public void setCommunistYouthUnionJoinDate(Date communistYouthUnionJoinDate) {
		this.communistYouthUnionJoinDate = communistYouthUnionJoinDate;
	}

	public Date getCommunistPartyJoinDate() {
		return communistPartyJoinDate;
	}

	public void setCommunistPartyJoinDate(Date communistPartyJoinDate) {
		this.communistPartyJoinDate = communistPartyJoinDate;
	}

	public String getCarrer() {
		return carrer;
	}

	public void setCarrer(String carrer) {
		this.carrer = carrer;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public PersonDto() {
		this.addresses = new HashSet<PersonAddressDto>();
	}

	public PersonDto(Person p) {
		if (p != null) {

			this.birthDate = p.getBirthDate();
			this.birthPlace = p.getBirthPlace();
			this.displayName = p.getDisplayName();
			this.email = p.getEmail();
			this.endDate = p.getEndDate();
			this.firstName = p.getFirstName();
			this.lastName = p.getLastName();
			this.gender = p.getGender();
			this.id = p.getId();
			this.idNumber = p.getIdNumber();
			this.idNumberIssueBy = p.getIdNumberIssueBy();
			this.idNumberIssueDate = p.getIdNumberIssueDate();
			this.photoCropped = p.getPhotoCropped();
			this.shortName = p.getShortName();
			this.imagePath = p.getImagePath();
			this.phoneNumber = p.getPhoneNumber();
			if (p.getUser() != null) {
				this.userId = p.getUser().getId();
			}
			if (p.getAddress() != null) {
				Set<PersonAddressDto> address = new HashSet<PersonAddressDto>();
				for (PersonAddress tt : p.getAddress()) {
					PersonAddressDto ttDto = new PersonAddressDto();
					ttDto.setId(tt.getId());
					ttDto.setAddress(tt.getAddress());
					ttDto.setCity(tt.getCity());
					ttDto.setCountry(tt.getCountry());
					ttDto.setPersonId(p.getId());
					ttDto.setPostalCode(tt.getPostalCode());
					ttDto.setProvince(tt.getProvince());
					ttDto.setType(tt.getType());
					address.add(ttDto);
				}
				this.addresses = address;
			}
			if (p.getCommunistPartyJoinDate() != null) {
				this.communistPartyJoinDate = p.getCommunistPartyJoinDate();
			}
			if (p.getCommunistYouthUnionJoinDate() != null) {
				this.communistYouthUnionJoinDate = p.getCommunistYouthUnionJoinDate();
			}
			this.carrer = p.getCarrer();

			setCreateDate(p.getCreateDate());
			setCreatedBy(p.getCreatedBy());
			setModifyDate(p.getModifyDate());
			setModifiedBy(p.getModifiedBy());
		}
	}
}
