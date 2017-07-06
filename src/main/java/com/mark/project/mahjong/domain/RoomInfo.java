package com.mark.project.mahjong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2017/7/5.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfo {

	private String playMethod;

	private String owner;

	private Integer round;

	private Integer diamond;

	private String extend;

	private String awardHorse;

	private String settlement;

}
