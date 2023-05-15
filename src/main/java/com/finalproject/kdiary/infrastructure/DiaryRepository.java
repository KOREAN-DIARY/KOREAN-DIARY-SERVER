package com.finalproject.kdiary.infrastructure;

import com.finalproject.kdiary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserId(@Param("userId") String userId);

    Optional<Diary> findByDate(Date date);
}
