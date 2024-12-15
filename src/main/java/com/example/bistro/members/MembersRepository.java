package com.example.bistro.members;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Integer> {

    // 根據會員姓名查詢
    Optional<Members> findByMemberName(String memberName);

    // 根據會員電話查詢
    Optional<Members> findByMemberPhone(String memberPhone);

    //根據資料查詢會員姓名和電話查詢
    Optional<Members> findByMemberNameAndMemberPhone(String memberName, String memberPhone);

    //根據會員帳號查詢會員資料
    Optional<Members> findByMemberAccount(String account);

}
