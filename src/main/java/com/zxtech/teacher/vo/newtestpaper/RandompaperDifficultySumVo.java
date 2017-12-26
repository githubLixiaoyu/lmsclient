package com.zxtech.teacher.vo.newtestpaper;

import java.util.List;

import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficulty;
import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficultySumWithBLOBs;

public class RandompaperDifficultySumVo {

	private TRandompaperDifficultySumWithBLOBs randompaperDifficultySum;
	
	private List<TRandompaperDifficulty> randompaperDifficultyList;

	public List<TRandompaperDifficulty> getRandompaperDifficultyList() {
		return randompaperDifficultyList;
	}

	public void setRandompaperDifficultyList(List<TRandompaperDifficulty> randompaperDifficultyList) {
		this.randompaperDifficultyList = randompaperDifficultyList;
	}

	public TRandompaperDifficultySumWithBLOBs getRandompaperDifficultySum() {
		return randompaperDifficultySum;
	}

	public void setRandompaperDifficultySum(TRandompaperDifficultySumWithBLOBs randompaperDifficultySum) {
		this.randompaperDifficultySum = randompaperDifficultySum;
	}


}
