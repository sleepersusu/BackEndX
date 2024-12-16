package com.example.bistro.members;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
	@Autowired
	private PasswordEncoder pwdEncoder;//引入加密並綁定
	
	@Autowired
	private MembersRepository memberRepo;
	
	public Members insertMember(Members memberBean) {
		return memberRepo.save(memberBean);
	}
	
    //findById
    public Members findMembersById(Integer id) {
        Optional<Members> op = memberRepo.findById(id);
        return op.isPresent() ? op.get() : null;
    }

	//根據姓名或電話查詢會員
	public Members findMembers(String name, String phone) {
	    // 如果有姓名，優先用姓名查詢
		// 如果姓名為空但有電話，則用電話查詢
		// 如果兩者都沒有提供，返回 null
	    if (name != null && !name.isEmpty()) {
	        return memberRepo.findByMemberName(name).orElse(null);
	    }else if (phone != null && !phone.isEmpty()) {
	        return memberRepo.findByMemberPhone(phone).orElse(null);
	    }
	    return null;
	}
	public List<Members> findAllMembers() {
		return memberRepo.findAll();
	}
	
	public Optional<Members> checkLogin(String loginAccount,String loginPassword) {
		 Optional<Members> dbMember = memberRepo.findByMemberAccount(loginAccount);
		if (dbMember.isPresent()) {
			String encodedPwd = dbMember.get().getMemberPassword();
			boolean result = pwdEncoder.matches(loginPassword, encodedPwd);
			
			if (true) {//result
				return dbMember;
			}
		}
		return Optional.empty();
	}
	
	public String updateMember(Members memberBean) {
		memberRepo.save(memberBean);
		return "更新完成";
	}
}
