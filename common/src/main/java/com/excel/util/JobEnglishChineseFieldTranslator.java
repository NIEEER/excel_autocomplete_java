package com.excel.util;

import java.util.HashMap;
import java.util.Map;

public class JobEnglishChineseFieldTranslator {
    private static final Map<String, String> fieldMap = new HashMap<>();
    static {
        // 初始化中英文字段对应关系
        fieldMap.put("departmentCode", "部门代码");
        fieldMap.put("departmentName", "部门名称");
        fieldMap.put("employmentBureau", "用人司局");
        fieldMap.put("characterOfStructure", "机构性质");
        fieldMap.put("recruitmentPosition", "招考职位");
        fieldMap.put("jobAttribute", "职位属性");
        fieldMap.put("jobDistribution", "职位分布");
        fieldMap.put("jobDescription", "职位简介");
        fieldMap.put("jobCode", "职位代码");
        fieldMap.put("hierarchyOfOrganization", "机构层级");
        fieldMap.put("examinationCategory", "考试类别");
        fieldMap.put("numberOfApplicants", "招考人数");
        fieldMap.put("professionalRestriction", "专业");
        fieldMap.put("educationBackground", "学历");
        fieldMap.put("educationQualification", "学位");
        fieldMap.put("politicsStatus", "政治面貌");
        fieldMap.put("minimumNumberOfYearsOfGrassRootsWork", "基层工作最低年限");
        fieldMap.put("workExperienceInServingGrassrootsProjects", "服务基层项目工作经历");
        fieldMap.put("professionalAptitudeTestsAreOrganizedAtTheInterviewStage", "是否在面试阶段组织专业能力测试");
        fieldMap.put("interviewerRatio", "面试人员比例");
        fieldMap.put("workplace", "工作地点");
        fieldMap.put("placeOfSettlement", "落户地点");
        fieldMap.put("remarks", "备注");
        fieldMap.put("departmentalWebsite", "部门网站");
        fieldMap.put("enquiryPhoneOne", "咨询电话1");
        fieldMap.put("enquiryPhoneTwo", "咨询电话2");
        fieldMap.put("enquiryPhoneThree", "咨询电话3");
    }

    public static String translate(String englishField) {
        return fieldMap.get(englishField);
    }
}
