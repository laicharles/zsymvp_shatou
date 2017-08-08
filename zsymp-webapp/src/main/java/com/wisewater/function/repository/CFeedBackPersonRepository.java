package com.wisewater.function.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.function.pojo.CFeedBackPerson;
import com.wisewater.function.pojo.CNoticeTemp;


public interface CFeedBackPersonRepository extends BaseRepository<CFeedBackPerson, String>{
	
	@Query("select p from CFeedBackPerson p where p.token=:token and p.type =:type and p.isLogicDel=0")
	public List<CFeedBackPerson> findByTokenAndType(@Param("token")String token,@Param("type")int type);
	
	@Query("select p from CFeedBackPerson p where p.token=:token and p.isLogicDel=0")
	public List<CFeedBackPerson> findByToken(@Param("token")String token);
	
	@Query("select p from CFeedBackPerson p where p.token=:token and p.isLogicDel=0")
    public Page<CFeedBackPerson> findAll(Pageable page, @Param("token")String token);
}
