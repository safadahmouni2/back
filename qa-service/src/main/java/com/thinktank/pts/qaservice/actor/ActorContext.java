package com.thinktank.pts.qaservice.actor;

import org.springframework.util.StringUtils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActorContext {

	private String code;

	public boolean isEmpty() {
		return StringUtils.isEmpty(code);
	}
}