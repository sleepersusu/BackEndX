package com.example.bistro.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
    @GetMapping("/api/remainingTime")
    @ResponseBody  // 用來返回 JSON 格式的數據
    public ResponseEntity<Long> getRemainingTime(HttpSession session) {
    	long currentTime = System.currentTimeMillis();
    	long lastAccessTime=0;
    	long reoprtTime=0;
    	long remainingTime =0;
    	long maxTime =(long) session.getMaxInactiveInterval()*1000;
    	if (session != null && session.getAttribute("lastAccessTime") != null) {
    	    lastAccessTime = (long) session.getAttribute("lastAccessTime");
    	    reoprtTime=currentTime-lastAccessTime;
    	    remainingTime=maxTime - reoprtTime;
    	}
        if (remainingTime < 0) {
            remainingTime = 0;
        }
        return ResponseEntity.ok(remainingTime);
    }
	
	
	@PostMapping("/employee/login")
	public String loginPost(String employeeAccount,String employeePassword,Model model
			,HttpSession httpSession) {
		//這邊引入這個Session作保存用戶資訊，會產生SessionID，並記錄在Cookie
		Optional<Employee> checkResult = employeeService.checkLogin(employeeAccount, employeePassword);//撈這筆資料
		
		if (checkResult.isPresent()) {
			long currentTime = System.currentTimeMillis();
			System.out.println("登入成功，建立Session");
			httpSession.setMaxInactiveInterval(60);//session存活時間sec
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
