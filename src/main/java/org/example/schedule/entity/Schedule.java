package org.example.schedule.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter // getter 메서드를 자동으로 만들어줌
@Entity // 이 클래스가 DB테이블이라는 뜻
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 만들어줌
@EntityListeners(AuditingEntityListener.class) // 작성일,수정일 자동 관리
public class Schedule {

    @Id // 고유 식별자(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 증가
    private Long id;

    @Column(nullable = false) // 필수 입력값! 이 필드 NULL 이면 안돼! 라는 뜻
    private String title; // 일정 제목

    @Column(nullable = false)
    private String content; // 일정 내용

    @Column(nullable = false)
    private String author; // 작성자명

    @Column(nullable = false)
    private String password; // 비밀번호

    @CreatedDate // 생성 시 자동으로 현재 시간 저장 (작성일은 변경할 수 없어 라고 한 부분) JPA Auditing
    @Column(updatable = false) // 작성일은 수정 불가
    private LocalDateTime createdAt; // 작성일

    @LastModifiedDate // 수정 시 자동으로 현재 시간 저장 (수정한 시점으로 변경되어야해 라고 한 부분) JPA Auditing
    private LocalDateTime modifiedAt; // 수정일

    // 일정 생성할 때 사용하는 생성자
    public Schedule(String title, String content, String author, String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    // 일정 수정 (제목, 작성자명만 수정 가능)
    public void update(String title, String author) {
        this.title = title;
        this.author = author;

    }
}
