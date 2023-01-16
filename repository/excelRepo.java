package excel.read.save.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import excel.read.save.model.excel;

public interface excelRepo extends JpaRepository<excel, Integer> {

}
