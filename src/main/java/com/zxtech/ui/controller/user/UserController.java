package com.zxtech.ui.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zxtech.auth.CustomUserDetails;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.user.UserApplication;
import com.zxtech.ui.vo.user.UserVo;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserApplication userApplication;

	/**
	 * 登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/base")
	public ModelAndView base() throws Exception{
		CustomUserDetails userDetails = AuthDetailUtil.getLoginInfo();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userDetails.getUserId());
		data.put("userName", userDetails.getUsername());
		data.put("password", userDetails.getPassword());
		return new ModelAndView("user/index", data);
	}
	
	/**
	 * 修改用户密码
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateByPassword")
	public Result updateByPassword(UserVo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(userApplication.updateByPassword(record));
		return result;
	}
	
	/**
	 * 重置密码
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/resetPassword")
	public Result resetPassword(UserVo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(userApplication.resetPassword(record));
		return result;
	}
	
	/**
	 * 验证密码
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/validatePassword")
	public String validatePassword(UserVo record) throws Exception {
//		Result result = new Result();
		
//		if(MessageDigestUtil.encode(record.getMianPassword(), "MD5").equals(AuthDetailUtil.getLoginInfo().getPassword())){
//			return "1";
//		} 
		
		return "0";
	}

	/**
	 * 查询用户全部列表
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectByAll")
	public Result selectByAll() throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(userApplication.selectByAll());
		return result;
	}

	/**
	 * 根据用户Id查详细
	 * 
	 * @author dingchao
	 * @param UserId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectByPrimaryKey")
	public Result selectByPrimaryKey(String userId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(userApplication.selectByPrimaryKey(userId));
		return result;
	}

	/**
	 * 查询、搜索、分页
	 * 
	 * @author dinchao
	 * @param record
	 * @param pageNum
	 * @param pageSize
	 * @return selectByKey
	 * @throws Exception
	 */
	@RequestMapping("/selectByKey")
	public Result selectByKey(UserVo record, int start, int length) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = userApplication.selectByKey(record, start / length + 1, length);
		result.setData(page.getList());
//		result.setRecordsTotal(page.getTotal());
//		result.setRecordsFiltered(page.getTotal());
		return result;
	}

	@RequestMapping("/deleteByPrimaryKey")
	public Result deleteByPrimaryKey(String userId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(userApplication.deleteByPrimaryKey(userId));
		return result;
	}

	@RequestMapping("/insert")
	public Result insert(UserVo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(userApplication.insert(record));
		return result;
	}

	@RequestMapping("/insertSelective")
	public Result insertSelective(UserVo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(userApplication.insertSelective(record));
		return result;
	}
}
