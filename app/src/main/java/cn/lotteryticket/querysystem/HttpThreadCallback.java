package cn.lotteryticket.querysystem;

import android.util.ArrayMap;

public abstract class HttpThreadCallback {
    public abstract void runCallback(int runStatus, ArrayMap<String,String> responseHeaderProperties, String result, String error);
}
