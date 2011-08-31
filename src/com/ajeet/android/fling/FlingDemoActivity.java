package com.ajeet.android.fling;

 

 
 

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class FlingDemoActivity extends Activity {
    /** Called when the activity is first created. */
     	private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 250;
    	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    	private GestureDetector gestureDetector;
    	View.OnTouchListener gestureListener;
    	private Animation slideLeftIn;
    	private Animation slideLeftOut;
    	private Animation slideRightIn;
        private Animation slideRightOut;
        private ViewFlipper viewFlipper;
       // private Animation test;
        private int mArraypointer=0;
        private ImageView mNextImageview;
        private int mArraylenth;
       // private ImageView mPrevImageview;
        Drawable icons[];
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            viewFlipper = (ViewFlipper)findViewById(R.id.flipper);
            slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
            slideLeftOut = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);
            slideRightIn = AnimationUtils.loadAnimation(this, R.anim.slide_right_in);
            slideRightOut = AnimationUtils.loadAnimation(this, R.anim.slide_right_out);
          // test =  AnimationUtils.loadAnimation(this, R.anim.translate);
           mNextImageview =(ImageView)findViewById(R.id.next);
         //   mPrevImageview /=(ImageView)findViewById(R.id.prev);
            Resources res=this.getResources();
            icons =new Drawable []{
    		        res.getDrawable(R.drawable.and1),
    		    	res.getDrawable(R.drawable.and2),
    		    	res.getDrawable(R.drawable.and3),
    		    	res.getDrawable(R.drawable.and4),
    		    	res.getDrawable(R.drawable.and5),
    		    	res.getDrawable(R.drawable.and6),
    		     	res.getDrawable(R.drawable.and7),
    		     	res.getDrawable(R.drawable.and8),
    		     	res.getDrawable(R.drawable.and9),
    		     	res.getDrawable(R.drawable.and10),
    		     	res.getDrawable(R.drawable.and11),
    		     	res.getDrawable(R.drawable.and12)
    		     	    		     	
    		    }; 
            mArraylenth=icons.length-1;
            gestureDetector = new GestureDetector(new MyGestureDetector());
            gestureListener = new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (gestureDetector.onTouchEvent(event)) {
                        return true;
                    }
                    return false;
                }
            };
        }
        class MyGestureDetector extends SimpleOnGestureListener {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                        return false;
                    // right to left swipe
                    if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    	viewFlipper.setInAnimation(slideLeftIn);
                        viewFlipper.setOutAnimation(slideLeftOut);
                    //    viewFlipper.startAnimation(test);
                    	changeimage(++mArraypointer);
                        viewFlipper.showNext();
                    	
                    }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    	viewFlipper.setInAnimation(slideRightIn);
                        viewFlipper.setOutAnimation(slideRightOut);
                    	changeimage(--mArraypointer);
                    	viewFlipper.showPrevious();
                    }
                } catch (Exception e) {
                    // nothing
                }
                return false;
            }
        }
        public void changeimage(int i)
        {
        	if(!(i> mArraylenth) && !(i< 0))
        		mNextImageview.setBackgroundDrawable(icons[i]);        		
        	else
        		mArraypointer=0;
        		
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (gestureDetector.onTouchEvent(event))
    	        return true;
    	    else
    	    	return false;
        }
}