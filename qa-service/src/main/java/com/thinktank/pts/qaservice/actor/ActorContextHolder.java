package com.thinktank.pts.qaservice.actor;

import org.springframework.security.core.context.SecurityContextHolder;

public final class ActorContextHolder {

	private ActorContextHolder() {
	}

	private static final ThreadLocal<ActorContext> CONTEXTHOLDER = new ThreadLocal<>();

	public static void setActor(ActorContext actor) {

		if (actor == null) {
			throw new IllegalArgumentException("Only non-null ActorContext instances are permitted");
		}

		CONTEXTHOLDER.set(actor);
	}

	public static ActorContext getActor() {

		ActorContext actor = CONTEXTHOLDER.get();

		if (actor == null) {
			actor = createEmptyContext();
			CONTEXTHOLDER.set(actor);
		}

		return actor;
	}

	public static ActorContext createEmptyContext() {
		return ActorContext.builder().build();
	}

	/**
	 * Info about actor and security context.
	 *
	 * @return
	 */
	public static String infoString() {
		return String.format("actor=%s, secCtx=%s", getActor().getCode(),
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

	public static void removeActor() {
		CONTEXTHOLDER.remove();
	}
}
