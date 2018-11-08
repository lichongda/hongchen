package com.hongchen.util.linux;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

public class ConnectionSSH {
    //字符编码默认是utf-8
    private static String  DEFAULTCHART="UTF-8";
    private static Session session=null;
    private static ChannelExec channel= null;
    public static Boolean login(IpAddress ipAddress){
        boolean flg=false;
        try {
            //String path=new ConnectionSSH().getFilePathInSrcAfterRun();
            //String filePath=path+ "Identity";
            String filePath ="";
            try{
                File cfgFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + ipAddress.getKeyName());
                filePath = cfgFile.getPath();
            }catch (Exception e){

            }
            JSch jsch = new JSch();
            String pubKeyPath = filePath;
            jsch.addIdentity(pubKeyPath);
            session = jsch.getSession(ipAddress.getUserName(), ipAddress.getIp(), ipAddress.getPort());//为了连接做准备
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println("----------------------------"+ipAddress.getIp()+"登录成功");
            flg =true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flg;
    }

    /**
     * @author Ickes
     * 远程执行shll脚本或者命令
     * 即将执行的命令
     * @return
     *      命令执行完后返回的结果值
     * @since V0.1
     */
    public static String execute(IpAddress ipAddress){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if(login(ipAddress)){
                 String serviceMessage= "----------------------------"+ipAddress.getIp()+"登录成功";
                stringBuilder.append(serviceMessage).append("<br/>");
                channel=(ChannelExec)session.openChannel("exec");
                channel.setCommand(ipAddress.getCommand());
                String message = processStdout(channel, DEFAULTCHART);
                System.out.println(message);
                stringBuilder.append(message);
                serviceMessage ="----------------------------"+ipAddress.getIp()+"执行命令"+ipAddress.getCommand() + "---------------结束";
                stringBuilder.append(serviceMessage).append("<br/>");
                System.out.println(serviceMessage);
                channel.disconnect();
            }else{
                String loginMessage= "----------------------------"+ipAddress.getIp()+"登录失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * @author Ickes
     * 远程执行shll脚本或者命令
     * 即将执行的命令
     * @return
     *      命令执行完后返回的结果值
     * @since V0.1
     */
    public static String executePassword(IpAddress ipAddress){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if(login(ipAddress)){
                String serviceMessage= "----------------------------"+ipAddress.getIp()+"登录成功";
                stringBuilder.append(serviceMessage).append("<br/>");
                channel=(ChannelExec)session.openChannel("exec");
                channel.setCommand(ipAddress.getCommand());
                String message = processStdout(channel, DEFAULTCHART);
                System.out.println(message);
                stringBuilder.append(message);
                serviceMessage ="----------------------------"+ipAddress.getIp()+"执行命令"+ipAddress.getCommand() + "---------------结束";
                stringBuilder.append(serviceMessage).append("<br/>");
                System.out.println(serviceMessage);
                channel.disconnect();
            }else{
                String loginMessage= "----------------------------"+ipAddress.getIp()+"登录失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 解析脚本执行返回的结果集
     * @author Ickes
     * @return
     *       以纯文本的格式返回
     */
    public static String processStdout(ChannelExec channel, String charset){
        StringBuffer buffer = new StringBuffer();;
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream(),DEFAULTCHART));
            channel.connect();
            String line;
            while((line = in.readLine()) != null){
                //System.out.println(line);
                buffer.append(line+"<br/>");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public String getFilePathInSrcAfterRun()
    {
        //excel模板路径
        try{
            File cfgFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/excel/inventory_act.xlsx");

        }catch (Exception e){

        }
        String path = this.getClass().getClassLoader().getResource("").toString();
        // System.out.println("编译后src路径："+path);//file:/D:/dev/workspase2Spring/XMLreader/bin/
        int m = path.indexOf("/");// file:/<----点位到file:后面的反斜杠
        path = path.substring(m + 1);// 从反斜杠之后的一位开始截取字符串
        // System.out.println("编译后src路径："+path);
        return path;
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
                //IpAddress ipAddress = new IpAddress(arrst[0], arrst[1]);
                //list.add(ipAddress);
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args)  {
        try {
            String path=new ConnectionSSH().getFilePathInSrcAfterRun();
            String filePath=path+ "ipAddsess.properties";
            List<IpAddress> ipAddressList =  getIpaddress(filePath);
            for(IpAddress ipAddress: ipAddressList){
                ConnectionSSH.execute(ipAddress);
            }
            session.disconnect();
        }catch (Exception e){
            e.printStackTrace();
            if(channel != null){
                channel.disconnect();
            }
            if(session!=null ){
                session.disconnect();
            }
        }
    }



    private static List<IpAddress>getIpaddress(String filePath){
        List<IpAddress>resultList = new ArrayList<>();
        String txt = readToString(filePath);
        JSONObject jsonObject = JSON.parseObject(txt);
        if (jsonObject == null) {
            return null;
        }
        JSONArray hostList = jsonObject.getJSONArray("hostList");
        Iterator<Object> num = hostList.iterator();
        //遍历JSONArray，转换格式。按按钮集合按模块（name）放入map中
        while (num.hasNext()) {
            JSONObject jsonIp = (JSONObject) num.next();
            IpAddress ipAddress = new IpAddress();
            ipAddress.setCommand(jsonIp.get("command").toString());
            ipAddress.setIp(jsonIp.get("ip").toString());
            ipAddress.setPort((Integer) jsonIp.get("port"));
            ipAddress.setUserName((String)jsonIp.get("userName"));
            resultList.add(ipAddress);
        }

        return resultList;
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

}