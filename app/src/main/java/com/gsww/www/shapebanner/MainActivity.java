package com.gsww.www.shapebanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ImageView img_bg;
    private Boolean isPlay=true;
    private ViewPager vp;

    private List<View> mdata;
    private MyAdapter adapter;
    private MediaPlayer player;
   private Handler mHandler = new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           int pos = msg.what;
           pos++;
           vp.setCurrentItem(pos);

       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_bg = (ImageView) findViewById(R.id.img_bg);
        vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setPageTransformer(true,new CustomPageTransform());
        initBlurPic();
        try {
            loadPic();
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player = MediaPlayer.create(this,R.raw.shinian);
        try {
            player.setLooping(true);
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(){
            @Override
            public void run() {
                 while(isPlay){
                     try {
                         sleep(3000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     mHandler.sendEmptyMessage(vp.getCurrentItem());
                 }
            }
        }.start();
        vp.addOnPageChangeListener(this);

    }

    private void initData(){

        /*for (int i = 0; i < pic.length; i++) {
            ImageView img = new ImageView(this);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);

            img.setBackgroundResource(pic[i]);
            mdata.add(img);
        }*/
        int currentItem = getStartSelectItem();
        vp.setOffscreenPageLimit(5);
        adapter = new MyAdapter(this,mdata);
        vp.setAdapter(adapter);
        vp.setCurrentItem(currentItem,true);

    }

    private void initBlurPic() {
        Glide.with(this).load(R.drawable.pic17)
                .bitmapTransform(new BlurTransformation(this, 15), new CenterCrop(this))
                .into(img_bg);
    }

    public int getStartSelectItem() {
        int currentItem = Integer.MAX_VALUE/2;
        if(currentItem%list_image.length==0){
            return currentItem;
        }
        while(currentItem%list_image.length!=0){
            currentItem++;
        }

        return currentItem;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class CustomPageTransform implements ViewPager.PageTransformer {
        public static final float MIN_SCALE = 0.6f;
        @Override
        public void transformPage(View page, float position) {
            if(position<-1){
                page.setScaleY(MIN_SCALE);
                page.setAlpha(MIN_SCALE);
            }else if(position<=1){
                float scale = Math.max(MIN_SCALE,1-Math.abs(position));
                page.setScaleY(scale);
                page.setAlpha(scale);
            }else {
                page.setScaleY(MIN_SCALE);
                page.setAlpha(MIN_SCALE);

            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!player.isPlaying()) {
            player.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
        isPlay=false;
    }
 String[] list_image = null;
  private void loadPic() throws IOException {
      mdata = new ArrayList<>();
      list_image = getAssets().list("imgs");

      for (int i = 0; i <list_image.length; i++) {
          Log.e("a", "loadPic: "+list_image[i] );
          InputStream is = getResources().getAssets().open("imgs/"+list_image[i]);
          Bitmap bmp = BitmapFactory.decodeStream(is);
          ImageView img = new ImageView(this);
          img.setScaleType(ImageView.ScaleType.CENTER_CROP);
          img.setImageBitmap(bmp);
          mdata.add(img);

      }
  }
}