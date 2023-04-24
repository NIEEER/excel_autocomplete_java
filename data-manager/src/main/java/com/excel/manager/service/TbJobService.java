package com.excel.manager.service;

import com.excel.manager.dao.JobDao;
import com.excel.manager.pojo.TbJob;
import com.excel.manager.util.ExcelUtil;
import com.excel.util.JobChineseEnglishFieldTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class TbJobService {
    @Autowired
    private JobDao jobDao;

    public void importFromExcel(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        List<TbJob> tbJobs = ExcelUtil.readExcel(inputStream);
        jobDao.saveBatch(tbJobs, (tbJobs.size() / 50));
    }

    public List<String> fetchSuggestions(String inputValue, String columnName) {
        columnName = JobChineseEnglishFieldTranslator.translate(columnName);
        List<String> columnValues = jobDao.getColumnValues(columnName);
        System.out.println(columnValues);
        return columnValues;
    }

    public TbJob getJobById(String id) {
        return jobDao.getById(id);
    }

    public List<TbJob> getAllJob() {
        return jobDao.list();
    }
}
