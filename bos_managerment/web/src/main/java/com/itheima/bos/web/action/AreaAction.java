package com.itheima.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.AreaService;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.common.CommonAction;



/**  
 * ClassName:AreaAction <br/>  
 * Function:  <br/>  
 * Date:     Nov 30, 2017 8:54:07 PM <br/>       
 */
@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class AreaAction extends CommonAction<Area> {
    @Autowired
    private AreaService service;
    private File file;
    private String q;

    public void setQ(String q) {
        this.q = q;
    }
    public void setFile(File file) {
        this.file = file;
    }
    private Area model=getModel();
    

  
    @Action("areaAction_importXLS")
    public String importXLS() throws Exception {
       HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file)); 
       HSSFSheet sheet = workbook.getSheetAt(0);
       List<Area> list=new ArrayList<>();
       for (Row row : sheet) {
           //跳过表头的目录
           if (row.getRowNum()==0) {
            continue;
        }
           //读取一行的内容
           String id = row.getCell(0).getStringCellValue();
           String province = row.getCell(1).getStringCellValue();
           String city = row.getCell(2).getStringCellValue();
           String district = row.getCell(3).getStringCellValue();
           String postcode = row.getCell(4).getStringCellValue();
           //一行的内容封装到area
           province = province.substring(0, province.length() - 1);
           city = city.substring(0, city.length() - 1);
           district = district.substring(0, district.length() - 1);
           // 生成城市编码
           String citycode =
                   PinYin4jUtils.hanziToPinyin(city, "").toUpperCase();
           // 生成简码
           String[] headByString = PinYin4jUtils
                   .getHeadByString(province + city + district);
           String shortcode =
                   PinYin4jUtils.stringArrayToString(headByString);

           Area area = new Area();
           area.setProvince(province);
           area.setCity(city);
           area.setDistrict(district);
           area.setPostcode(postcode);
           area.setCitycode(citycode);
           area.setShortcode(shortcode);
           list.add(area);
            
    }
       service.save(list);
       workbook.close();
        return NONE;
    }
    @Action("areaAction_findByPage")
    public String findByPage() throws IOException {
        Pageable pageable = new PageRequest(page-1, rows);
        Page<Area> page=  service.findByPage(pageable);
        String[] excludes= {"subareas"};
        pageToJSON(page, excludes);
        return NONE;
    }
    
    @Action("areaAction_findAll")
  public String findAll() {
        List<Area> list=new ArrayList<>();
        if (StringUtils.isEmpty(q)) {
            list=  service.findAll();
        }else {
            list=service.finQ(q);
        }
        String[] excludes= {"subareas"};
       listToJSON(list,excludes);
        
        return NONE;
    }
    
/*   @Action("areaAction_exportExcel")
    public String exportExcel() {
       List<Area> all = service.findAll();
       // 在内存中创建Excel文件
       HSSFWorkbook workbook = new HSSFWorkbook();
       //创建工作簿
      HSSFSheet sheet = workbook.createSheet("分区信息统计");
      //创建第一行
      HSSFRow titleRow = sheet.createRow(0);
      titleRow.createCell(0).setCellValue("分区编号");
      titleRow.createCell(1).setCellValue("分区开始编号");
      titleRow.createCell(2).setCellValue("分区结束编号");
      titleRow.createCell(3).setCellValue("分区关键字");
      titleRow.createCell(4).setCellValue("辅助关键字");
      titleRow.createCell(5).setCellValue("区域信息");
      
      for (Area area : all) {
       HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
       row.createCell(0).setCellValue(area.getId());
       row.createCell(1).setCellValue(area.get);
       row.createCell(0).setCellValue();
       row.createCell(0).setCellValue();
       row.createCell(0).setCellValue();
       row.createCell(0).setCellValue();
    }
      
      
       return NONE; 
    }*/

}
  
