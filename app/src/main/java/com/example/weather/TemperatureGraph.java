package com.example.weather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class TemperatureGraph extends View {

    private TextPaint mTextPaint;
    private Paint linePaint;
    private float mTextWidth;
    private float mTextHeight;
    private float verticalPadding;

    static float[] temps;
    static float highest;
    static float lowest;
    static final float verticalPaddingDp = 24f;

    int position;

    public TemperatureGraph(Context context) {
        super(context);
        init(null, 0);
    }

    public TemperatureGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TemperatureGraph(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TemperatureGraph, defStyle, 0);
        int colour = -1; //a.getColor(R.styleable.TemperatureGraph_lineColour, 0);
        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(32f);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();

        float scale = getResources().getDisplayMetrics().density;

        linePaint = new Paint();
        linePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(2f * scale + 0.5f);
        linePaint.setColor(colour);

        verticalPadding = verticalPaddingDp * scale + 0.5f;
    }

    private void invalidateTextPaintAndMeasurements() {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(temps != null){
            float ppd = (getHeight() - verticalPadding * 2) / (highest - lowest);
            //canvas.drawLine(getWidth() / 2f, verticalPadding, getWidth() / 2f, getHeight() - verticalPadding, linePaint);
            if(position == 0){
                float y1 = verticalPadding + (highest - temps[position]) * ppd;
                float y2 = verticalPadding + (highest - midpoint(temps[position], temps[position + 1])) * ppd;
                float x1 = getWidth() / 2f;
                float x2 = getWidth();
                Path path = new Path();
                path.moveTo(x1, y1);
                path.lineTo(x1, getHeight());
                path.lineTo(x2, getHeight());
                path.lineTo(x2, y2);
                path.lineTo(x1, y1);
                canvas.drawPath(path, linePaint);
                canvas.drawText(Float.toString(temps[position]), x1, 0, mTextPaint);
                //canvas.drawLine(getWidth() / 2f, y1, getWidth(), y2, linePaint);

            }else if(position == 23){
                float y1 = verticalPadding + (highest - midpoint(temps[position], temps[position - 1])) * ppd;
                float y2 = verticalPadding + (highest - temps[position]) * ppd;
                float x1 = 0;
                float x2 = getWidth() / 2f;
                Path path = new Path();
                path.moveTo(x1, y1);
                path.lineTo(x1, getHeight());
                path.lineTo(x2, getHeight());
                path.lineTo(x2, y2);
                path.lineTo(x1, y1);
                canvas.drawPath(path, linePaint);
                canvas.drawText(Float.toString(temps[position]), x2, 0, mTextPaint);
                //canvas.drawLine(getWidth() / 2f, y1, 0, y2, linePaint);
            }else{
                float y1 = verticalPadding + (highest - midpoint(temps[position], temps[position - 1])) * ppd;
                float y2 = verticalPadding + (highest - temps[position]) * ppd;
                float y3 = verticalPadding + (highest - midpoint(temps[position], temps[position + 1])) * ppd;
                float x1 = 0;
                float x2 = getWidth() / 2f;
                float x3 = getWidth();
                //canvas.drawLine(0, y1, getWidth() / 2f, y2, linePaint);
                //canvas.drawLine(getWidth() / 2f, y2, getWidth(), y3, linePaint);
                Path path = new Path();
                path.moveTo(x1, y1);
                path.lineTo(x2, y2);
                path.lineTo(x3, y3);
                path.lineTo(x3, getHeight());
                path.lineTo(x1, getHeight());
                path.lineTo(x1, y1);
                canvas.drawPath(path, linePaint);
                canvas.drawText(Float.toString(temps[position]), x2, 0, mTextPaint);
            }
        }
    }

    static void setTemps(Hour[] hours){
        temps = new float[24];
        lowest = (float) hours[0].temp;
        highest = (float) hours[0].temp;
        for(int i = 0; i < hours.length; i++) {
            temps[i] = (float) hours[i].temp;
            lowest = (float) Math.min(lowest, hours[i].temp);
            highest = (float) Math.max(highest, hours[i].temp);
        }
    }

    float midpoint(float a, float b){
        return (a + b) / 2;
    }

    void update(int position){
        this.position = position;
        invalidate();
    }
}