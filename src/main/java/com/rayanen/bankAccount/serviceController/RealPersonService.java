package com.rayanen.bankAccount.serviceController;


import com.rayanen.bankAccount.model.entity.RealPerson;

import java.util.List;

public interface RealPersonService {
      void saveReal(RealPerson realPerson) throws Exception;

     void updateReal(RealPerson realPerson) throws Exception;

     RealPerson findByNationalCode(String nationalCode) throws Exception;

    List<RealPerson> findRealAll(RealPerson realPerson)throws Exception;



}
