package net.ozsofts.wechat.server.message.handler.mq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.MapDeserializer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import net.ozsofts.core.utils.StringUtils;
import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.message.ReceiveEventMessage;
import net.ozsofts.wechat.core.message.ReceiveMessage;
import net.ozsofts.wechat.server.message.handler.MessageHandler;

public class RabbitMessageHandler implements MessageHandler {
	private String host = "localhost";
	private int port = 5672;

	private String username;
	private String password;

	private String exchangeName = "wechat"; // Exchange的名称

	private Channel channel;

	@Override
	public void handle(WxAccount wxAccount, ReceiveMessage message) throws Exception {
		initialize();

		StringBuilder messageKey = new StringBuilder();
		if (ReceiveMessage.MSGTYPE_EVENT.equals(message.getMsgType())) {
			ReceiveEventMessage eventMessage = (ReceiveEventMessage) message;
			messageKey.append(ReceiveMessage.MSGTYPE_EVENT);
			messageKey.append(".");
			messageKey.append(eventMessage.getEventKey());
		} else {
			messageKey.append("message.");
			messageKey.append(message.getMsgType());
		}

		channel.basicPublish(exchangeName, messageKey.toString(), null, null);
	}

	private void initialize() throws Exception {
		if (channel != null)
			return;

		synchronized (this) {
			if (channel != null)
				return;

			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setPort(port);
			if (StringUtils.isNotBlank(username)) {
				factory.setUsername(username);
				factory.setPassword(password);
			}

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			// 声明一个TOPIC类型的Exchange
			channel.exchangeDeclare(exchangeName, "topic");
		}
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		map.put("key5", "value5");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Hessian2Output output = new Hessian2Output(baos);
		output.writeObject(map);
		output.close();

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		Hessian2Input input = new Hessian2Input(bais);
		Map<String, String> result = (Map<String, String>) input.readObject();

		System.out.println(result.toString());
	}
}
