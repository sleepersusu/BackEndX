package com.example.bistro.members;

import com.example.bistro.campaign.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembersService {
    @Autowired
    MembersRepositoryDao membersRepositoryDao;

    //findById
        public Members findMembersById(Integer id) {
            Optional<Members> op = membersRepositoryDao.findById(id);
            return op.isPresent() ? op.get() : null;
        }

    //根據姓名或電話查詢會員
    public Members findMembers(String name, String phone) {
        // 如果有姓名，優先用姓名查詢
        if (name != null && !name.isEmpty()) {
            return membersRepositoryDao.findByMemberName(name).orElse(null);
        }
        // 如果姓名為空但有電話，則用電話查詢
        if (phone != null && !phone.isEmpty()) {
            return membersRepositoryDao.findByMemberPhone(phone).orElse(null);
        }
        // 如果兩者都沒有提供，返回 null
        return null;
    }

}
