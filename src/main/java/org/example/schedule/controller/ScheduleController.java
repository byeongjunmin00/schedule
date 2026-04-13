package org.example.schedule.controller;


import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // API 요청을 받는 컨트롤러 라는 뜻
@RequestMapping("/schedules") // schedules 경로로 들어오 요청처리
@RequiredArgsConstructor // final 필드를 자동으로 주입해주는 것
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 API
    @PostMapping // POST /schedule 요청을 처리
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // Service에게 일정 생성 요청하고 결과를 받는다
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
            return ResponseEntity.ok(responseDto);
        }
    }
