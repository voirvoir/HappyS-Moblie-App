package com.ams.happys.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tbl_person")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.INTEGER)
@XmlRootElement
public class Person extends BaseObject
{
	@Transient
	private static final long serialVersionUID = 1216825583672377485L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// private Long id;

	@Column(name = "first_name", nullable = true)
	protected String firstName;

	@Column(name = "last_name", nullable = true)
	protected String lastName;

	@Column(name = "display_name", nullable = true)
	protected String displayName;
	@Column(name = "short_name", nullable = true)
	protected String shortName;// Ví dụ : Ô. Ổn, ô Đăng, Ô Kim, .... dùng trong lịch tuần

	@Column(name = "birth_date", nullable = true)
	protected Date birthDate;

	@Column(name = "birth_place", nullable = true)
	protected String birthPlace;

	@Column(name = "gender", nullable = true)
	protected String gender;

	@Column(name = "start_date", nullable = true)
	protected Date startDate;

	@Column(name = "end_date", nullable = true)
	protected Date endDate;

	@Column(name = "phone_number", nullable = true)
	protected String phoneNumber;

	@Column(name = "id_number", nullable = true)
	protected String idNumber;// Số chứng mình thư

	@Column(name = "id_number_issue_by", nullable = true)
	protected String idNumberIssueBy;

	@Column(name = "id_number_issue_date", nullable = true)
	protected Date idNumberIssueDate;

	@Column(name = "email", nullable = true)
	protected String Email;

	@Column(name = "communist_youth_union_join_date", nullable = true)
	protected Date communistYouthUnionJoinDate;// Ngày vào đoàn

	@Column(name = "communist_party_join_date", nullable = true)
	protected Date communistPartyJoinDate;// Ngày vào đảng
	
	@Column(name = "communist_party_member", nullable = true)
	private Boolean isCommunistPartyMember;//Là đảng viên? 
	@OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<PersonAddress> address;// Sử dụng trong địa chỉ thường trú và nơi ở hiện tại

	@Column(name = "carrer", nullable = true)
	protected String carrer;// nghề nghiệp/chức vụ

	// @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
	@ManyToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
	// @OneToOne
	@JoinColumn(name = "user_id", unique = false)
	protected User user;

	@Basic(fetch = FetchType.LAZY)
	// @Column(name = "photo", nullable = true, columnDefinition = "LONGBLOB NULL")
	// @Column(name = "photo", nullable = true,length=5242880)//Kích thước tối đa
	// 5M, có thể để kích thước lớn hơn - SQL Server
	@Transient
	protected byte[] photo;

	@Column(name = "photo_cropped", nullable = true)
	private Boolean photoCropped;
	
	@Column(name = "marital_status", nullable = true)
	private Integer maritalStatus;// Tình trạng hôn nhân

	@Column(name = "is_dead", nullable = true)
	private Boolean isDead;

	@Column(name = "image_path", nullable = true)
	private String imagePath;
	
	@Column(name="tax_code")
	private String taxCode;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Set<PersonAddress> getAddress() {
		return address;
	}

	public void setAddress(Set<PersonAddress> address) {
		this.address = address;
	}


	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
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
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCarrer() {
		return carrer;
	}

	public void setCarrer(String carrer) {
		this.carrer = carrer;
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

	public Boolean getIsCommunistPartyMember() {
		return isCommunistPartyMember;
	}

	public void setIsCommunistPartyMember(Boolean isCommunistPartyMember) {
		this.isCommunistPartyMember = isCommunistPartyMember;
	}

	public Boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(Boolean isDead) {
		this.isDead = isDead;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public Person() {
		super();
	}

	public Person(UUID id, String firstName, String lastName, String displayName, Date birtDate, String phoneNumber) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDisplayName(displayName);
		this.setBirthDate(birtDate);
	}

	public Person(Person person) {
		super(person);
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.displayName = person.getDisplayName();

		if (person.getAddress() != null) {
			Set<PersonAddress> address = new HashSet<PersonAddress>();
			for (PersonAddress tt : person.getAddress()) {
				PersonAddress ttDto = new PersonAddress();
				ttDto = tt;
				address.add(ttDto);
			}
			this.address = address;
		}
		this.birthDate = person.getBirthDate();
		this.birthPlace = person.getBirthPlace();
		this.Email = person.getEmail();
		this.photo = person.getPhoto();
		this.photoCropped = person.getPhotoCropped();
		this.gender = person.getGender();
		this.idNumber = person.getIdNumber();
		this.idNumberIssueDate = person.getIdNumberIssueDate();
		this.phoneNumber = person.getPhoneNumber();
		this.idNumberIssueBy = person.getIdNumberIssueBy();
		if (person.getUser() != null) {
			this.user = new User(person.getUser(), false);
		}
		this.carrer = person.getCarrer();
		if (person.getMaritalStatus() != null)
			this.maritalStatus = person.getMaritalStatus();
	}
}
