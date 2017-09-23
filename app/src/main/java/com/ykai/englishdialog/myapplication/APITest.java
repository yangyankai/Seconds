package com.ykai.englishdialog.myapplication;

/**
 * Created by ykai on 17/5/30.
 */
public class APITest {

    public void main(String s[]){

        BaiduTranslateHelper baiduTranslateHelper=new BaiduTranslateHelper();
        try {
            baiduTranslateHelper.translate("你好小度", "zh", "en", new HttpCallBack() {
                @Override
                public void onSuccess(String result) {

                    System.out.printf("res: "+result);
                }

                @Override
                public void onFailure(String exception) {
                    System.out.printf("err: "+exception);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
