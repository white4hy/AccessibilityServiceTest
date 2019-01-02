package com.accessibilityservicetest;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.socks.library.KLog;

import java.util.List;


public class MyAccessbilityService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        KLog.e("onAccessibilityEvent" + event.getAction() + event.toString());
        if (event != null && (event.getPackageName().toString().contains("qq") || event.getPackageName().toString().contains("tencent"))) {

        }
        if (event.getSource() != null) {
            findAndPerformAction("网络请求");
//            findAndPerformAction("下一步");
//            findAndPerformAction("完成");
        }
    }

    private void findAndPerformAction(String action) {
// AccessibilityEvent.getSource(),
// findFocus(int),
// getWindow()或者
// getRootInActiveWindow()获取窗口内容
        if (getRootInActiveWindow() == null) {
            return;
        }
        List<AccessibilityNodeInfo> text = getRootInActiveWindow().
                findAccessibilityNodeInfosByText(action);
        for (int i = 0; i < text.size(); i++) {
            AccessibilityNodeInfo info = text.get(i);
            if (info.getClassName().equals("android.widget.Button") && info.isEnabled()) {
                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }

    }


    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

    }
}
