//package sinia.com.linkfarm.utils;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.view.Display;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.TextView;
//
//import sinia.com.baihangeducation.R;
//
///**
// * Created by 忧郁的眼神 on 2016/7/26.
// */
//public class DialogUtils {
//
//    public static Dialog dialog;
//
//    public static Dialog createLoginOutDialog(final Context context,
//                                              final OnOkListener okListener) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.dialog_loginout, null);
//        dialog = new AlertDialog.Builder(context).create();
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
//        WindowManager windowManager = ((Activity) context).getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.width = (display.getWidth() - 160); // 设置宽度
//        lp.height = (display.getHeight() * 1 / 5); // 设置高度
//        dialog.getWindow().setAttributes(lp);
//        dialog.setContentView(v, lp);
//        final TextView ok = (TextView) dialog.findViewById(R.id.tv_add);
//        final TextView cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
//        ok.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                okListener.onClick();
//                dialog.dismiss();
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                dialog.dismiss();
//            }
//        });
//        return dialog;
//    }
//
//    public static Dialog createPublicDialog(final Context context,
//                                            final OnOkListener okListener) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.dialog_public, null);
//        dialog = new AlertDialog.Builder(context).create();
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
//        WindowManager windowManager = ((Activity) context).getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.width = (display.getWidth() - 160); // 设置宽度
//        lp.height = (display.getHeight() * 1 / 5); // 设置高度
//        dialog.getWindow().setAttributes(lp);
//        dialog.setContentView(v, lp);
//        final TextView cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
//        cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                dialog.dismiss();
//                ActivityManager.getInstance().finishCurrentActivity();
//            }
//        });
//        return dialog;
//    }
//
//    public interface OnOkListener {
//        public void onClick();
//    }
//
//    public interface OnCancelListener {
//        public void onClick();
//    }
//}
