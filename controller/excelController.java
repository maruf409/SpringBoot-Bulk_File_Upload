package excel.read.save.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import excel.read.save.model.excel;
import excel.read.save.service.excelService;

@RestController
@RequestMapping("/excelfile")
public class excelController {
	Map<String, Object> map = new HashedMap<>();

	@Autowired
	private excelService excelService;

	@PostMapping("/store/db")
	public ResponseEntity<?> excelFileUpload(@RequestParam("file") MultipartFile file) {
		List<excel> excels = excelService.saveExcelToDatabase(file);
		map.put("excelList", excels);
		return ResponseEntity.ok().body(map);
	}
}
