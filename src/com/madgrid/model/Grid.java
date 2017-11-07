package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class Grid implements Serializable{

	
	public static final int GRID_TYPE_REGULAR = 1;
	public static final int GRID_TYPE_BEGINNER = 2;
	public static final int GRID_TYPE_WINNER_IS_FIRST = 3;
	public static final int GRID_TYPE_FIXED_PRICE = 4;
	public static final int GRID_TYPE_DOUBLE_OR_NOTHING = 5;
	public static final int GRID_TYPE_FREE = 6;
	public static final int GRID_TYPE_MULTIPRIZE = 7;
	
	private Integer id;
	private Integer itemId;
	private Date created;
	private Date modified;
	private Date startDate;
	private Integer type;
	private Integer boxes;
	private Double boxPrice;
	private Integer freeBoxes;
	private Integer boughtBoxes;
	private Double moneyWon;
	private Integer winPos;
	private Boolean finished;
	private Date finishDate;
	private Boolean ongoing;
	private Integer partialWinSeconds;
	private Boolean isInPartialWin;
	private Date partialWinStartTime;
	private User partialWinUser;
	private User partialWinPreviousUser;
	private Integer partialWinUserId;
	private Integer partialWinPreviousUserId;
	private Integer partialWinPreviousBoxId;
	private String virtualPath;
	private String winPosText;
	private String winPosHash;
	private Integer multiPrize1_1CreditPos;
	private Integer multiPrize1_2CreditPos;
	private Integer multiPrize1_3CreditPos;
	private Integer multiPrize1_4CreditPos;
	private Integer multiPrize1_5CreditPos;
	private Integer multiPrize2_1CreditPos;
	private Integer multiPrize2_2CreditPos;
	private Integer multiPrize5CreditPos;
	private Integer multiPrize10CreditPos;
	
	

	public String getVirtualPath() {
		return virtualPath;
	}
	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public Integer getPartialWinPreviousUserId() {
		return partialWinPreviousUserId;
	}
	public void setPartialWinPreviousUserId(Integer partialWinPreviousUserId) {
		this.partialWinPreviousUserId = partialWinPreviousUserId;
	}
	public User getPartialWinPreviousUser() {
		return partialWinPreviousUser;
	}
	public void setPartialWinPreviousUser(User partialWinPreviousUser) {
		this.partialWinPreviousUser = partialWinPreviousUser;
	}
	public Integer getPartialWinSeconds() {
		return partialWinSeconds;
	}
	public void setPartialWinSeconds(Integer partialWinSeconds) {
		this.partialWinSeconds = partialWinSeconds;
	}
	public Boolean getIsInPartialWin() {
		return isInPartialWin;
	}
	public void setIsInPartialWin(Boolean isInPartialWin) {
		this.isInPartialWin = isInPartialWin;
	}
	public Date getPartialWinStartTime() {
		return partialWinStartTime;
	}
	public void setPartialWinStartTime(Date partialWinStartTime) {
		this.partialWinStartTime = partialWinStartTime;
	}
	public User getPartialWinUser() {
		return partialWinUser;
	}
	public void setPartialWinUser(User partialWinUser) {
		this.partialWinUser = partialWinUser;
	}
	public Integer getPartialWinUserId() {
		return partialWinUserId;
	}
	public void setPartialWinUserId(Integer partialWinUserId) {
		this.partialWinUserId = partialWinUserId;
	}
	private Item item;
	
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Boolean getFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getBoxes() {
		return boxes;
	}
	public void setBoxes(Integer boxes) {
		this.boxes = boxes;
	}
	public Double getBoxPrice() {
		return boxPrice;
	}
	public void setBoxPrice(Double boxPrice) {
		this.boxPrice = boxPrice;
	}
	public Integer getFreeBoxes() {
		return freeBoxes;
	}
	public void setFreeBoxes(Integer freeBoxes) {
		this.freeBoxes = freeBoxes;
	}
	public Integer getBoughtBoxes() {
		return boughtBoxes;
	}
	public void setBoughtBoxes(Integer boughtBoxes) {
		this.boughtBoxes = boughtBoxes;
	}
	public Double getMoneyWon() {
		return moneyWon;
	}
	public void setMoneyWon(Double moneyWon) {
		this.moneyWon = moneyWon;
	}
	public Integer getWinPos() {
		return winPos;
	}
	public void setWinPos(Integer winPos) {
		this.winPos = winPos;
	}
	public Boolean getOngoing() {
		return ongoing;
	}
	public void setOngoing(Boolean ongoing) {
		this.ongoing = ongoing;
	}
	public Integer getPartialWinPreviousBoxId() {
		return partialWinPreviousBoxId;
	}
	public void setPartialWinPreviousBoxId(Integer partialWinPreviousBoxId) {
		this.partialWinPreviousBoxId = partialWinPreviousBoxId;
	}
	public String getWinPosText() {
		return winPosText;
	}
	public void setWinPosText(String winPosText) {
		this.winPosText = winPosText;
	}
	public String getWinPosHash() {
		return winPosHash;
	}
	public void setWinPosHash(String winPosHash) {
		this.winPosHash = winPosHash;
	}
	public Integer getMultiPrize1_1CreditPos() {
		return multiPrize1_1CreditPos;
	}
	public void setMultiPrize1_1CreditPos(Integer multiPrize1_1CreditPos) {
		this.multiPrize1_1CreditPos = multiPrize1_1CreditPos;
	}
	public Integer getMultiPrize1_2CreditPos() {
		return multiPrize1_2CreditPos;
	}
	public void setMultiPrize1_2CreditPos(Integer multiPrize1_2CreditPos) {
		this.multiPrize1_2CreditPos = multiPrize1_2CreditPos;
	}
	public Integer getMultiPrize1_3CreditPos() {
		return multiPrize1_3CreditPos;
	}
	public void setMultiPrize1_3CreditPos(Integer multiPrize1_3CreditPos) {
		this.multiPrize1_3CreditPos = multiPrize1_3CreditPos;
	}
	public Integer getMultiPrize1_4CreditPos() {
		return multiPrize1_4CreditPos;
	}
	public void setMultiPrize1_4CreditPos(Integer multiPrize1_4CreditPos) {
		this.multiPrize1_4CreditPos = multiPrize1_4CreditPos;
	}
	public Integer getMultiPrize1_5CreditPos() {
		return multiPrize1_5CreditPos;
	}
	public void setMultiPrize1_5CreditPos(Integer multiPrize1_5CreditPos) {
		this.multiPrize1_5CreditPos = multiPrize1_5CreditPos;
	}
	public Integer getMultiPrize2_1CreditPos() {
		return multiPrize2_1CreditPos;
	}
	public void setMultiPrize2_1CreditPos(Integer multiPrize2_1CreditPos) {
		this.multiPrize2_1CreditPos = multiPrize2_1CreditPos;
	}
	public Integer getMultiPrize2_2CreditPos() {
		return multiPrize2_2CreditPos;
	}
	public void setMultiPrize2_2CreditPos(Integer multiPrize2_2CreditPos) {
		this.multiPrize2_2CreditPos = multiPrize2_2CreditPos;
	}
	public Integer getMultiPrize5CreditPos() {
		return multiPrize5CreditPos;
	}
	public void setMultiPrize5CreditPos(Integer multiPrize5CreditPos) {
		this.multiPrize5CreditPos = multiPrize5CreditPos;
	}
	public Integer getMultiPrize10CreditPos() {
		return multiPrize10CreditPos;
	}
	public void setMultiPrize10CreditPos(Integer multiPrize10CreditPos) {
		this.multiPrize10CreditPos = multiPrize10CreditPos;
	}
	
		
	
}
