package com.excel.manager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TbJob {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String departmentCode;
    private String departmentName;
    private String employmentBureau;
    private String characterOfStructure;
    private String recruitmentPosition;
    private String jobAttribute;
    private String jobDistribution;
    private String jobDescription;
    private String jobCode;
    private String hierarchyOfOrganization;
    private String examinationCategory;
    private String numberOfApplicants;
    private String professionalRestriction;
    private String educationBackground;
    private String educationQualification;
    private String politicsStatus;
    private String minimumNumberOfYearsOfGrassRootsWork;
    private String workExperienceInServingGrassrootsProjects;
    private String professionalAptitudeTestsAreOrganizedAtTheInterviewStage;
    private String interviewerRatio;
    private String workplace;
    private String placeOfSettlement;
    private String remarks;
    private String departmentalWebsite;
    private String enquiryPhoneOne;
    private String enquiryPhoneTwo;
    private String enquiryPhoneThree;

    public TbJob() {
        this.id = null;
        this.departmentCode = "";
        this.departmentName = "";
        this.employmentBureau = "";
        this.characterOfStructure = "";
        this.recruitmentPosition = "";
        this.jobAttribute = "";
        this.jobDistribution = "";
        this.jobDescription = "";
        this.jobCode = "";
        this.hierarchyOfOrganization = "";
        this.examinationCategory = "";
        this.numberOfApplicants = "";
        this.professionalRestriction = "";
        this.educationBackground = "";
        this.educationQualification = "";
        this.politicsStatus = "";
        this.minimumNumberOfYearsOfGrassRootsWork = "";
        this.workExperienceInServingGrassrootsProjects = "";
        this.professionalAptitudeTestsAreOrganizedAtTheInterviewStage = "";
        this.interviewerRatio = "";
        this.workplace = "";
        this.placeOfSettlement = "";
        this.remarks = "";
        this.departmentalWebsite = "";
        this.enquiryPhoneOne = "";
        this.enquiryPhoneTwo = "";
        this.enquiryPhoneThree = "";
    }
}
