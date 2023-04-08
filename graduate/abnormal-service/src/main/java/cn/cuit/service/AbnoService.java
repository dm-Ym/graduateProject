package cn.cuit.service;


import cn.cuit.entity.Abno;
import cn.cuit.resultAPI.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbnoService {
	Result<Abno> addAbno(Abno abno);
	Result<Abno> deleteAbno(int id);
	Result<Abno> updateAbno(Abno abno);
	Result<Abno> queryAbnoById(int id);
	Result<List<Abno>> queryAllAbno();
}
