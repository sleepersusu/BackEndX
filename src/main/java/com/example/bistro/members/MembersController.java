package com.example.bistro.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/Bistro/Members")
public class MembersController {
    @Autowired
    private MembersService membersService;

    @GetMapping("/Bistro/Members/getMembersInfo")
    public ResponseEntity<Map<String, Object>> getMembersInfo(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone) {

        // 確保至少提供一個參數
        if ((name == null || name.isEmpty()) && (phone == null || phone.isEmpty())) {
            return ResponseEntity.badRequest().body(Map.of("error", "請提供姓名或電話號碼"));
        }

        // 使用 Service 查找會員
        Members members = membersService.findMembers(name, phone);

        // 返回會員信息或 null
        if (members != null) {
            return ResponseEntity.ok(Map.of(
                    "memberId", members.getID(),
                    "name", members.getMemberName(),
                    "phone", members.getMemberPhone()
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "message", "非會員"
            ));
        }
    }
}
