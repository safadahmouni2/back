package com.thinktank.pts.qaservice.dto;

public class DragAndDropTestCaseDTO {

	private Long dragFolderId;
	private Long dropFolderId;
	private Long productId;

	public Long getDragFolderId() {
		return dragFolderId;
	}

	public void setDragFolderId(Long dragFolderId) {
		this.dragFolderId = dragFolderId;
	}

	public Long getDropFolderId() {
		return dropFolderId;
	}

	public void setDropFolderId(Long dropFolderId) {
		this.dropFolderId = dropFolderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
