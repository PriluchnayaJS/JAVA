package com.example.drawshapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Traingle  extends Shape{
   // String color;
    PointF cornerOwnT;
    PointF cornerTwoT;
    PointF cornerTreeT;

    Traingle(String color, PointF cornerOwnT, PointF cornerTwoT, PointF cornerTreeT) {
        super(color);
        this.cornerOwnT = cornerOwnT;
        this.cornerTwoT = cornerTwoT;
        this.cornerTreeT = cornerTreeT;
    }


    public void draw (Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#" + this.color));
//        paint.setStrokeWidth(5);



        Path path = new Path();
        path.moveTo(cornerOwnT.x, cornerOwnT.y);
        path.lineTo(cornerTwoT.x, cornerTwoT.y);
        path.lineTo(cornerTreeT.x, cornerTreeT.y);
        path.lineTo(cornerOwnT.x, cornerOwnT.y);

        canvas.drawPath(path, paint);

//        canvas.drawLine(cornerOwnT.x, cornerOwnT.y, cornerTwoT.x, cornerTwoT.y, paint);
//        canvas.drawLine(cornerTwoT.x, cornerTwoT.y, cornerTreeT.x, cornerTreeT.y, paint);
//        canvas.drawLine(cornerTreeT.x, cornerTreeT.y, cornerOwnT.x, cornerOwnT.y, paint);

    }

}
