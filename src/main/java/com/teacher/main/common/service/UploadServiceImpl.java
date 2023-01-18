package com.teacher.main.common.service;

import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.teacher.main.enums.UploadHeader;
import com.teacher.main.exception.BadRequestException;
import com.teacher.main.teacher.dto.TeacherDTO;
import com.teacher.main.teacher.service.TeacherService;
import com.teacher.main.utility.ExcelContent;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private TeacherService teacherService;

	@Override
	public String processTeachersCreationUsingFile(MultipartFile uploadedFile) throws Exception {

		String filename = uploadedFile.getOriginalFilename();
		String fileExtension = filename.substring(filename.indexOf('.'));
		Workbook workbook;

		if (fileExtension.equals(".xls")) {
			workbook = new HSSFWorkbook(uploadedFile.getInputStream());
		} else if (fileExtension.equals(".xlsx")) {
			workbook = new XSSFWorkbook(uploadedFile.getInputStream());
		} else {
			throw new BadRequestException("EX109");
		}
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		return createTeachers(rowIterator);
	}

	private HashMap<String, Integer> convertHeader(Iterator<Cell> cellIterator) {

		HashMap<String, Integer> headerMap = new HashMap<>();
		int c = 0;
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			headerMap.put(cell.getStringCellValue(), c);
			c++;
		}
		return headerMap;
	}

	private String createTeachers(Iterator<Row> rowIterator) throws Exception {

		ExcelContent excelContent = new ExcelContent();
		int r = 0;

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			excelContent.setRow(row);
			r++;
			Iterator<Cell> cellIterator = row.cellIterator();
			if (r == 1) {
				excelContent.setHeaderMap(convertHeader(cellIterator));
			} else {
				Cell cell = excelContent.getCell(UploadHeader.NAME);
				if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {

					String name = excelContent.getValue(UploadHeader.NAME);
					String age = excelContent.getValue(UploadHeader.AGE);
					String gender = excelContent.getValue(UploadHeader.GENDER);
					String email = excelContent.getValue(UploadHeader.EMAIL);
					String role = excelContent.getValue(UploadHeader.ROLE);
					String username = excelContent.getValue(UploadHeader.USERNAME);
					String password = excelContent.getValue(UploadHeader.PASSWORD);

//					Teacher existing = teacherService.g

					TeacherDTO teacher = new TeacherDTO();
					if (!StringUtils.isEmpty(name)) {
						teacher.setName(name);
					}
					if (!StringUtils.isEmpty(age)) {
						teacher.setAge(Integer.valueOf(age));
					}
					if (!StringUtils.isEmpty(gender)) {
						teacher.setGender(gender);
					}
					if (!StringUtils.isEmpty(email)) {
						teacher.setEmail(email);
					}
					if (!StringUtils.isEmpty(role)) {
						teacher.setRole(role);
					}
					if (!StringUtils.isEmpty(username)) {
						teacher.setUsername(username);
					}
					if (!StringUtils.isEmpty(password)) {
						teacher.setPassword(password);
					}
					teacherService.create(teacher);
				} else {
					throw new BadRequestException("EX110");
				}
			}
		}
		return "Teachers created successfully";
	}
}