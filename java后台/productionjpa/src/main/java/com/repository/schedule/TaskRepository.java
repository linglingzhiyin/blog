package com.repository.schedule;

import com.domain.schedule.Task;
import com.domain.schedule.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Task p where p.taskId in ?1 ")
    public void deleteByIds(Long[] ids);

}
