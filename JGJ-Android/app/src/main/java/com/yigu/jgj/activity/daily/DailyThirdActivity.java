package com.yigu.jgj.activity.daily;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.imageutils.BitmapUtil;
import com.soundcloud.android.crop.Crop;
import com.yigu.jgj.R;
import com.yigu.jgj.adapter.ImageAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.result.MapiImageResult;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.DPUtil;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.FileUtil;
import com.yigu.jgj.commom.util.JGJBitmapUtils;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.widget.DividerGridItemDecoration;
import com.yigu.jgj.widget.MainAlertDialog;
import com.yigu.jgj.widget.PhotoDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DailyThirdActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.submit)
    TextView submit;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    ArrayList<MapiImageResult> mList;
    ImageAdapter mAdapter;
    private PhotoDialog photoDialog;
    MapiItemResult itemResult;
    String tasksend = "";
    MainAlertDialog dialog;
    private int deletePos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_third);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras())
            itemResult = (MapiItemResult) getIntent().getSerializableExtra("item");
        if(null!=itemResult){
            initView();
            initListener();
        }
    }

    private void initView() {
        tvCenter.setText("日常巡查");
        mList = new ArrayList<>();
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this, DPUtil.dip2px(8), this.getResources().getColor(R.color.background_all)));//分割线
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new ImageAdapter(this,mList);
        recyclerView.setAdapter(mAdapter);
        dialog = new MainAlertDialog(this);
        dialog.setLeftBtnText("取消").setRightBtnText("确认").setTitle("确认删除这张图片?");
    }

    private void initListener(){
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mList.size()>= Config.MAX_VALUE){
                    deletePos = position;
                    dialog.show();

                }else{
                    if(position==mList.size()){
                        if (photoDialog == null)
                            photoDialog = new PhotoDialog(DailyThirdActivity.this, R.style.image_dialog_theme);
                        photoDialog.setImagePath("daily_image.jpg");
                        photoDialog.showDialog();
                    }
                    else{
                        deletePos = position;
                        dialog.show();

                    }
                }
            }
        });

        dialog.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deletePos>=0){
                    mList.remove(deletePos);
                    mAdapter.notifyItemRemoved(deletePos);
                    mAdapter.notifyItemRangeRemoved(deletePos,mList.size()+1);
                }
                dialog.dismiss();
            }
        });

    }

    @OnClick({R.id.back, R.id.file, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.file:
                file();
                break;
            case R.id.submit:
                getImages();
                itemResult.setRemark(editText.getText().toString());
                ControllerUtil.go2SelRoot(itemResult);
                break;
        }
    }

    private void getImages(){
        String images = "";
        StringBuilder sb = new StringBuilder();
        for (MapiImageResult imageResult : mList) {
            if (sb.length() != 0)
                sb.append(",");
            sb.append(imageResult.getID());
        }
        images = sb.toString();
        itemResult.setImage(images);
    }

    private void file(){//归档
        getImages();
        itemResult.setRemark(editText.getText().toString());
        showLoading();
        DailyApi.dailyPatrol(this, "0", itemResult.getID(), userSP.getUserBean().getUSER_ID(), itemResult.getPtioners() + "", itemResult.getHCATEN() + "", itemResult.getShowlicense() + "",
                itemResult.getHygiene() + "", itemResult.getInvoice() + "", itemResult.getSanitation() + "", itemResult.getOverdue() + "", itemResult.getFullmark() + "", itemResult.getTrain() + "",
                itemResult.getDisinfection() + "", itemResult.getPoster() + "",itemResult.getRemark() , tasksend, itemResult.getImage(), new RequestCallback() {
                    @Override
                    public void success(Object success) {
                        hideLoading();
                        MainToast.showShortToast("归档成功");
                        ControllerUtil.go2Daily();
                        finish();
                    }
                }, new RequestExceptionCallback() {
                    @Override
                    public void error(String code, String message) {
                        hideLoading();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        DebugLog.i("requestCode=" + requestCode + "resultCode=" + resultCode);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case RequestCode.CAMERA:
                    File picture = FileUtil.createFile(this, "daily_image.jpg",FileUtil.TYPE_IMAGE);
                    startPhotoZoom(Uri.fromFile(picture));
                break;
                case RequestCode.PICTURE:
                    if (data != null)
                        startPhotoZoom(data.getData());
                    break;
                case  Crop.REQUEST_CROP: //缩放以后
                    if (data != null) {
                        File picture2 = FileUtil.createFile(this, "daily_image_crop.jpg",FileUtil.TYPE_IMAGE);
                        String filename = picture2.getAbsolutePath();
//                        Bitmap bitmap = BitmapFactory.decodeFile(filename);
//                        JGJBitmapUtils.saveBitmap2file(bitmap, filename);
                        if (JGJBitmapUtils.rotateBitmapByDegree(filename, filename, JGJBitmapUtils.getBitmapDegree(filename))) {
                            uploadImage(picture2);
                        } else
                            DebugLog.i("图片保存失败");
                         }
                break;
            }
        }

    }

    private void uploadImage(File file){
        showLoading();
        DailyApi.uploadImage(this, file, new RequestCallback<MapiImageResult>() {
            @Override
            public void success(MapiImageResult success) {
                hideLoading();
                if(null!=success){
                    mList.add(success);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                hideLoading();
                DebugLog.i(message);
            }
        });
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Uri outUrl = Uri
                .fromFile(FileUtil.createFile(this, "daily_image_crop.jpg",FileUtil.TYPE_IMAGE));
        Crop.of(uri, outUrl).asSquare().withMaxSize(500, 500).start(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("item",itemResult);
        outState.putSerializable("list",mList);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        itemResult = (MapiItemResult) savedInstanceState.getSerializable("item");
        mList = (ArrayList<MapiImageResult>) savedInstanceState.getSerializable("list");
    }

}
