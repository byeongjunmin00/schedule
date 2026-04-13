package org.example.schedule.service;


import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
