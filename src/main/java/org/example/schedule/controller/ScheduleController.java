package org.example.schedule.controller;


import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 전체 일정 조회 API
    @GetMapping // GET /schedule 요청 처리
    public ResponseEntity<List<ScheduleResponseDto>> getSchedulses(@RequestParam(required = false) String author) {
        // required = false : 작성자명이 없어도 됨 (선택 조건)
        return ResponseEntity.ok(scheduleService.getSchedules(author));
    }


    // 선택 일정 조회 API
    @GetMapping("/{id}") // 단건 조회이기 때문에 어떤 일정을 볼 건지 알려줘야하기 때문에 ID에 URL을 넣는
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        // @PathVariable : URL에 있는 id값을 가져옴
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    // 일정 수정 API
    @PutMapping("/{id}") // PUT /schedules/1 이런 식으로 요청!
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        // URL에서 id를 받고, Body에서 수정할 데이터를 받는다
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto));
    }


}
