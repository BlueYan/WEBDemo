package com.mark.project.mahjong.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Administrator on 2017/6/30.
 * 套接字客户端
 * 和服务器建立连接
 */
public class SocketClient {

	private Socket mSocket = null;

	private static final String IP = "192.168.0.115";

	private static final Integer PORT = 6789;

	public SocketClient() {
		onConnection();
	}

	/**
	 * 开启链接
	 */
	private void onConnection() {
		try {
			if ( mSocket == null ) {
				mSocket = new Socket(IP, PORT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//提示创建连接失败
		}

	}

	/**
	 *  --------------------------------------------------------------------------
	 *  | len(2bytes) | cmd(2bytes) | key(4bytes) | bodyLen(4bytes) | data(body) |
	 *  -------------------------------------------------------------------------
	 *  0------------2-------------4-------------8-----------------12
	 * @param cmd 请求的命令
	 * @param key 请求的key 默认可设置0
	 * @param data 请求的数据
	 */
	public void onSendMessage(Integer cmd, Integer key, byte[] data) {
		OutputStream os = null;
		try {
			// 消息包的长度
			Integer msgLength = 2 + 4 + 4 + data.length;
			// 将所有的数据放到缓冲区中
			ByteBuffer bb = ByteBuffer.wrap(new byte[2 + msgLength]);
			bb.order(ByteOrder.LITTLE_ENDIAN); //设置小端模式
			bb.putShort(msgLength.shortValue());
			bb.putShort(cmd.shortValue());
			bb.putInt(key);
			bb.putInt(data.length);
			bb.put(data);
			os = mSocket.getOutputStream();
			os.write(bb.array());
			//mSocket.shutdownOutput();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			if ( os != null ) {
//				try {
//					os.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
		}
	}

	/**
	 *  --------------------------------------------------------------------------
	 *  | len(2bytes) | cmd(2bytes) | key(4bytes) | bodyLen(4bytes) | data(body) |
	 *  -------------------------------------------------------------------------
	 *  0------------2-------------4-------------8-----------------12
	 * 获取数据
	 * @return 返回byte[]
	 */
	public byte[] onGetMessage() {
		InputStream in = null;
		ByteArrayOutputStream bos = null;
		try {
			in = mSocket.getInputStream();
			bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int readLen = in.read(buffer);
			System.out.println("readLen = " + readLen);
			bos.write(buffer, 0, readLen);
			ByteBuffer byteBuffer = ByteBuffer.wrap(bos.toByteArray());
			byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
			//获取bodayLen的数据 表示data的长度
			int bodyLen = byteBuffer.getInt(8);
			byte data[] = new byte[bodyLen];
			//更新数据包的格式 从第12位开始 将后面的data数据复制出来
			for ( int i = 12, j = 0; i < bos.toByteArray().length; i++, j++ ) {
				data[j] = byteBuffer.get(i);
			}
			//mSocket.shutdownInput();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( bos != null ) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
//			if ( in != null ) {
//				try {
//					in.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
		}
		return null;
	}

	/**
	 * 关闭链接
	 */
	public void onDisconnection() {
		if ( mSocket != null ) {
			try {
				mSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
