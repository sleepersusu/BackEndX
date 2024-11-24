package com.example.bistro.members;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Integer> {
    // 根據會員姓名查詢
    Optional<Members> findByMemberName(String memberName);

    // 根據會員電話查詢
    Optional<Members> findByMemberPhone(String memberPhone);
}
