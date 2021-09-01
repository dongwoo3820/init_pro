package com.resume.init.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account implements UserDetails{

	private String id;
	private String password;
	private int failCnt;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	
	private Collection <? extends GrantedAuthority> authorities;

    
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.authorities;
		}

        // UserDetails의 필수 메서드들
		@Override
		public String getPassword() {
			return this.password;
		}

		@Override
		public String getUsername() {
			return this.id;
		}

		@Override
		public boolean isAccountNonExpired() {
			return this.isAccountNonExpired;
		}

		@Override
		public boolean isAccountNonLocked() {
			return this.isAccountNonLocked;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return this.isCredentialsNonExpired;
		}

		@Override
		public boolean isEnabled() {
			return this.isEnabled;
		}
}