package com.itheima.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.SubAreaService;
import com.itheima.bos.utils.FileDownloadUtils;
import com.itheima.bos.web.common.CommonAction;

/**  
 * ClassName:SubAreaAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 2, 2017 3:56:26 PM <br/>       
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Scope("prototype")
public class SubAreaAction  extends CommonAction<SubArea>{
    
    private SubArea model=getModel();
    private List<Long> subAreaId;
    

    public void setSubAreaId(List<Long> subAreaId) {
        this.subAreaId = subAreaId;
    }

    @Autowired
    private SubAreaService service;
    @Action(value="subAreaAction_save",results= {@Result(name="success",location="/pages/base/sub_area.html",type="redirect")})
    public String save() {
        service.save(model);
        
        return SUCCESS;
    }
    @Action("subAreaAction_findByPage")
    public String findBypage() {
      Pageable pageable = new PageRequest(page-1,rows);
      Page<SubArea> page=  service.findByPage(pageable);
     
      pageToJSON(page, new String[] {"fixedArea","subareas"});

        return NONE;
    }
   
    @Action("subAreaAction_findBindFixedArea")
    public String findBindFixedArea() {
        
      
     List<SubArea> list=service.findBindFixedArea(model.getId());
 
     listToJSON(list, new String[] {"area","couriers","subareas"});
        return NONE;
    }

    @Action("subAreaAction_findNoBindFixedArea")
    public String findBindNoFixedArea() {
    List<SubArea> list=service.findNoBindFixedArea();
    listToJSON(list, new String[] {"area"});
        return NONE;
    }
    
    @Action(value="subAreaAction_bindFixedArea",results= {@Result(name="success",location="/pages/base/fixed_area.html",type="redirect")})
    public String bindFixedArea() {
       service.BindFixedArea(model.getId(),subAreaId);
        return SUCCESS;
    }
    
    @Action("subAreaAction_exportExcel")
    public String exportExcel() throws IOException {
      List<SubArea> list= service.findAll(); 
      //创建excel文件
     HSSFWorkbook workbook = new  HSSFWorkbook();
     //创建工作簿
        HSSFSheet sheet = workbook.createSheet("分区统计");
        //创建表头
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("分区编号");
        titleRow.createCell(1).setCellValue("分区开始编号");
        titleRow.createCell(2).setCellValue("分区结束编号");
        titleRow.createCell(3).setCellValue("分区关键字");
        titleRow.createCell(4).setCellValue("辅助关键字");
        titleRow.createCell(5).setCellValue("区域信息");
        //遍历 添加数据
        for (SubArea subArea : list) {
            HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
            row.createCell(0).setCellValue(subArea.getId());
            row.createCell(1).setCellValue(subArea.getStartNum());
            row.createCell(2).setCellValue(subArea.getEndNum());
            row.createCell(3).setCellValue(subArea.getKeyWords());
            row.createCell(4).setCellValue(subArea.getAssistKeyWords());
            row.createCell(5).setCellValue(subArea.getArea().getName());
        }
        
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName="分区统计信息.xls";
        String agent = ServletActionContext.getRequest().getHeader("user-Agent");
        fileName = FileDownloadUtils.encodeDownloadFilename(fileName, agent);
        
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        // 指定文件的MIME类型
        response.setContentType(ServletActionContext.getServletContext().getMimeType(fileName));
        
        // 写出文件
        workbook.write(outputStream);
        // 释放资源
        workbook.close();
        return NONE;
    }
    
}
  
