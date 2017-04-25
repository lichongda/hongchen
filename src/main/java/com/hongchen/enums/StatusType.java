package com.hongchen.enums;
/**

 * @Version:			[ v1.0 ]
 * @Description:   		[ 类型 ]
 */
public enum StatusType {

	NORMAL(0),//运行
	CONGEAL(1),//停止
	DELETE(2);//删除
	
	private int value;
	
	private StatusType(int value){
		 this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static StatusType fromTo(int value)
	{
		StatusType[] values = StatusType.values();
		for (StatusType hongChenType : values)
		{
			if (hongChenType.getValue() == value)
			{
				return hongChenType;
			}
		}
		throw new IllegalArgumentException("类型不正确,不能识别为[" + value + "]的类型.");
	}

	public static void main(String[] args) {
		System.out.println(StatusType.CONGEAL.value );

	}
}
