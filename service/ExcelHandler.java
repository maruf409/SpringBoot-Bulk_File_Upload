package excel.read.save.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excel.read.save.model.excel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelHandler {

	private ExcelHandler() {

	}

	public static List<excel> extractExcelSheet(InputStream stream) {
		List<excel> excels = new ArrayList<excel>();
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(stream);
			Sheet sheet = workbook.getSheetAt(0);
			boolean skipHeader = true;
			for (Row row : sheet) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				List<Cell> cells = new ArrayList<Cell>();
				int lastColumn = Math.max(row.getLastCellNum(), 7);

				for (int i = 0; i < lastColumn; i++) {
					Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					cells.add(cell);
				}

				excel excel = extractFromCell(cells);
				excels.add(excel);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return excels;
	}

	private static excel extractFromCell(List<Cell> cells) {
		excel excel = new excel();

		Cell address = cells.get(0);
		if (address != null) {
			excel.setAddress(address.getStringCellValue());
		}

		Cell phone = cells.get(1);
		if (phone != null) {
			excel.setPhoneNumber(phone.getStringCellValue());
		}

		Cell gender = cells.get(2);
		if (gender != null) {
			excel.setGender(gender.getStringCellValue());
		}

		Cell location = cells.get(3);
		if (location != null) {
			excel.setLocation(location.getStringCellValue());
		}

		Cell parmanentAddress = cells.get(4);
		if (parmanentAddress != null) {
			excel.setParmanentAddress(parmanentAddress.getStringCellValue());
		}

		Cell personName = cells.get(5);
		if (personName != null) {
			excel.setPersonName(personName.getStringCellValue());
		}

		Cell profession = cells.get(6);
		if (profession != null) {
			excel.setProfession(profession.getStringCellValue());
		}

		return excel;
	}
}
