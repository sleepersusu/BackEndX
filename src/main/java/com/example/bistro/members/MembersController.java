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

import com.example.bistro.config.ImageService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MembersController {
    @Autowired
    private MemberService memberService;
    
	@Autowired
	private ImageService imageService;

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
    	String type = "member";
    	Members dbMember = memberService.findMembersById(memberBean.getId());
        memberBean.setMemberShip(dbMember.getMemberShip());
        memberBean.setMemberStatus(dbMember.getMemberStatus());
        memberBean.setCreatedAt(dbMember.getCreatedAt());
            try {
            	if (file != null && !file.isEmpty()) {
                    byte[] fileBytes = file.getBytes(); // 轉成 byte[]
                    imageService.imageUpload(type, memberBean.getId(), fileBytes);
                    memberBean.setMemberImg(fileBytes);
            	}else {
            		memberBean.setMemberImg(dbMember.getMemberImg());
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result = memberService.updateMember(memberBean);
            System.out.println(result);
        return "redirect:/Bistro/Member/findAllMembers";
    }
    
    @Transactional
    @PostMapping("/Bistro/Member/postMember")
    public String createNewMember(@ModelAttribute Members memberBean,@RequestParam("memberPhoto") MultipartFile file) {
    	String memberShip = "會員";
    	String memberStatus = "啟用";
    	String name = memberBean.getMemberName();
    	String phone = memberBean.getMemberPhone();
    	Members dbMember = memberService.findMembers(name, phone);
    	if(dbMember==null) {
    		memberBean.setMemberShip(memberShip);
    		memberBean.setMemberStatus(memberStatus);
    		memberBean.setMemberPoint(0);
        	try {
        		if (file != null && !file.isEmpty()) {
        			byte[] fileBytes = file.getBytes(); // 轉成 byte[]
        			memberBean.setMemberImg(fileBytes); 
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	memberService.insertMember(memberBean);
    		return "redirect:/Bistro/Member/findAllMembers";
    	}else {
    		System.out.println("新增失敗，資料已存在");
    	}
    	return "redirect:/Bistro/Member/findAllMembers";
    }
    
}
