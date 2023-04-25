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
        System.out.println(inputValue);
        columnName = JobChineseEnglishFieldTranslator.translate(columnName);
        return jobDao.getColumnValues(columnName);
    }

    public TbJob getJobById(String id) {
        return jobDao.getById(id);
    }

    public List<TbJob> getAllJob() {
        return jobDao.list();
    }

    public List<TbJob> getJobListByPage(Integer currentPage, Integer pageSize) {
        return jobDao.getJobListByPage(currentPage, pageSize);
    }
}
