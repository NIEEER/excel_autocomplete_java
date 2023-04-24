//package com.excel.manager.util;
//
//import com.excel.manager.pojo.TbJob;
//import com.excel.util.JobChineseEnglishFieldTranslator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class ExcelUtil2 {
//
//    public static List<TbJob> readExcel(InputStream inputStream) throws IOException {
//        List<TbJob> departmentList = new ArrayList<>();
//
//        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//        Iterator<Row> rowIterator = sheet.iterator();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            TbJob department = new TbJob();
//            Iterator<Cell> cellIterator = row.cellIterator();
//            if (row.getRowNum() == 0) {
//                while (cellIterator.hasNext()){
//                    Cell cell = cellIterator.next();
//                    System.out.println(JobChineseEnglishFieldTranslator.translate(cell.getStringCellValue())
//                    +"---"+ cell.getColumnIndex());
//                }
//                continue; // skip header row
//            }
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                switch (cell.getColumnIndex()) {
//                    case 0:
//                        department.setDepartmentCode(cell.getStringCellValue());
//                        break;
//                    case 1:
//                        department.setDepartmentName(cell.getStringCellValue());
//                        break;
//                    case 2:
//                        department.setEmploymentBureau(cell.getStringCellValue());
//                        break;
//                    case 3:
//                        department.setCharacterOfStructure(cell.getStringCellValue());
//                        break;
//                    case 4:
//                        department.setRecruitmentPosition(cell.getStringCellValue());
//                        break;
//                    case 5:
//                        department.setJobAttribute(cell.getStringCellValue());
//                        break;
//                    case 6:
//                        department.setJobDistribution(cell.getStringCellValue());
//                        break;
//                    case 7:
//                        department.setJobDescription(cell.getStringCellValue());
//                        break;
//                    case 8:
//                        department.setJobCode(cell.getStringCellValue());
//                        break;
//                    case 9:
//                        department.setHierarchyOfOrganization(cell.getStringCellValue());
//                        break;
//                    case 10:
//                        department.setExaminationCategory(cell.getStringCellValue());
//                        break;
//                    case 11:
//                        department.setNumberOfApplicants(cell.getStringCellValue());
//                        break;
//                    case 12:
//                        department.setProfessionalRestriction(cell.getStringCellValue());
//                        break;
//                    case 13:
//                        department.setEducationBackground(cell.getStringCellValue());
//                        break;
//                    case 14:
//                        department.setPoliticsStatus(cell.getStringCellValue());
//                        break;
//                    case 15:
//                        department.setEducationQualification(cell.getStringCellValue());
//                        break;
//                    case 16:
//                        department.setMinimumNumberOfYearsOfGrassRootsWork(cell.getStringCellValue());
//                        break;
//                    case 17:
//                        department.setWorkExperienceInServingGrassrootsProjects(cell.getStringCellValue());
//                        break;
//                    case 18:
//                        department.setProfessionalAptitudeTestsAreOrganizedAtTheInterviewStage(cell.getStringCellValue());
//                        break;
//                    case 19:
//                        department.setInterviewerRatio(cell.getStringCellValue());
//                        break;
//                    case 20:
//                        department.setWorkplace(cell.getStringCellValue());
//                        break;
//                    case 21:
//                        department.setPlaceOfSettlement(cell.getStringCellValue());
//                        break;
//                    case 22:
//                        department.setRemarks(cell.getStringCellValue());
//                        break;
//                    case 23:
//                        department.setDepartmentalWebsite(cell.getStringCellValue());
//                        break;
//                    case 24:
//                        department.setEnquiryPhoneOne(cell.getStringCellValue());
//                        break;
//                    case 25:
//                        department.setEnquiryPhoneTwo(cell.getStringCellValue());
//                        break;
//                    case 26:
//                        department.setEnquiryPhoneThree(cell.getStringCellValue());
//                        break;
//                    default:
//                        break;
//                }
//            }
//            departmentList.add(department);
//        }
//        workbook.close();
//        inputStream.close();
//        return departmentList;
//    }
//}