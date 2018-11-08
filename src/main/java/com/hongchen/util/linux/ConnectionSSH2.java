package com.hongchen.util.linux;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ConnectionSSH2 {
    public static void main(String[] args) throws JSchException, IOException {
        JSch jsch = new JSch();
        String pubKeyPath = "C:\\Users\\yutao\\.ssh\\id_rsa";
        jsch.addIdentity(pubKeyPath);

        String username = "yutao";
        String host = "192.168.116.131";
        Session session=jsch.getSession(username, host, 22);//为了连接做准备
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        String command = "./test.sh";
//      Channel channel=session.openChannel("shell");
        ChannelExec channel=(ChannelExec)session.openChannel("exec");
        channel.setCommand(command);


//      channel.setInputStream(System.in);
//      channel.setOutputStream(System.out);
//      InputStream in=channel.getInputStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));

        channel.connect();

        String msg;

        while((msg = in.readLine()) != null){
            System.out.println(msg);
        }
        channel.disconnect();
        session.disconnect();
    }
}