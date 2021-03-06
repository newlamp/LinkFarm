package sinia.com.linkfarm.base;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import sinia.com.linkfarm.R;
import sinia.com.linkfarm.utils.ActivityManager;
import sinia.com.linkfarm.utils.AppInfoUtil;
import sinia.com.linkfarm.utils.SystemBarTintManager;
import sinia.com.linkfarm.view.loadingview.LoadingView;


/**
 * Created by 忧郁的眼神 on 2016/7/14.
 */
public class BaseActivity extends AppCompatActivity {
    private LinearLayout headParent;

    private LinearLayout bodyParent;

    private LinearLayout footParent;

    private TextView devideTv;

    private TextView backView;

    private TextView titleView;

    private TextView doingView;

    private ImageView img_pic;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    private SystemBarTintManager mTintManager;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActivityManager.getInstance().addActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(true);
        mTintManager.setTintColor(getResources().getColor(R.color.themeColor));
        if (!AppInfoUtil.isNetworkConnected(getApplicationContext())) {
            showToast("网络未连接");
        }
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void startActivityForNoIntent(Class forwordClass) {
        Intent intent = new Intent(this, forwordClass);
        startActivity(intent);
    }

    /**
     */
    protected void startActivityForIntent(Class forwordClass, Intent intent) {
        intent.setClass(this, forwordClass);
        startActivity(intent);
    }

    protected void back(View backView) {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.getInstance().finishCurrentActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void setDoingView(TextView doingView) {
        this.doingView = doingView;
    }

    public TextView getDoingView() {
        return doingView;
    }

    public LinearLayout getHeadParent() {
        return headParent;
    }

    public void setHeadParent(LinearLayout headParent) {
        this.headParent = headParent;
    }

    public TextView getBackView() {
        return backView;
    }

    public void setBackView(TextView backView) {
        this.backView = backView;
    }

    public ImageView getImg_pic() {
        return img_pic;
    }

    public void setImg_pic(ImageView img_pic) {
        this.img_pic = img_pic;
    }

    public void setTitleView(TextView titleView) {
        this.titleView = titleView;
    }

    protected void onAfterSetContentView() {
        backView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
        doingView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                doing();
            }

        });
        img_pic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                doing();
            }
        });
    }

    protected void back(){
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }

    public void setContentView(int layoutResID, String title) {
        View body = View.inflate(this, layoutResID, null);
        setContentView(body, title);
        ButterKnife.bind(this);
        onAfterSetContentView();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    public void setContentView(View body, String title) {
        View root = View.inflate(this, R.layout.root_layout, null);
        headParent = (LinearLayout) root.findViewById(R.id.root_layout_head);
        bodyParent = (LinearLayout) root.findViewById(R.id.root_layout_body);
        footParent = (LinearLayout) root.findViewById(R.id.root_layout_foot);
        devideTv = (TextView) root.findViewById(R.id.root_layout_tv);
        buildHeadView(headParent, title);
        bodyParent.addView(body, params);
        super.setContentView(root);
    }

    private void buildHeadView(LinearLayout parent, String title) {
        View head = createHeadView(title);
        if (head != null && !hideHeadView() /* && !TextUtils.isEmpty(title) */) {
            parent.setVisibility(View.VISIBLE);
            parent.addView(head, params);
        } else {
            parent.setVisibility(View.GONE);
        }
    }

    public View createHeadView(String title) {
        View head = View.inflate(this, R.layout.head_layout, null);
        backView = (TextView) head.findViewById(R.id.back);
        titleView = (TextView) head.findViewById(R.id.title);
        doingView = (TextView) head.findViewById(R.id.doing);
        img_pic = (ImageView) head.findViewById(R.id.img_pic);
        titleView.setText(title);
        if (hasShowDoingView()) {
            doingView.setVisibility(View.VISIBLE);
        }
        return head;
    }

    public boolean hasShowDoingView() {
        return true;
    }

    public boolean hideHeadView() {
        return false;
    }

    public View getFootParentView() {
        return footParent;
    }

    public TextView getHengxian() {
        return devideTv;
    }

    public View getHeadParentView() {
        return headParent;
    }

    public View getTitleView() {
        return titleView;
    }

    public void doing() {

    }

    private void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    private View mDialogContentView;
    private LoadingView mLoadingView;

    public void showLoad(String text) {
        dialog = new Dialog(this, R.style.custom_dialog);
        mDialogContentView = LayoutInflater.from(this).inflate(
                R.layout.layout_loading_dialog, null);
        mLoadingView = (LoadingView) mDialogContentView
                .findViewById(R.id.loadView);
        mLoadingView.setLoadingText(text);
        Display d = getWindowManager().getDefaultDisplay();
        dialog.show();
        dialog.setContentView(mDialogContentView, new ViewGroup.LayoutParams((int) (d.getWidth() * 0.5),
                (int) (d.getHeight() * 0.3)));
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCanceledOnTouchOutside(false);
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
