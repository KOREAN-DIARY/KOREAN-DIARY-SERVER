package com.finalproject.kdiary.infrastructure;

import com.finalproject.kdiary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserId(@Param("userId") String userId);

    List<Diary> findByDate(Date date);
}
