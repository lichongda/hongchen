package com.hongchen.util.linux; /**
 * @author MrBread
 * @date 2017年6月18日
 * @time 下午3:14:05
 * @project_name TestSocket
 * 功能：检测本机端口是否已经被使用用
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class IPUtil {
    //start--end是所要检测的端口范围
    public static void main(String args[]){
        String path=new IPUtil().getFilePathInSrcAfterRun();
        System.out.println(path);
        String filePath=path+ "ipAddsess.properties";
        //打印配置文件
        List<IpAddress> list =  getProperties(filePath);

        for(IpAddress ipAddres:list ){
            if(isLocalPortUsing(ipAddres.getIp(),ipAddres.getPort())) {
                System.out.println("ip " + ipAddres.getIp() + "端口 " + ipAddres.getPort() +"  正常");
            }else{
                System.out.println("ip " + ipAddres.getIp() + "端口 " + ipAddres.getPort() +"  失败");
            }
        }
    }
    private static List<IpAddress> getListResult(){
        IPUtil m = new IPUtil();
        String path=  m.getFilePathInSrcAfterRun();
        System.out.println(path);
        List<IpAddress> list = null;

        return  list;
    }
    /*String path=
        MySrcFileLocationAfterCompile.class.getClassLoader().getResource("").toString();
        编译之后src目录下面的文件，会在bin目录中，
        bin下的文件或者文件夹与src下的文件或者文件夹一一对应
        System.out.println("src目录下的文件经过编译后的位置："+path);
    */
    public String getFilePathInSrcAfterRun()
    {
        String path = this.getClass().getClassLoader().getResource("").toString();
        // System.out.println("编译后src路径："+path);//file:/D:/dev/workspase2Spring/XMLreader/bin/
        int m = path.indexOf("/");// file:/<----点位到file:后面的反斜杠
        path = path.substring(m + 1);// 从反斜杠之后的一位开始截取字符串
        // System.out.println("编译后src路径："+path);
        return path;
    }
    /**
     * 测试本机端口是否被使用
     * @param port
     * @return
     */
    public static boolean isLocalPortUsing(String ip , int port){
        boolean flag = true;  
        try {
            //如果该端口还在使用则返回true,否则返回false,127.0.0.1代表本机
            flag = isPortUsing(ip, port);
        } catch (Exception e) {
            flag = false;
        }  
        return flag;  
    }  
    /*** 
     * 测试主机Host的port端口是否被使用
     * @param host 
     * @param port 
     * @throws UnknownHostException
     */  
    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;  
        InetAddress Address = InetAddress.getByName(host);
        try {  
            Socket socket = new Socket(Address,port);  //建立一个Socket连接
            flag = true;  
        } catch (ConnectException ex){
            System.out.println("连接超时");
        }catch (IOException e) {
            e.printStackTrace();
        }  
        return flag;  
    }

    public static List<IpAddress> getProperties(String filePath){
        List<IpAddress> list = new ArrayList<>();
        Properties properties=new Properties();
        //新建一个配置文件类型。这个类型是一个键值对列表，类似于Map ，
        //Properties类实现了Map接口
        //Properties相当于只能存放String类型的键值对的Map集合
        try
        {
            InputStream inStream=new BufferedInputStream( new FileInputStream(filePath));
            properties.load(inStream);
            Set<String> keys=properties.stringPropertyNames();
            //取出所有的键，作为键的Set集合
            for(Iterator<String> it = keys.iterator(); it.hasNext();)
            {
                String key=it.next();//取出下一个键key
                String value=properties.getProperty(key);
                //通过键从a.properties（相当于键值对Set集合）中取出value
                //System.out.println("key=\t"+key+"\tvalue="+value);
                String[]arrst= value.split(",");
               // IpAddress ipAddress = new IpAddress(arrst[0], Integer.parseInt(arrst[1]));
                //list.add(ipAddress);
            }
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

}

