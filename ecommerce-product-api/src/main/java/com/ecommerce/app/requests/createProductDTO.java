package com.ecommerce.app.requests;

import lombok.Data;

@Data
public class createProductDTO {

	private String productCode;

	private String productName;

	private String shortDescription;

	private String longDescription;

	private boolean canDisplay;

	private boolean isDeleted;

	private boolean isAutomotive;

	private boolean isInternational;

	private Long parentCategory;

	private Long category;
	
}
