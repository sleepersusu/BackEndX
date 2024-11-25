package com.example.bistro.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private PasswordEncoder pwdEncoder;//引入加密並綁定
	
	@Autowired
	private MemberRepository memberRepo;
}
