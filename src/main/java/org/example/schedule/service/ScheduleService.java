package org.example.schedule.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service // 서비스 역할 이라는 뜻
@RequiredArgsConstructor // final 필드를 자동으로 주입해주는 것
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // 1. DTO에서 받은 데이터로 Entity 생성
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getAuthor(),
                requestDto.getPassword()
        );

        // 2. DB에 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // 3. 저장된 Entity를 ResponseDto로 변환해 반환 (비밀번호 제외)
        return new ScheduleResponseDto(saveSchedule);
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> getSchedules(String author) {
        List<Schedule> schedules;

        if (author != null) {
            // 작성자명 있으면 그 작성의 일정만 조회
            schedules = scheduleRepository.findByAuthorOrderByModifiedAtDesc(author);
        }else {
            // 작성자명이 없으면 전체 조회
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        }

        // Entity 리스트를 ResponseDto 리스트로 변환
        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    // 선택 일정 조회
    public ScheduleResponseDto getSchedule(Long id) {
        // ID로 일정 찾기, 없으면 에러
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        return new ScheduleResponseDto(schedule);

    }

    // 일정 수정
    @Transactional // DB 변경사항을 자동으로 저장함
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {

        // 1. 일정 찾기
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        // 2. 비밀번호 확인
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 제목, 작성자명 수정
        schedule.update(requestDto.getTitle(), requestDto.getAuthor());

        // 4. 수정된 결과 반환
        return new ScheduleResponseDto(schedule);
    }
}
