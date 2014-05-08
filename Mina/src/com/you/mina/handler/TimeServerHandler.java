package com.you.mina.handler;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeServerHandler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) {
		// ��ʾ�ͻ��˵�ip�Ͷ˿�
		System.out.println(session.getRemoteAddress().toString());
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String str = message.toString();
		if (str.trim().equalsIgnoreCase("quit")) {
			// �����Ự
			session.close();
			return;
		}
		Date date = new Date();
		// ���ص�ǰʱ����ַ���
		session.write(date.toString());
		System.out.println("Message written..." + str);
	}

}
