package cn.cuit.controller;

import cn.cuit.entity.Booking;
import cn.cuit.entity.Pernum;
import cn.cuit.entity.Recording;
import cn.cuit.mapper.BookingMapper;
import cn.cuit.mapper.RecordingMapper;
import cn.cuit.resultAPI.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BRController {
	@Autowired
	private BookingMapper bookingMapper;

	@Autowired
	private RecordingMapper recordingMapper;

	/**
	 * Booking
	 */
	@PostMapping("/booking")
	public Result createBooking(@RequestBody Booking booking){
		return Result.success(bookingMapper.insert(booking));
	}

	@DeleteMapping("/booking/{id}")
	public Result deleteBooking(@PathVariable int id){
		return Result.success(bookingMapper.deleteById(id));
	}

	@PutMapping("/booking")
	public Result updateBooking(@RequestBody Pernum pernum){
		// 寻找是否存在，证明是否预约过
		Pernum i = bookingMapper.selectSGA(pernum);
		if (i != null){
			return Result.failed("请勿重复预约!");
		}
		return Result.success(bookingMapper.updateNum(pernum));
	}

	@GetMapping("/booking/per")
	public Result<List> getPer(){
		return Result.success(bookingMapper.selectListPer());
	}

	@GetMapping("/booking/{id}")
	public Result getBooking(@PathVariable int id){
		return Result.success(bookingMapper.selectById(id));
	}
	@GetMapping("/booking")
	public Result<List> getBk(){
		return Result.success(bookingMapper.selectList(null));
	}
	@GetMapping("/booking/user/{id}")
	public Result<List> getUserBk(@PathVariable int id){
		return Result.success(bookingMapper.selectListUid(id));
	}

	/**
	 * Recording
	 */
	@PostMapping("/record")
	public Result createRecord(@RequestBody Recording recording){
		return Result.success(recordingMapper.insertRe(recording));
	}

	@DeleteMapping("/record/{id}")
	public Result deleteRe(@PathVariable int id){
		return Result.success(recordingMapper.deleteById(id));
	}

	@PutMapping("/record")
	public Result updateRe(@RequestBody Recording recording){
		return Result.success(recordingMapper.updateRe(recording));
	}

	@GetMapping("/record/jiezhong")
	public Result getReJz(){
		return Result.success(recordingMapper.selectJz());
	}

	@GetMapping("/record/{id}")
	public Result getRe(@PathVariable int id){
		return Result.success(recordingMapper.selectByUid(id));
	}
	@GetMapping("/record")
	public Result<List> getRe(){
		return Result.success(recordingMapper.selectListGroup());
	}
}
