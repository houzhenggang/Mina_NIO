package com.you.mina.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.you.mina.handler.TimeClientHandler;

public class MinaTimeClient {
	public static void main(String[] args) { 
		// �����ͻ���������. 
		NioSocketConnector connector = new NioSocketConnector(); 
		connector.getFilterChain().addLast( "logger", new LoggingFilter() ); 
		//connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" )))); //���ñ��������
		connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory()));
		connector.setConnectTimeout(30); 
		connector.setHandler(new TimeClientHandler());//�����¼������� 
		ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9123));//�������� 
		cf.awaitUninterruptibly();//�ȴ����Ӵ������ 
		cf.getSession().write("hello");//������Ϣ 
//		cf.getSession().write("quit");//������Ϣ 
		cf.getSession().getCloseFuture().awaitUninterruptibly();//�ȴ����ӶϿ� 
		connector.dispose(); 
		}

}
