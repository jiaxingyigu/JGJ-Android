package com.yigu.jgj.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.util.FileUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zaoren on 2015/6/26.
 */
public class PhotoDialog extends Dialog {
    @Bind(R.id.camera)
    TextView camera;
    @Bind(R.id.photo)
    TextView photo;
    @Bind(R.id.cancel)
    TextView cancel;

    private String imagePath;
    private BaseActivity mActivity;

    public PhotoDialog(Context context,int theme) {
        super(context,theme);
        mActivity = (BaseActivity) context;
        initView();
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private void initView() {
        setContentView(R.layout.dialog_photo);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);//默认黑色背景，设置背景为透明色，小米出现黑色背景
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth(); //设置宽度
        getWindow().setAttributes(lp);
    }

    public void showDialog() {
        super.show();
        getWindow().setGravity(Gravity.BOTTOM);
    }

    @OnClick({R.id.camera, R.id.photo, R.id.cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
                goCamera();
                break;
            case R.id.photo:
                goPicture();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }


    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(FileUtil.createFile(mActivity, imagePath,FileUtil.TYPE_IMAGE)));
        mActivity.startActivityForResult(intent, RequestCode.CAMERA);
        dismiss();
    }

    private void goPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(intent, RequestCode.PICTURE);
        dismiss();
    }

}
