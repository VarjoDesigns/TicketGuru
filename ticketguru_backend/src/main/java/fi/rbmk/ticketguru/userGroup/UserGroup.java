package fi.rbmk.ticketguru.userGroup;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.user.User;

@Entity
@Table(name = "user_groups")
public class UserGroup extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_group_id")
	private Long userGroup_id;

	@NotEmpty(message = "User group name is required")
	@Length(max = 100)
	private String name;

	@CreationTimestamp
	@Column(name = "created")
	private LocalDateTime created;

	@Column(name = "invalid")
	private LocalDateTime invalid;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userGroup")
	private List<User> users;

	public UserGroup() {
	}

	public UserGroup(UserGroup userGroup) {
	}

	public UserGroup(String name) {
		this.name = name;
	}

	// Getters
	public Long getUserGroup_id() { return userGroup_id; }
	public String getName() { return name; }
	public LocalDateTime getCreated() { return created; }
	public LocalDateTime getInvalid() { return invalid; }
	public List<User> getUsers() { return users; }

	// Setters
	public void setName(String name) { this.name = name; }
	public void setInvalid() { this.invalid = LocalDateTime.now(); }
}