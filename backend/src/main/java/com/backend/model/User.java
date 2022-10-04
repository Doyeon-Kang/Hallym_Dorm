package com.backend.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="user")

// CREATE TABLE IF NOT EXISTS `dormitory`.`user` (
//   `id` INT NOT NULL AUTO_INCREMENT,
//   `no` VARCHAR(200) NOT NULL COMMENT '아이디',
//   `name` VARCHAR(50) NULL COMMENT '이름',
//   `pw` VARCHAR(50) NULL COMMENT '비밀번호',
//   `email` VARCHAR(100) NULL COMMENT '이메일',
//   `join_yn` VARCHAR(1) NULL COMMENT '입사여부',
//   `div` VARCHAR(20) NULL COMMENT '회원구분',
//   `user_member_id` INT,
//   `user_member_no` VARCHAR(200),
//   `user_member_user_member_parent_id` INT,
//   `user_member_user_member_parent_no` VARCHAR(200),
//   `apply_join_id` INT,
//   `apply_join_apply_date` DATE,
//   PRIMARY KEY (`id`, `no`),

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username")
	@NotBlank
	@Size(max=200)
    private String username; 

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "name")
	@Size(max=50)
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Column(name = "password")
	@Size(max=120)
    private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    @Column(name = "email")
	@Size(max=100) 
    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Column(name = "join_yn")
    private char join_yn;

	public char getJoin_yn() {
		return join_yn;
	}

	public void setJoin_yn(char join_yn) {
		this.join_yn = join_yn;
	}

    // @Column(name = "div")
    // private String div;

	// public String getDiv() {
	// 	return div;
	// }

	// public void setDiv(String div) {
	// 	this.div = div;
	// }

    @Column(name = "user_member_id")
    private Long user_member_id;

	public Long getUser_member_id() {
		return user_member_id;
	}

	public void setUser_member_id(Long user_member_id) {
		this.user_member_id = user_member_id;
	}

    @Column(name = "user_member_no")
    private String user_member_no;

	public String getUser_member_no() {
		return user_member_no;
	}

	public void setUser_member_no(String user_member_no) {
		this.user_member_no = user_member_no;
	}

    @Column(name = "user_member_user_member_parent_id")
    private Long user_member_user_member_parent_id;

	public Long getUser_member_user_member_parent_id() {
		return user_member_user_member_parent_id;
	}

	public void setUser_member_user_member_parent_id(Long user_member_user_member_parent_id) {
		this.user_member_user_member_parent_id = user_member_user_member_parent_id;
	}

    @Column(name = "user_member_user_member_parent_no")
    private String user_member_user_member_parent_no;

	public String getUser_member_user_member_parent_no() {
		return user_member_user_member_parent_no;
	}

	public void setUser_member_user_member_parent_no(String user_member_user_member_parent_no) {
		this.user_member_user_member_parent_no = user_member_user_member_parent_no;
	}

    @Column(name = "apply_join_id")
    private int apply_join_id;

	public int getApply_join_id() {
		return apply_join_id;
	}

	public void setApply_join_id(int apply_join_id) {
		this.apply_join_id = apply_join_id;
	}

    @Column(name = "apply_join_apply_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date apply_join_apply_date;

	public Date getApply_join_apply_date() {
		return apply_join_apply_date;
	}

	public void setApply_join_apply_date(Date apply_join_apply_date) {
		this.apply_join_apply_date = apply_join_apply_date;
	}

	public User() {

	}
    public User(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
