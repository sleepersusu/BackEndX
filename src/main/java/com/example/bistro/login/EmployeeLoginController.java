package com.example.bistro.login;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bistro.employee.Employee;
import com.example.bistro.employee.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeLoginController {
	
	@Autowired
	private EmployeeService employeeService;
	
    @GetMapping("/api/checkTime")
    @ResponseBody  // 用來返回 JSON 格式的數據
    public ResponseEntity<?> getRemainingTime(HttpSession session) {
        if (session == null) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Session is null"));
        }
    	int maxTime = session.getMaxInactiveInterval();
    	
    	Map<String, Integer> response = new HashMap<>();
        response.put("totalTime", maxTime);
        
        return ResponseEntity.ok(response);
    }
	
	
	@PostMapping("/employee/login")
	public String loginPost(String employeeAccount,String employeePassword,Model model
			,HttpSession httpSession) {
		//這邊引入這個Session作保存用戶資訊，會產生SessionID，並記錄在Cookie
		Optional<Employee> checkResult = employeeService.checkLogin(employeeAccount, employeePassword);//撈這筆資料
		
		if (checkResult.isPresent()) {
			long currentTime = System.currentTimeMillis();
			System.out.println("登入成功，建立Session");
			httpSession.setMaxInactiveInterval(3600);//session存活時間sec
			httpSession.setAttribute("lastAccessTime", currentTime);
			httpSession.setAttribute("loginAccount", checkResult.get().getEmployeeAccount());
			httpSession.setAttribute("loginId", checkResult.get().getId());
			//Session紀錄資訊
			return "/HomePage/BackEndIndex";
		}else {
			System.out.println("登入失敗");
			model.addAttribute("errorMsg","登入失敗");
			return "/login/login";
		}
		
	}
	
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // 销毁 Session
            System.out.println("登出銷毀Session");
        }
        return "redirect:/";  // 重定向到登录页面
    }
	
}
