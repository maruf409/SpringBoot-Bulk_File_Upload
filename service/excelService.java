package excel.read.save.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import excel.read.save.model.excel;
import excel.read.save.repository.excelRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class excelService {

	@Autowired
	private excelRepo excelRepo;

	public List<excel> saveExcelToDatabase(MultipartFile file) {

		List<excel> list = new ArrayList<excel>();
		try {
			list = ExcelHandler.extractExcelSheet(file.getInputStream());
			for (excel excel : list) {
				excelRepo.save(excel);
			}
		} catch (Exception e) {

		}

		return list;
	}

}
