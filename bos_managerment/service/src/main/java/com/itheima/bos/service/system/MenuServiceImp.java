package com.itheima.bos.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.MenuReposotories;
import com.itheima.bos.domain.system.Menu;

/**  
 * ClassName:MenuServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 4:46:21 PM <br/>       
 */
@Service
@Transactional
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuReposotories menuDao;

    @Override
    public List<Menu> findAll() {
        return menuDao.findByParentMenuIsNull();
    }

    @Override
    public void save(Menu model) {
        //没有父目录的情况
        if (model.getParentMenu()!=null&&model.getParentMenu().getId()==null) {
            model.setParentMenu(null);
        }
         menuDao.save(model);
    }

    @Override
    public Page<Menu> findByPage(Pageable pageable) {
          
        // TODO Auto-generated method stub  
        return menuDao.findAll(pageable);
    }
    
}
  
