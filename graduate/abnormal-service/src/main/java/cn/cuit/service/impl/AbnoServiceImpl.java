package cn.cuit.service.impl;

import cn.cuit.entity.Abno;
import cn.cuit.mapper.AbnoMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.AbnoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class AbnoServiceImpl implements AbnoService {

	@Autowired
	private AbnoMapper abnoMapper;

	public void setUserMapper(AbnoMapper abnoMapper) {
		this.abnoMapper = abnoMapper;
	}

	@Override
	public Result<Abno> addAbno(@NotNull Abno abno) {
		int insert = abnoMapper.insert(abno);
		if (insert == 0){
			Result.failed("已经存在用户"+ abno);
		}
		return Result.success("新增用户成功");
	}

	@Override
	public Result<Abno> deleteAbno(int id) {
		if (abnoMapper.deleteById(id) == 0){
			Result.failed("用户不存在");
		}
		return Result.success("删除用户成功");
	}

	@Override
	public Result<Abno> updateAbno(Abno abno) {
//		abnoMapper.updateInfo(abno);
		return Result.success(abnoMapper.selectById(abno.getId()));
	}

	@Override
	public Result<Abno> queryAbnoById(int id) {
		return Result.success(abnoMapper.selectById(id));
	}

	@Override
	public Result<List<Abno>> queryAllAbno() {
		return Result.success(abnoMapper.selectList(null));
	}
}
