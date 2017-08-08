package com.wisewater.cusConfig.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import com.wisewater.base.BaseRepository;
import com.wisewater.cusConfig.pojo.TimeSet;

public interface SetTimeRepository extends BaseRepository<TimeSet, String>{
	
	
    @Query("select u from TimeSet u")
    public List<TimeSet> findTimeSet();
    
    //@Query("update TimeSet u set u.firstDay=:date1 and u.secondDay=:date2 and u.firstHour=:time1 and u.secondHour=:time2 where u.flag=1")
    //public List<TimeSet> update(@Param("date1")String date1,@Param("date2")String date2,@Param("time1")String time1,@Param("time2")String time2);

}
