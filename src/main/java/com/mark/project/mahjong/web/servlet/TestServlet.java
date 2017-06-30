package com.mark.project.mahjong.web.servlet;

import com.mark.project.mahjong.proto.ProtoLogin;
import com.mysql.jdbc.util.Base64Decoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by Administrator on 2017/6/28.
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {

	/* 装包
	 --------------------------------------------------------------------------
	 | len(2bytes) | cmd(2bytes) | key(4bytes) | bodyLen(4bytes) | data(body) |
	 -------------------------------------------------------------------------
	 0------------2-------------4-------------8-----------------12
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Socket socket = new Socket("192.168.0.115", 6789);
		boolean isConnected = socket.isConnected();
		if (isConnected) {
			//连接成功
			//将pb文件转成byte
			ProtoLogin.LoginRequest.Builder builder = ProtoLogin.LoginRequest.newBuilder();
			builder.setOpenId("Mark8");
			builder.setNickname("kobe bryant");
			builder.setUnionId("asdasd");
			builder.setSex(true);
			builder.setImage("http://www.google.com");
			builder.setProvince("广东");
			builder.setCity("深圳");
			ProtoLogin.LoginRequest loginRequest = builder.build();
			byte[] requestArray = loginRequest.toByteArray();
			System.out.println("requestArray = " + requestArray.length);
			Integer msgLength = 2 + 4 + 4 + requestArray.length;

			//发送数据
			ByteBuffer bb = ByteBuffer.wrap(new byte[2 + msgLength]);
			bb.order(ByteOrder.LITTLE_ENDIAN); //小端模式
			short len = Integer.valueOf(msgLength).shortValue();
			bb.putShort(len);
			bb.putShort(Integer.valueOf(105).shortValue());
			bb.putInt(0);
			bb.putInt(requestArray.length);
			bb.put(requestArray);
			byte[] reqData = Base64.getEncoder().encode(bb.array());
			OutputStream os = socket.getOutputStream();
			os.write(bb.array());
			os.flush();
			//os.close();

			System.out.println("Send message success!!");

			//接收数据
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int readLen = -1;
			if ( (readLen = in.read(buffer)) != -1 ) {
				bos.write(buffer, 0, readLen);
			}
			byte[] result = bos.toByteArray();
			System.out.println("result.len = " + result.length);
			ByteBuffer byteBuffer = ByteBuffer.wrap(result);
			byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
			System.out.println("len = " + byteBuffer.getShort(0));
			System.out.println("cmd = " + byteBuffer.getShort(2));
			System.out.println("bodyLen = " + byteBuffer.getInt(8));

			int bodyLen = byteToInt(byteBuffer.get(8));
			byte[] data1 = new byte[bodyLen];
			for ( int i = 12, j = 0; i < result.length; i++, j++) {
				System.out.println("byte = " + byteBuffer.get(i));
				data1[j] = byteBuffer.get(i);
			}
			//byte[] data = byteBuffer.get(data1, 0, bodyLen).array();
			System.out.println("data.len = " + data1.length);
			//byte[] result1= Base64.getDecoder().decode(data1);
//			String str = new String(data1);
//			BASE64Decoder decoder = new BASE64Decoder();
//			byte[] data2 = decoder.decodeBuffer(str);
			System.out.println("data = " + new String(data1, "UTF-8"));
			ProtoLogin.LoginResponse loginResponse = ProtoLogin.LoginResponse.parseFrom(data1);
			String nickname = loginResponse.getUserInfo().getNickname();
			System.out.println("nickname = " + nickname);
			bos.close();
			in.close();
			os.close();
			socket.close();
		}
		System.out.println("isConnected = " + isConnected);
	}

	/**
	 * 对象转数组
	 * @param obj
	 * @return
	 */
	public byte[] toByteArray (Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray ();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}
	public int byteToInt(byte b) {
		//Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
		return b & 0xFF;
	}
}
