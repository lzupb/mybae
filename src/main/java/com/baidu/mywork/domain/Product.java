package com.baidu.mywork.domain;

@Domain
public class Product {

	private Long id;

	private String name;

	private double price;

	private String description;

	private PriceStatus status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PriceStatus getStatus() {
		return status;
	}

	public void setStatus(PriceStatus status) {
		this.status = status;
	}

	/**
	 * 出售商品
	 **/
	public void sell() {
		if (status != null && status.equals(PriceStatus.INVENTORY)) {
			setStatus(PriceStatus.SELLED);
		} else {

		}
	}

	/**
	 * 更新商品
	 **/
	public void update() {
		if (status != null && status.equals(PriceStatus.INVENTORY)) {

		} else {

		}
	}

	/**
	 * 商品退货
	 **/
	public void refund() {
		if (status != null && status.equals(PriceStatus.SELLED)) {

		} else {

		}
	}

	/**
	 * 删除商品
	 **/
	public void delete() {
		if (status != null && status.equals(PriceStatus.INVENTORY)) {

		} else {

		}
	}

	enum PriceStatus {
		SELLED("出售", 1), INVENTORY("在库", 2), DELETE("删除", 3);

		private String name;

		private Integer status;

		private PriceStatus(String name, Integer status) {
			this.name = name;
			this.status = status;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

	}
}
