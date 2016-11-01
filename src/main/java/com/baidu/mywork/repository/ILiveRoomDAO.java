package com.baidu.mywork.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baidu.mywork.entity.LiveRoomDB;

@Repository
public interface ILiveRoomDAO extends PagingAndSortingRepository<LiveRoomDB, Long> {

    @Query("FROM LiveRoomDB t WHERE t.name LIKE :name ORDER BY t.name")
    Page<LiveRoomDB> search(@Param("name") String name, Pageable pageable);
    
    @Query("FROM LiveRoomDB t WHERE t.startTime > :startTime and t.endTime < :endTime ORDER BY t.id")
    List<LiveRoomDB> searchByStartEndTime(@Param("startTime") Date startTime, 
    		@Param("endTime") Date endTime);

}
