package cn.cuit.service.impl;

import cn.cuit.entity.Anno;
import cn.cuit.mapper.AnnoMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.AnnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnoServiceImpl implements AnnoService {
	@Autowired
	private AnnoMapper annoMapper;

	public void setAnnoMapper(AnnoMapper annoMapper) {
		this.annoMapper = annoMapper;
	}

	@Override
	public Result<Anno> addNewAnno(Anno anno) {
		return Result.success(annoMapper.insertNewAnno(anno)+"");
	}

	/**
	 * 所谓删除,只是将status字段设置为0而已,并非真正意义的删除
	 */
	@Override
	public Result<Anno> deleteAnno(Anno anno) {
		return Result.success(annoMapper.updateStatus(anno)+"");
	}

	/**
	 * 时间倒序
	 *  - 尚未实现
	 */
	@Override
	public Result<List<Anno>> queryAnno() {
		return Result.success(annoMapper.selectList(null));
	}
}
