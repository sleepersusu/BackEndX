package com.example.bistro.members;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembersRepositoryDao extends JpaRepository<Members, Integer> {
    // 根據會員姓名查詢
    Optional<Members> findByMemberName(String memberName);

    // 根據會員電話查詢
    Optional<Members> findByMemberPhone(String memberPhone);
}
