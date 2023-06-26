package thowl.wiprojekt.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
@Slf4j
public class SubListener implements ApplicationListener<SessionSubscribeEvent> {

	@Override
	public void onApplicationEvent(SessionSubscribeEvent event) {
		Message<byte[]> msg = event.getMessage();
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(msg);
		log.info("-------------Intercepted subscription event--------");
		log.info("Message: " + accessor.getMessage());
		log.info("Sub ID: " + accessor.getSubscriptionId());
		log.info("Receipt ID: " + accessor.getReceiptId());
		log.info("Receipt: " + accessor.getReceipt());
		log.info("-------------end--------------------");
	}
}
