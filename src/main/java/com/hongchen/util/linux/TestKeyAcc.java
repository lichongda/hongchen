package com.hongchen.util.linux;

import com.jcraft.jsch.*;

public class TestKeyAcc {
public static void main(String[] arg) {

   String keyFile = "F:\\Identity";
   String user = "develop";
   String host = "10.240.4.49";
   String passphrase = "111111";
   int port = 22;
   try {
    JSch jsch = new JSch();
    jsch.addIdentity(keyFile);

    Session session = jsch.getSession(user, host, port);

    // username and passphrase will be given via UserInfo interface.
    UserInfo ui = new MyUserInfo(passphrase);
    session.setUserInfo(ui);
    session.connect();

    Channel channel = session.openChannel("sftp");
    channel.connect();
    ChannelSftp sftp = (ChannelSftp) channel;
    System.out.println(sftp.pwd());
   } catch (Exception e) {
    e.printStackTrace();
    System.out.println(e);
   }
}

public static class MyUserInfo implements UserInfo {
   private String passphrase = null;

   public MyUserInfo(String passphrase) {
    this.passphrase = passphrase;
   }

   public String getPassphrase() {
    return passphrase;
   }

   public String getPassword() {
    return null;
   }

   public boolean promptPassphrase(String s) {
    return true;
   }

   public boolean promptPassword(String s) {
    return true;
   }

   public boolean promptYesNo(String s) {
    return true;
   }

   public void showMessage(String s) {
    System.out.println(s);
   }
}
}