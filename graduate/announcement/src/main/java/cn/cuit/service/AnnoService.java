package cn.cuit.service;

import cn.cuit.entity.Anno;
import cn.cuit.resultAPI.Result;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ResourceBundle;

@Component
public interface AnnoService {
	Result<Anno> addNewAnno(Anno anno);
	Result<Anno> deleteAnno(Anno anno);
	Result<List<Anno>> queryAnno();

}
