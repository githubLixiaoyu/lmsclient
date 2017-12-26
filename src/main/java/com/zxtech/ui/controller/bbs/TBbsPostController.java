package com.zxtech.ui.controller.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.bbs.TBbsPostApplication;
import com.zxtech.ui.vo.bbs.TBbsPost;
import com.zxtech.ui.vo.bbs.TBbsPostReply;

@RestController
@RequestMapping("/ui/tbbspost")
public class TBbsPostController {
	@Autowired
	private TBbsPostApplication tBbsPostApplication;

	/**
	 * 获取论坛信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectBbsInfo")
	public Result selectBbsInfo() throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tBbsPostApplication.selectBbsInfo());
		return result;
	}

	/**
	 * 发表帖子
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveBbsInfo")
	public Result saveBbsInfo(TBbsPost tBbsPost) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tBbsPostApplication.saveBbsInfo(tBbsPost));
		return result;
	}

	/**
	 * 获取帖子回复信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectBbsReplyInfoByPostId")
	public Result selectBbsReplyInfoByPostId(String postId) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tBbsPostApplication.selectBbsReplyInfoByPostId(postId));
		return result;
	}

	/**
	 * 添加浏览量
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addBbsLookNum")
	public Result addBbsLookNum(String id) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tBbsPostApplication.addBbsLookNum(id));
		return result;
	}

	/**
	 * 回复帖子
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addBbsReply")
	public Result addBbsReply(TBbsPostReply tBbsPostReply) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tBbsPostApplication.addBbsReply(tBbsPostReply));
		return result;
	}
}