package com.you.mina.handler;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.you.mina.bean.EquipmentBean;
import com.you.mina.datautil.JsonDataUtil;
import com.you.mina.dbutil.SQLUtil;

public class ReceivedJsonDataHandler extends IoHandlerAdapter {
	
	//�������з�ֹ�̳߳�ͻ
	private BlockingQueue<EquipmentBean> queue = new LinkedBlockingQueue<EquipmentBean>();
	private SQLUtil sqlUtil = new SQLUtil();
	
	public ReceivedJsonDataHandler() {
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							//��ȡ�������û����ȴ�
							EquipmentBean bean = queue.take();
		
							if (sqlUtil.addEquipmentBean(bean)) {
								//����ɹ�������
							} else {
								//����ʧ�ܴ�����
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();	
		}
	}
	
	@Override
	public void sessionCreated(IoSession session) {
		// ��ʾ�ͻ��˵�ip�Ͷ˿�
		System.out.println(session.getRemoteAddress().toString());
	}
	//û�÷���
	//ArrayList<EquipmentBean> arrayList = new ArrayList<EquipmentBean>();
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
		session.write("{'Successful': true,'Message': null}"/*+arrayList.size()*/);
		System.out.println("Message written..." + str);
		
		
		JsonDataUtil jsonDataUtil = new JsonDataUtil();
		//����ת��
		EquipmentBean  bean = jsonDataUtil.sensorData(str);
		//���뻺�����
		if (queue.add(bean)) {
			/*
			 * ����ɹ�����
			 * һ�㲻����Ҳ��
			 */
		}else {
			/*
			 * ����ʧ�ܴ���
			 * �����ԭ���ڴ治��
			 */
		}
		//arrayList.add(jsonDataUtil.sensorData(str));
		session.write(bean.getType());
		
		//����û��Ҫ��
		/*if(arrayList.size()==500000){
			System.out.println("=========================================================================================");
			SQLUtil sqlUtil = new SQLUtil();
			sqlUtil.addEquipmentBeanList(arrayList);
			arrayList.clear();
		}*/
		
	}

}
