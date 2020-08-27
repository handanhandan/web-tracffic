package com.hjd.prepaser;

/**
 * 预解析原始日志数据
 */
public class WebLogPreParser {

    /**
     * 解析原始数据，每条日志数据封装成preParsedLog对象
     * @param line
     * @return
     */
    public static PreParsedLog parse(String line) {
        if (line.startsWith("#")) {
            return null;
        } else {
            PreParsedLog preParsedLog = new PreParsedLog();
            String[] temps = line.split(" ");

            preParsedLog.setServerTime(temps[0] + " " + temps[1]); //日志产生的服务器时间
            preParsedLog.setServerIp(temps[2]); //
            preParsedLog.setMethod(temps[3]); // 访问方法
            preParsedLog.setUriStem(temps[4]); // 访问uri

            String queryString = temps[5]; // 访问参数
            preParsedLog.setQueryString(queryString);
            String[] queryStrTemps = queryString.split("&");
            String command = queryStrTemps[1].split("=")[1]; // 访客行为类型
            preParsedLog.setCommand(command);
            String profileIdStr = queryStrTemps[2].split("=")[1]; //网站唯一标识
            preParsedLog.setProfileId(getProfileId(profileIdStr));

            preParsedLog.setServerPort(Integer.parseInt(temps[6])); //服务器监听端口
            preParsedLog.setClientIp(temps[8]); // 访客IP
            preParsedLog.setUserAgent(temps[9].replace("+", " "));

            String tempTime = preParsedLog.getServerTime().replace("-", ""); // 设置日志生成的日期
            preParsedLog.setDay(Integer.parseInt(tempTime.substring(0, 8)));
            preParsedLog.setMonth(Integer.parseInt(tempTime.substring(0, 6)));
            preParsedLog.setYear(Integer.parseInt(tempTime.substring(0, 4)));
            return preParsedLog;
        }
    }

    /**
     * 私有方法，获取网站唯一标识
     * @param profileIdStr
     * @return
     */
    private static int getProfileId(String profileIdStr) {
        return Integer.valueOf(profileIdStr.substring(profileIdStr.indexOf("-") + 1));
    }

}
