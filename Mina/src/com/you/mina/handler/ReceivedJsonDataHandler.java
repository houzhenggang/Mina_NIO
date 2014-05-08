package com.you.mina.handler;

import java.util.ArrayList;
import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.you.mina.bean.EquipmentBean;
import com.you.mina.datautil.JsonDataUtil;
import com.you.mina.dbutil.SQLUtil;

public class ReceivedJsonDataHandler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) {
		// ��ʾ�ͻ��˵�ip�Ͷ˿�
		System.out.println(session.getRemoteAddress().toString());
	}

	ArrayList<EquipmentBean> arrayList = new ArrayList<EquipmentBean>();
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
		session.write("{'Successful': true,'Message': null}"+arrayList.size());
		System.out.println("Message written..." + str);
		JsonDataUtil jsonDataUtil = new JsonDataUtil();
		arrayList.add(jsonDataUtil.sensorData(str));
		session.write(arrayList.get(0).getType());
		
		if(arrayList.size()==100){
			System.out.println("=========================================================================================");
			SQLUtil sqlUtil = new SQLUtil();
			sqlUtil.addEquipmentBeanList(arrayList);
			arrayList.clear();
		}
		
	}

}
