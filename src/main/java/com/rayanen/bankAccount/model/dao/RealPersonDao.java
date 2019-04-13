package com.rayanen.bankAccount.model.dao;



import com.rayanen.bankAccount.dto.RealPersonDto;
import com.rayanen.bankAccount.model.entity.RealPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RealPersonDao extends JpaRepository<RealPerson, Integer> {


    @Query("select c from RealPerson c where c.name Like %:name% and  c.familyName =:familyName")
    List<RealPersonDto> findByNameAndFamilyName(@Param("name") String name, @Param("familyName") String familyName);


    RealPersonDto findByNationalCode( String nationalCode);

    boolean existsByNationalCode(String nationalCode);


}
