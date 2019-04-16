package com.cloudminds.cms.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Author: zhouchong
 * Created by 76409 on 10:51 2019/4/10.
 * @Description:
 */
public class RedisKeyExpirationEvebtListener extends KeyExpirationEventMessageListener {

	private static final Logger _log = LoggerFactory.getLogger(RedisKeyExpirationEvebtListener.class);
	/**
	 * Creates new {@link MessageListener} for {@code __keyevent@*__:expired} messages.
	 *
	 * @param listenerContainer must not be {@literal null}.
	 */
	public RedisKeyExpirationEvebtListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		_log.info("Redis event message body {}",new String(message.getBody()));
		_log.info("Redis event message channel {}",new String(message.getChannel()));
		_log.info("Redis event message pattern {}",new String(pattern));
		super.onMessage(message, pattern);
	}

}
