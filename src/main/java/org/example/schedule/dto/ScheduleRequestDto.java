package org.example.schedule.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {

    private String title;
    private String content;
    private String author;
    private String password;
}
