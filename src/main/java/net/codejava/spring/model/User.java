package net.codejava.spring.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	//@OneToMany(targetEntity=phones.class, mappedBy="user", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@ElementCollection(targetClass=phones.class)
	private Set<phones> phones;

	//@Id
	//@GeneratedValue
	//@Column(name = "user_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	//@OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
	public Set<net.codejava.spring.model.phones> getPhones() {
		return phones;
	}

	public void setPhones(Set<net.codejava.spring.model.phones> phones) {
		this.phones = phones;
	}
}
