package com.hongchen.util.linux;
public class IpAddress {
        private String ip;
        private String command;
        private String userName;
        private Integer port;
        private String keyName;
        private String userPassword;

        public IpAddress() {

        }
        public IpAddress(String ip, String command, String userName, Integer port, String keyName) {
                this.ip = ip;
                this.command = command;
                this.userName = userName;
                this.port = port;
                this.keyName = keyName;
        }

        public IpAddress(String ip, String command, String userName, Integer port, String keyName, String userPassword) {
                this.ip = ip;
                this.command = command;
                this.userName = userName;
                this.port = port;
                this.keyName = keyName;
                this.userPassword = userPassword;
        }

        public String getIp() {
                return ip;
        }

        public void setIp(String ip) {
                this.ip = ip;
        }

        public String getCommand() {
                return command;
        }

        public void setCommand(String command) {
                this.command = command;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public Integer getPort() {
                return port;
        }

        public void setPort(Integer port) {
                this.port = port;
        }

        public String getKeyName() {
                return keyName;
        }

        public void setKeyName(String keyName) {
                this.keyName = keyName;
        }

        public String getUserPassword() {
                return userPassword;
        }

        public void setUserPassword(String userPassword) {
                this.userPassword = userPassword;
        }
}