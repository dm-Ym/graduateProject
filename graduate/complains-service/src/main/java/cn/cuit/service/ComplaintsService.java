package cn.cuit.service;

import cn.cuit.entity.Complaints;
import cn.cuit.resultAPI.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ComplaintsService {
	/**
	 * 用户新增投诉
	 */
	Result<Complaints> addNewComplaints(Complaints complaints);

	/**
	 *  写错了,改一下.
	 */
//	Result<Complaints> updateComplaints(Complaints complaints);
	Result<List<Complaints>> queryAll();

	Result<Complaints> deleteComplaints(int id);
}
