package com.zking.zkingedu.common.config;

import org.springframework.stereotype.Component;

/**
 * @email 2417902780@qq.com
 * @author:阿飘
 * @company 飘飘公司
 * @create 2019-06-22 15:34
 */
public class AlipayConfig {

    //商户APPID(沙箱环境在沙箱页面查看,正式环境需要申请应用,后面详细说明)
    public static String APP_ID = "2016101100660846";
    //public static String APP_ID = "2019062265626815";
    // 应用私钥(即前面所生成的私钥)
    public static String PRIVARY_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCe3zQ0c0Z0+CB8M/Y/cnQyIprHNT/L4s3fAEts/yCuVKUO5UtPPbivA2LsGWEAm6uxGtMo9MWn47zbIz/2Y+4nbio8rYxNKd5yXV7CQQGE80PKEgORqnLPwod2ZyfMLclWyggCdKFdAQtH8X+8JltFAJh5eayADPxKez0NT0Gs/M5dADO1yR/oOzqAqjuLAeRdtNisK8HEmoTeDrnOQoIfC9HnUygz6nJleHb6v8rwNJSPs/4exWQ9uPg7k28iTWtVlbSGmd3rG6DGEBzywbTvhyya/HwGaYzZmv4Xbg/jLb5flT59Hi2VSd3DXtKI3nOeLrYXtdGZo6u24wnoVGaVAgMBAAECggEAI9kxqnQofdRPYDdJZV+qIHX2fuThFFyFH8CtRFb9RNkMtaO/ro260qLs+2Fupfh6cWggJam1wAB/aOM3V52M65DMLwzqD69W5m9zJloMwbcyoLIX5BKV/ZNjhbAZJtVeDRthW0x97MKDjSRNMmNlTNipjx+JcQt9EkFksVAk/adiPwCyBYEo3pXtXW0J5tgA/LhzP+d1jk7SCTxyh/v+TvXamAy3oo58k8pVYSzD3cbsewKjCR/p78GVjddblulUoJAXOxmrByOka6TnxmCT7scJNRASRzcFLmpdAiPHlZYTilfP1ML8V7B8IF5/BZI0lPi+mwOSIWVYNvcHkfslMQKBgQDh+fPfTXi4Dpeppp080NWYkRmFzqNjfAcBpLZbIkyApAICFvM4pUWhVUHAEuiQuni7i7fGMfD8NwRnoD276A9OVIO5eC7Myqg1gt36z3aAw1vtDkXvLE/0cmEhL2cRunrCtY760UzGVgaXKWf2/M5dnBby+olWChmFr0lQ94i8xwKBgQCz+totqUfHNJQxuEljezIvU63zgia1BG6rCgH0pk7yQ8caAxMCeUeO5QYDxsYJWRHhbhWYmIHQG451DpzKUPBtkq5FXt2hjv1LSJLyyVaFIalYIUqbrsyWcINF36KOpn06q48f7j6nQLPfNA8aoOJqgHFNv08EKj4olh7WWjWNwwKBgQCrK1Qo71vjv1nuLcCo0l8Y47/7dRyaPNZpzkQ7H+3m+1RyFMRDQh1OKXBWd3wmNLlDBoA75pRt15bEj8JzVUJSyP3GPVzlzxQ/BFP0qbVPr6swE0Gx+1TNCt677lYaPbDDUBdvsZ6AFaWEVt7jXXdLvPI9Nr1UqO3QL4hPhlDS/QKBgH4Ylpu/KkbrE5jyIJjOS47U9d7RpQTWIiM7U7tweYdK7UfQN5VYG0aGNvyWW1FZUim60u6iivCfHePtZPRqWMisJ+T6XhU+9T+1jR8E6NVFjDvutzLz+Oq90Xs2/9Ep3Dsqz4/zf3n23w7Wjm59oaQ0cS2jh1zHq90LsHdFpJe5AoGAXP0ShIZCEYxoP6LcSHhOnq5zuZHTOJUuzxS/QHQqZ9NbMoIZaiDTBFyWAnGzmh9MkCpV3dYsGY1oM5IbJ1OLSEzgwwqYGR94dsehyRB04QzMPTZ1jLOBPBcdIX2WF8bjQ7YsFhpZX//X7nDNP+zlBN93b+5C8poOJXQsxgtUQsk=";
    // 应用公钥(即前面所生成的公钥)
    public static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnt80NHNGdPggfDP2P3J0MiKaxzU/y+LN3wBLbP8grlSlDuVLTz24rwNi7BlhAJursRrTKPTFp+O82yM/9mPuJ24qPK2MTSnecl1ewkEBhPNDyhIDkapyz8KHdmcnzC3JVsoIAnShXQELR/F/vCZbRQCYeXmsgAz8Sns9DU9BrPzOXQAztckf6Ds6gKo7iwHkXbTYrCvBxJqE3g65zkKCHwvR51MoM+pyZXh2+r/K8DSUj7P+HsVkPbj4O5NvIk1rVZW0hpnd6xugxhAc8sG074csmvx8BmmM2Zr+F24P4y2+X5U+fR4tlUndw17SiN5zni62F7XRmaOrtuMJ6FRmlQIDAQAB";

    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvmz7XgHUenQqbq8QqzdhHKEe218VcNHQdwnqzNrgF5hAeLrdUNwoxMUDR4FbT2PUlc9dwR8jEqz+8wCpWyoGYRNwbPDZNxg0FC1AnVBd9SROiq7eWCRhpz/Y3TtwOV1yR6vDiTdtND54rkQbKBA5cir2iTWBQpa/BH7iaOfqsZdeM8zcKwbK5/aJEOGsxaYyNRJ+zKE8BIXUoToKDE9zTM5tBfXpvjIqxISJpc67lS12Pm559vScwGj10C4gSQSg2DkjWm9NGE7EirNYyV6DP8vVebPm2AOzF4wdmegwHoFoDsUixdQ3uovNaOTKtbrDeF2P4qcR+EKlAlYvh6zjxQIDAQAB";
    //http://0hg02lie2c.52http.net
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL = "http://www.zkingedu.top:8899";
    //跳转页面，买家支付成功后跳转的页面
    public static String RETURN_URL = "http://www.zkingedu.top:8899/user/userinfo/index#get8";
    // RSA2
    public static String SIGN_TYPE = "RSA2";
    // 编码格式
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 日志记录目录
    public static String LOG_PATH = "/log";
    //支付网关 正式环境("https://openapi.alipay.com/gateway.do";)无dev
    public static String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    //public static String GATEWAY_URL = "https://openapi.alipay.com/gateway.do";

}
