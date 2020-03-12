package fi.rbmk.ticketguru.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.saleEvent.SaleEvent;
import fi.rbmk.ticketguru.userGroup.UserGroup;

@Entity
@Table(name = "Users")
public class User extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_ID", nullable = false, updatable = false)
	private Long user_ID;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotEmpty(message = "Password is required")
	@Length(max = 100)
	private String password;

	@NotEmpty(message = "Username is required")
	@Length(max = 50)
	private String name;

	private boolean active = false;

	@ManyToOne
	@NotNull(message = "User Group is required")
	@JoinColumn(name = "userGroup_ID")
	private UserGroup userGroup;

	@OneToMany(mappedBy = "user")
	private List<SaleEvent> saleEvents;

	public User() {
	}

	public User(User user) {
	}

	public User(String name, String password, UserGroup userGroup, boolean active) {
		super();
		this.name = name;
		this.password = password;
		this.userGroup = userGroup;
		this.active = active;
	}

	// Getters
	public Long getUser_ID() {
		return user_ID;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public boolean getActive() {
		return active;
	}

	public List<SaleEvent> getSaleEvents() {
		return saleEvents;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}