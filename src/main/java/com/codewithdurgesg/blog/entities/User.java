package com.codewithdurgesg.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="user")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Column(name="user_name", length=100)
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Post> posts;

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER  )
	@JoinTable(name="role_user",
	joinColumns = @JoinColumn(name="user", referencedColumnName = "id"),
	inverseJoinColumns= @JoinColumn(name="role",referencedColumnName = "id") )
	private Set<Role> role=new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authority=this.role.stream().map((roles)->new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toList());

		return authority;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
