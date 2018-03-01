package com.example.khoi.touches;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    static int ACTION_BAR_HEIGHT = 56;
    int height = getScreenHeight();
    int width = getScreenWidth();


    private int initialTouchX=0;
    private int initialTouchY=0;
    private int initialMarginLeft=0;
    private int initialMarginTop=0;

    int initalIndex;
    boolean switchSpots;
    RelativeLayout r1;
    TextView tv1;
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        buildGUIByCode();
    }

    private void buildGUIByCode(){
        r1 = new RelativeLayout(this);
        tv1 = new TextView (this);
        tv2 = new TextView(this);

        tv1.setBackgroundColor(0xFFFF0000);
        tv2.setBackgroundColor(0xFF0000FF );

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width/2,height);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(width/2,height);
        params.leftMargin = 0;
        params.rightMargin = 0;
        params2.leftMargin = width/2;
        params2.topMargin = 0;

        r1.addView(tv1,params);
        r1.addView(tv2,params2);
        tv1.setOnTouchListener(this);
        tv2.setOnTouchListener(this);

        String test = String.valueOf(getViewIndex(tv2));

        Toast toast = Toast.makeText(this, test, Toast.LENGTH_LONG);
        //toast.show();


        setContentView(r1);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                initalIndex = getViewIndex(view);
                switchSpots = false;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                initialMarginLeft = params.leftMargin;
                //initialMarginTop = params.topMargin;

                initialTouchX = (int) motionEvent.getRawX();
                //initialTouchY = (int) motionEvent.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:
                params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                params.leftMargin = (int) (motionEvent.getRawX() - initialTouchX + initialMarginLeft);
                //params.topMargin = (int) (motionEvent.getRawY() - initialTouchY + initialMarginTop);
                view.setLayoutParams(params);
                r1.bringChildToFront(view);


                break;
            case MotionEvent.ACTION_UP:
                params = (RelativeLayout.LayoutParams) view.getLayoutParams();

                if(initalIndex == 1){
                    if(params.leftMargin >= width*.25){
                        if(view == tv1){
                            moveIndexToZero(tv2);
                            switchSpots = true;
                        }
                        else if(view == tv2){
                            moveIndexToZero(tv1);
                            switchSpots = true;
                        }
                    }
                }
                else if(initalIndex == 2){
                    if(params.leftMargin <= width*.25){
                        if(view == tv1){
                            moveIndexToHalf(tv2);
                            switchSpots = true;
                        }
                        else if(view == tv2){
                            moveIndexToHalf(tv1);
                            switchSpots = true;
                        }
                    }
                }

                if(initalIndex == 1){
                    if(switchSpots == true){
                        if(view == tv1){
                            moveIndexToHalf(tv1);
                            switchSpots = false;
                        }
                        else if(view == tv2){
                            moveIndexToHalf(tv2);
                            switchSpots = false;
                        }
                    }
                    else if(switchSpots == false){
                        if(view == tv1){
                            moveIndexToZero(tv1);
                            switchSpots = false;
                        }
                        else if(view == tv2){
                            moveIndexToZero(tv2);
                            switchSpots = false;
                        }
                    }
                }
                else if(initalIndex == 2){
                    if(switchSpots == true){
                        if(view == tv1){
                            moveIndexToZero(tv1);
                            switchSpots = false;
                        }
                        else if(view == tv2){
                            moveIndexToZero(tv2);
                            switchSpots = false;
                        }
                    }
                    else if(switchSpots == false){
                        if(view == tv1){
                            moveIndexToHalf(tv1);
                            switchSpots = false;
                        }
                        else if(view == tv2){
                            moveIndexToHalf(tv2);
                            switchSpots = false;
                        }
                    }
                }

                break;
        }
        return true;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public int getViewIndex(View view){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if(params.leftMargin < width/2){
            return 1;
        }
        else
            return 2;
    }

    public void moveIndexToZero(View view){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.leftMargin = 0;
        view.setLayoutParams(params);
    }

    public void moveIndexToHalf(View view){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.leftMargin = width/2;
        view.setLayoutParams(params);
    }


}
