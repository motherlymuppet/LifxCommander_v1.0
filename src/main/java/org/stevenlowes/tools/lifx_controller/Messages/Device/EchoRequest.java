package org.stevenlowes.tools.lifx_controller.Messages.Device;

import java.util.Random;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class EchoRequest extends Payload{
	private final int code = 58;
	private byte[] payload;			// 64-Bytes
	
	public EchoRequest() {
		payload = new byte[64];
		new Random().nextBytes(payload);
	}
	
	public EchoRequest(byte[] payload) {
		this.payload = payload;
	}
	
	public EchoRequest(EchoRequest echoRequest) {
		payload = echoRequest.payload;
	}
	
	public int getCode() {
		return code;
	}
	
	public byte[] getPayload() {
		return payload;
	}
	
	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[64];
		for(int i=0; i<64; i++) byteArray[i] = payload[(-1*i)+63];
		return byteArray;
	}

}
