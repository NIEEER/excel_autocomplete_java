package com.excel.util;

import java.util.HashMap;
import java.util.Map;

public class JobChineseEnglishFieldTranslator {
    private static final Map<String, String> fieldMap = new HashMap<>();
    static {
        // 初始化中英文字段对应关系
        fieldMap.put("部门代码", "departmentCode");
        fieldMap.put("部门名称", "departmentName");
        fieldMap.put("用人司局", "employmentBureau");
        fieldMap.put("机构性质", "characterOfStructure");
        fieldMap.put("招考职位", "recruitmentPosition");
        fieldMap.put("职位属性", "jobAttribute");
        fieldMap.put("职位分布", "jobDistribution");
        fieldMap.put("职位简介", "jobDescription");
        fieldMap.put("职位代码", "jobCode");
        fieldMap.put("机构层级", "hierarchyOfOrganization");
        fieldMap.put("考试类别", "examinationCategory");
        fieldMap.put("招考人数", "numberOfApplicants");
        fieldMap.put("专业", "professionalRestriction");
        fieldMap.put("学历", "educationBackground");
        fieldMap.put("学位", "educationQualification");
        fieldMap.put("政治面貌", "politicsStatus");
        fieldMap.put("基层工作最低年限", "minimumNumberOfYearsOfGrassRootsWork");
        fieldMap.put("服务基层项目工作经历", "workExperienceInServingGrassrootsProjects");
        fieldMap.put("是否在面试阶段组织专业能力测试", "professionalAptitudeTestsAreOrganizedAtTheInterviewStage");
        fieldMap.put("面试人员比例", "interviewerRatio");
        fieldMap.put("工作地点", "workplace");
        fieldMap.put("落户地点", "placeOfSettlement");
        fieldMap.put("备注", "remarks");
        fieldMap.put("部门网站", "departmentalWebsite");
        fieldMap.put("咨询电话1", "enquiryPhoneOne");
        fieldMap.put("咨询电话2", "enquiryPhoneTwo");
        fieldMap.put("咨询电话3", "enquiryPhoneThree");
    }

    public static String translate(String chineseField) {
        return fieldMap.get(chineseField);
    }
}
