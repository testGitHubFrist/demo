package com.nilo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/9/26 </p>
 * <p>Time: 13:56 </p>
 * <p>Version: 1.0.0 </p>
 */
public abstract class NetworkUtil {
    private static final Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

    public static final String LOCALHOST = "127.0.0.1";
    public static final String ANYHOST = "0.0.0.0";
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress())
            return false;
        String name = address.getHostAddress();
        return (name != null
                && ! ANYHOST.equals(name)
                && ! LOCALHOST.equals(name)
                && IP_PATTERN.matcher(name).matches());
    }

    /**
     * 本机地址
     * @return
     */
    public static String getLocalHost(){
        InetAddress address = getLocalAddress();
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    private static volatile InetAddress LOCAL_ADDRESS = null;

    /**
     * 遍历本地网卡，返回第一个合理的IP。
     *
     * @return 本地网卡IP
     */
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null)
            return LOCAL_ADDRESS;
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }
    public static String getLogHost() {
        InetAddress address = LOCAL_ADDRESS;
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = addresses.nextElement();
                                    if (isValidAddress(address)) {
                                        return address;
                                    }
                                } catch (Throwable e) {
                                    logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        logger.error("Could not get local host ip address, will use 127.0.0.1 instead.");
        return localAddress;
    }

    private static final Map<String, String> hostNameCache = new HashMap<String, String>(32);
    public static String getHostName(String address) {
        try {
            int i = address.indexOf(':');
            if (i > -1) {
                address = address.substring(0, i);
            }
            String hostname = hostNameCache.get(address);
            if (hostname != null && hostname.length() > 0) {
                return hostname;
            }
            InetAddress inetAddress = InetAddress.getByName(address);
            if (inetAddress != null) {
                hostname = inetAddress.getHostName();
                hostNameCache.put(address, hostname);
                return hostname;
            }
        } catch (Throwable e) {
            // ignore
        }
        return address;
    }

    /**
     * 获取客户端IP地址
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
    	String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Real-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 6) { // "***.***.***.***".length()== 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
    }

    /**
     * 获取服务器host
     * @param request
     * @return ip:port
     */
    public static String getServerHost(HttpServletRequest request){
        if(request == null) return "";
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port = request.getServerPort();
        StringBuilder buffer = new StringBuilder();
        buffer.append(domain);
        if("http".equalsIgnoreCase(scheme) && port != 80) {
            buffer.append(":").append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(scheme) && port != 443) {
            buffer.append(":").append(String.valueOf(port));
        }
        return buffer.toString();
    }

    /**
     * 获取服务器地址
     * @param request
     * @return
     */
    public static String getServerUrl(HttpServletRequest request){
        if(request == null) return "";
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuilder buffer = new StringBuilder(scheme);
        buffer.append("://");
        buffer.append(domain);
        if("http".equalsIgnoreCase(scheme) && port != 80) {
            buffer.append(":").append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(scheme) && port != 443) {
            buffer.append(":").append(String.valueOf(port));
        }
        buffer.append(contextPath);
        return buffer.toString();
    }
    static boolean isEmpty(String ip){
        return ip == null || ip.length() == 0;
    }


    /**
     * http get请求
     * @param requestUrl
     * @return
     */
    public static JSONObject doGet(String requestUrl) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent  = null;
        JSONObject result = null;
        try {
            //创建Get请求，
            HttpGet httpGet = new HttpGet(requestUrl);
            //执行Get请求，
            response = httpClient.execute(httpGet);
            //得到响应体
            HttpEntity entity = response.getEntity();
            //获取响应内容
            responseContent  = EntityUtils.toString(entity,"UTF-8");
            //转换为map
            result = JSON.parseObject(responseContent);
        } catch (IOException e) {
            logger.error("HttpUtil=====Start");
            logger.error(e.getMessage(),e);
            logger.error("HttpUtil=====End");
        }
        return result;
    }

}
