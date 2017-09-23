package com.ykai.englishdialog.myapplication;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

/**
 * Created by tangao on 2016/7/24.
 */
public class BaiduTranslateHelper {



    private static final String UTF8 = "utf-8";

    //申请者开发者id，实际使用时请修改成开发者自己的appid
    private static final String APP_ID = "20170528000049484";

    //申请成功后的证书token，实际使用时请修改成开发者自己的token (密钥)
    private static final String SECRET_KEY = "_2uEwktq9rL0Y6rFF_iC";
    //翻译API HTTP地址：
    private static final String baseURL = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    //随机数，用于生成md5值，开发者使用时请激活下边第四行代码
    private static final Random random = new Random();

    public BaiduTranslateHelper() {

    }

    public void translate(final String needToTransString, final String from, final String to, final HttpCallBack callBack) throws Exception {
        //用于md5加密生成签名sign
        int salt = random.nextInt(10000);

        // 对appId+源文+随机数+token计算md5值(签名sign),官方demo提供的下面这种计算为什么不行？？？
        StringBuilder md5String = new StringBuilder();
        md5String.append(APP_ID).append(needToTransString).append(salt).append(SECRET_KEY);
//        String sign = DigestUtils.md5Hex(md5String.toString());

        //应该对 appid+needToTransString+salt+密钥 拼接成的字符串做MD5加密得到32位小写的sign。确保要翻译的文本needToTransString为UTF-8编码。
//        String md5String = APP_ID + new String(needToTransString.getBytes(), "utf-8") + salt + SECRET_KEY;
        final String sign = MD5Encoder.encode(md5String.toString());

        //注意在生成签名拼接 appid+needToTransString+salt+密钥 字符串时，needToTransString不需要做URL encode，
        // 在生成签名之后，发送HTTP请求之前才需要对要发送的待翻译文本字段needToTransString做URL encode。
        final URL urlFinal = new URL(baseURL + "?q=" + URLEncoder.encode(needToTransString, UTF8) +
                "&from=" + from + "&to=" + to + "&appid=" + APP_ID + "&salt=" + salt + "&sign=" + sign);
//       URLEncoder.encode(needToTransString, UTF8);//%E4%BD%A0%E5%A5%BD

        // http://api.fanyi.baidu.com/api/trans/vip/translate?
        // q=apple&from=en&to=zh&appid=2015063000000001
        // &salt=1435660288&sign=f89f9594663708c1605f3d736d01d2d4

        //异步任务访问网络
        new AsyncTask<Void, Integer, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String text = null;
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) urlFinal.openConnection();
                    conn.setRequestMethod("GET");
                    //连接超时
                    conn.setConnectTimeout(8000);
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);

                    String line;
                    StringBuilder builder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        builder.append(line).append("\n");
                    }
                    //关闭输入流
                    br.close();
                    isr.close();
                    is.close();
                    // unicode
                    //System.out.println("builder.toString()  ------->    " + builder.toString());
                    //{"from":"zh","to":"en","trans_result":[{"src":"\u54c8\u55bd\uff0c\u4f60\u597d","dst":"Hello, hello."}]}

                    JSONObject resultJson = new JSONObject(builder.toString());
                    //System.out.println("resultJson.toString()  ------->    " + resultJson.toString());
                    // {"from":"zh","to":"en","trans_result":[{"src":"hello，你好","dst":"Hello, hello."}]}

                    /**
                     * 当翻译结果无法正常返回时，可通过下面的控制台输出找到问题
                     * 如果不用try/catch包裹，下面通过json解析不到text的值
                     */
                    try {
                        String error_code = resultJson.getString("error_code");
                        if (error_code != null) {
                            System.out.println("出错代码:" + error_code);
                            System.out.println("出错信息:" + resultJson.getString("error_msg"));
                            callBack.onFailure("出错信息:" + resultJson.getString("error_msg"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //获取翻译成功的结果
                    JSONArray jsonArray = (JSONArray) resultJson.get("trans_result");
                    JSONObject dstJson = (JSONObject) jsonArray.get(0);
                    text = dstJson.getString("dst");
                    text = URLDecoder.decode(text, UTF8);//utf-8译码
//                    System.out.println("text  ----->   " + text);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {//若url连接异常，则断开连接
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
                return text;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //翻译成功进行成功的回调
                callBack.onSuccess(s);
                System.out.println("onPostExecute  ---->  " + s);
            }
        }.execute();
    }
}