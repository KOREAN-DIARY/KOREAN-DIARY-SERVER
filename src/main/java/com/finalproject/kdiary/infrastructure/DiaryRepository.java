package com.finalproject.kdiary.infrastructure;

import com.finalproject.kdiary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
