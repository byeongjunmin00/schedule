package org.example.schedule.repository;

import org.example.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository를 상속받으 기본 CRUD 기능을 자동으로 해줌
    // <Schedule, Long> = Schedule 엔티티를 다루고, ID 타입은 Long이라는 뜻
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

        // 작성자명으로 조회 + 수정일 내림차순 정렬
        List<Schedule> findByAuthorOrderByModifiedAtDesc(String author);

        // 전체 조회 + 수정일 내림차순 정렬
        List<Schedule> findAllByOrderByModifiedAtDesc();

        // findByAuthor = 작성자명으로 찾아라
        // findAllBy = 전체로 찾아라
        // OrderByModifiedAtDesc = 수정일 내림차순으로 정렬해라

}
