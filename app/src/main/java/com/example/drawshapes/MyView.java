package com.example.drawshapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    private final static int MAX_POINTS = 5; //живет в одном экземпляре в области класса
    public static final String TYPE_RECT = "rect";
    public static final String TYPE_CIRCLE = "circle";
    //треугольник
    public static final String TYPE_TRAINGLE = "traingle";

    int width;
    int height;
    int sizeGrid = 30;
    float density;

      String typeShape = TYPE_RECT;
  //  String typeShape = TYPE_CIRCLE;  //текущая фигура рисования
    String color = "000000";

    int counterPoints;
    PointF[] points = new  PointF[MAX_POINTS];

    //int  counterRect;
    //Rect[] rects = new Rect[100];
    //Canvas canvas;

    //int counterCircles;
    //Circle[] circles = new Circle[100];

//треугольник
//    int counterTraingles;
//    Traingle[] traingles = new Traingle[100];

    int counterShapes;
    Shape[] shapes = new Shape[100];


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        density = getResources().getDisplayMetrics().density;
        sizeGrid *= density;
    }

  //метод для очистки View
    public void undo() {

        if (counterShapes > 0) {
            counterShapes--;
            this.invalidate();
        }
    }

    //метод для выбора Shape

    public void setTypeShape(String typeShape) {
        this.typeShape = typeShape;
        this.invalidate();
    }

    //метод выбора цвета в спиннере
    public void setColor(String color) {
        this.color = color;
        this.invalidate();
    }

//    public MyView(Context context) {
//        super(context);
//        density = getResources().getDisplayMetrics().density;
//        sizeGrid *= density;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // this.canvas = canvas;

  //      canvas.drawColor(Color.RED);

        canvas.getWidth();
        canvas.getHeight();

        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);

     //   canvas.drawLine(0,0,100,200, paint);

        drawGrid(canvas);
        drawPoints(canvas);
        //drawRects(canvas);
        //drawCircles(canvas);
        drawShapes(canvas);
   }

    private void drawGrid(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(density); //ширина линии с плотностью экрана
        int numBlockWidth = canvas.getWidth() / sizeGrid;
        int numBlockHeight = canvas.getHeight() / sizeGrid;

        int yStop = canvas.getHeight();
        for (int i = 1; i <= numBlockWidth; i++) {
            int x = i * sizeGrid;
            canvas.drawLine(x, 0, x, yStop, paint);
        }

        int xStop = canvas.getWidth();
        for (int i = 1; i <= numBlockHeight; i++) {
            int y = i * sizeGrid;
            canvas.drawLine(0, y, xStop, y, paint);
        }
    }
//shape
    void drawShapes(Canvas canvas) {
        Paint paint = new Paint();

        for (int i = 0; i < counterShapes; i++) {
            Shape shape = shapes[i];
            shape.draw(canvas, paint);
        }
    }

//прямоугольник
//    void drawRects(Canvas canvas) {
//        Paint paint = new Paint();
//
//        for (int i = 0; i < counterRect; i++) {
//            Rect rect = rects[i];
//            rect.draw(canvas, paint);
//        }
//    }

//окружность
//    void drawCircles(Canvas canvas) {
//        Paint paint = new Paint();
//
//        for (int i = 0; i < counterCircles; i++) {
//            circles[i].draw(canvas, paint);
//        }
//    }

//треугольник
//    void drawTraingles(Canvas canvas) {
//       Paint paint = new Paint();
//
//        for (int i = 0; i < counterTraingles; i++) {
//            Traingle traingle = traingles[i];
//            traingle.draw(canvas, paint);
//        }
//    }


//точки
    void drawPoints(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);


        int counter = Math.min(counterPoints, MAX_POINTS);

        for (int i = 0; i < counter; i++) {
            PointF pointF = points[i];
            canvas.drawCircle(pointF.x, pointF.y, 20, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
   //     Log.i("touch",event.getAction() + "");
   //     return super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //  sizeGrid += 20;
            //  this.invalidate();
            float x = event.getX();
            float y = event.getY();
            if (counterPoints < MAX_POINTS) {
                points[counterPoints] = new PointF(x, y);
                counterPoints++;

                switch (this.typeShape) {
                    case TYPE_RECT: checkPointsForCreateRect(); break;
                    case TYPE_CIRCLE: checkPointsForCreateCircle(); break;
                    case TYPE_TRAINGLE: checkPointsForCreateTraingle(); break;

                }

                this.invalidate();
            }
        }
        return true;
    }



    //создание окружности
    private void checkPointsForCreateCircle() {
        if (counterPoints >= 2) {
            float a = points[1].x - points[0].x;
            float b = points[1].y - points[0].y;
            float radius = (float)Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
            Circle circle = new Circle(this.color, points[0], radius);
            shapes[counterShapes] = circle;
            counterShapes++;

            counterPoints = 0;
            this.invalidate();
        }
    }

    //метод отрисовки прямоугольников
    private void checkPointsForCreateRect() {
        if (counterPoints >= 2) {
           //создаем прямоугольник
           Rect rect = new Rect(this.color, points[0], points[1]);
          // rect.color = this.color;
          // rect.cornerLT = points[0];
          // rect.cornerRB = points[1];

           shapes[counterShapes] = rect;
           counterShapes++;

           counterPoints = 0;
           this.invalidate();
        }
    }
//треугольник
    private void checkPointsForCreateTraingle() {
        if (counterPoints >= 3) {
            //создаем прямоугольник
           Shape traingle = new Traingle(this.color, points[0], points[1], points[2]);
//            traingle.color = this.color;
//            traingle.cornerOwnT = points[0];
//            traingle.cornerTwoT= points[1];
//            traingle.cornerTreeT= points[2];

            shapes[counterShapes] = traingle;
            counterShapes++;

            counterPoints = 0;
            this.invalidate();
        }
    }


}
