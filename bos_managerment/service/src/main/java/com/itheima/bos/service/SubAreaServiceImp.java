package com.itheima.bos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.SubAreaRepositories;
import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubAreaServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 2, 2017 3:57:34 PM <br/>       
 */
@Service
@Transactional
public class SubAreaServiceImp  implements SubAreaService {
    @Autowired
    private SubAreaRepositories dao;
    @Override
    public void save(SubArea model) {
        dao.save(model);
    }
    @Override
    public Page<SubArea> findByPage(Pageable pageable) {
          
        // TODO Auto-generated method stub  
        return dao.findAll(pageable);
    }
    @Override
    public List<SubArea> findBindFixedArea(Long id) {
          
        // TODO Auto-generated method stub  
        return dao.findBindFixedArea(id);
    }
    @Override
    public List<SubArea> findNoBindFixedArea() {
          
        // TODO Auto-generated method stub  
        return dao.findNoBindFixedArea();
    }
    @Override
    public void BindFixedArea(Long id, List<Long> subAreaId) {
          dao.updateNull(id);
          if (subAreaId!=null&&subAreaId.size()>0) {
              for (Long subId : subAreaId) {
                dao.bindFixedArea(id,subId);
            }
        }
        
    }

}
  
