package ru.sberbank.user7.testactivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.w3c.dom.Text;

/**
 * Created by user7 on 25.04.2017.
 */

public class TestView extends View {
    int count = 0;
    private TextPaint paint;
    public TestView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = new TextPaint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(w/3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                count++;
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        SaveState state = new SaveState(super.onSaveInstanceState());
        state.counter = count;
        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SaveState ss = (SaveState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        count = ss.counter;
        invalidate();
    }

    private static class SaveState extends BaseSavedState{
        private int counter;

        private static final Parcelable.Creator<SaveState> CREATOR = new Creator<SaveState>() {
            @Override
            public SaveState createFromParcel(Parcel source) {
                return new SaveState(source);
            }

            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[0];
            }
        };

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(counter);
        }

        public SaveState(Parcel source) {

            super(source);
            counter = source.readInt();
        }

        @TargetApi(Build.VERSION_CODES.N)
        public SaveState(Parcel source, ClassLoader loader) {
            super(source, loader);
            counter = source.readInt();
        }

        public SaveState(Parcelable superState) {
            super(superState);

        }
    }






    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(Integer.toString(count), canvas.getWidth()/2,canvas.getHeight()/2, paint);
        super.onDraw(canvas);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
