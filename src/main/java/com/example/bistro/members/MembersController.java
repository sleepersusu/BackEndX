package com.example.bistro.members;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MembersController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/Bistro/Member/getMembersInfo")
    public ResponseEntity<Map<String, Object>> getMembersInfo(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone) {
        // 確保至少提供一個參數
        if ((name == null || name.isEmpty()) && (phone == null || phone.isEmpty())) {
            return ResponseEntity.badRequest().body(Map.of("error", "請提供姓名或電話號碼"));
        }

        // 使用 Service 查找會員
        Members members = memberService.findMembers(name, phone);

        // 返回會員信息或 null
        if (members != null) {
            return ResponseEntity.ok(Map.of(
                    "memberId", members.getId(),
                    "name", members.getMemberName(),
                    "phone", members.getMemberPhone()
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "message", "非會員"
            ));
        }
    }
    
    @GetMapping("/Bistro/Member/findAllMembers")
    public String findAll(Model model) {
    	List<Members> allMembers = memberService.findAllMembers();
    	model.addAttribute("allMembers",allMembers);
        return "member/membersView";
    }
    
    @Transactional
    @PostMapping("/Bistro/Member/UpdateMember")
    public String updateMember(@ModelAttribute Members memberBean,@RequestParam("memberPhoto") MultipartFile file) {
    	Members dbMember = memberService.findMembersById(memberBean.getId());
    	System.out.println("MemberStatus:"+memberBean.getId());
        System.out.println("Account:"+memberBean.getMemberAccount());
        System.out.println("Password:"+memberBean.getMemberPassword());
        System.out.println("Name:"+memberBean.getMemberName());
        System.out.println("Email:"+memberBean.getMemberEmail());
        System.out.println("Phone:"+memberBean.getMemberPhone());
        memberBean.setMemberShip(dbMember.getMemberShip());
        System.out.println("MemberShip:"+memberBean.getMemberShip());
        System.out.println("MemberFavor:"+memberBean.getMemberFavor());
        System.out.println("Sex:"+memberBean.getMemberSex());
        System.out.println("Address:"+memberBean.getMemberAddress());
        memberBean.setMemberStatus(dbMember.getMemberStatus());
        System.out.println("Status:"+memberBean.getMemberStatus());
            try {
            	if (file != null && !file.isEmpty()) {
                    byte[] fileBytes = file.getBytes(); // 轉成 byte[]
                    memberBean.setMemberImg(fileBytes); 
            	}else {
            		memberBean.setMemberImg(memberBean.getMemberImg()); 
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println("MemberImg:"+memberBean.getMemberImg());
        return "member/membersView";
    }
    
    
}
