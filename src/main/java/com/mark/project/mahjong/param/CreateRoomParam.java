package com.mark.project.mahjong.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2017/7/4.
 * 创建房间请求参数类
 * 用于封装创建房间请求的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomParam {

	private String uuId;

	private Integer roomType; // 房间类型

	private Integer diamond; //钻石个数

	private Integer round; // 局数

	private Integer peopleNum; // 人数

	private boolean chihu; // 是否能吃胡

	private boolean ziMo; // 自摸

	private boolean tenTimeNoCal; //10倍不计分

	private boolean xiaoHu; //小胡

	private boolean jiHuCannotChiHu; //鸡胡不能吃胡

	private boolean genZhuang; //跟庄

	private boolean tieStilKong; //流局算杠

	private boolean kongBaoPayAll; //吃杠杠爆全包

	private boolean lianZhuang; //连庄

	private Integer awardHorse; //奖马数量

	private Integer buyHorse; //买马数量

	private Integer publishHorse; //罚马数量

	private boolean kongFollHorseNum; //马跟杠

	private Integer maxMultiple; //封顶 5 10 -1:不设封顶

}
