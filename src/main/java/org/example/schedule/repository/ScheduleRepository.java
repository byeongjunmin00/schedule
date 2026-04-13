package org.example.schedule.repository;

import org.example.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

    // JpaRepository를 상속받으 기본 CRUD 기능을 자동으로 해줌
    // <Schedule, Long> = Schedule 엔티티를 다루고, ID 타입은 Long이라는 뜻
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
