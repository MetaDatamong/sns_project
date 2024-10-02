package com.sns_project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long fileId;

    private Long postingId;

    private String originalFileName;
    private String address;
    private String fileName;

    private FileType fileType;

    public enum FileType {
        JPG(".jpg"),
        PNG(".png");

        private final String extension;
        FileType(String e) {  // Enum 생성자
            this.extension = e;
        }
        public String getValue() { // Enum 상수 값을 불러오기 위한 메소드
            return extension;
        }
    }
}