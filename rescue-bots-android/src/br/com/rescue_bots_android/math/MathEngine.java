package br.com.rescue_bots_android.math;

import br.com.rescue_bots_android.geom.Point;

public class MathEngine
{
    
    /**
      * Point in the azimute distance
      * @param P1 - Point2D point reference
      * @param Azimute - double azimute value
      * @param Distance - double distance 
      * @return - point2D result
      */
    public static Point getPointAtAzimuteDistance(Point P1, double Azimute, double Distance) {
    	double x = (double) (P1.getX() + Math.sin( Math.toRadians(Azimute)) * Distance);
    	double y = (double) (P1.getY() + Math.cos(Math.toRadians(Azimute)) * Distance);
        return new Point(x,y);
    }
 
    
    
    /**
      * Calculate azimute
      * @param P1 - first point
      * @param P2 - last point
      * @return - double azimute value
      */
    public static double getAzimute(Point P1, Point P2) {
        if ((P2.getY() - P1.getY()==0))
        if ((P2.getX() - P1.getX()<0))
           return 270.0;
        else return 90.0;
        if((P2.getX() - P1.getX()==0))
            if ((P2.getY() - P1.getY()<0))
               return 180.0;
            else return 0.0;
        //Calculo do Coeficiente Angular da Reta P1P2
        double Direct = Math.atan2(P2.getX() - P1.getX(), P2.getY() - P1.getY());
        if (Direct<0)
        Direct+=Math.toRadians(360);
        return Math.toDegrees(Direct);
    }

   
  /**
   * Get Point in the distance
   * @param P1 - first point 
   * @param P2 - last point
   * @param Distance - current distance
   * @return Point2D point result
   */
  public static Point getPointAtDistance(Point P1, Point P2, double Distance) {
      //calculo do angulo que a reta p1p2 forma
      double angulo = getDirection(P1,P2);
      return getPointAtDirectionDistance(P1, angulo, Distance);
  }
 
 /**
  * Get Point in the direction
  * @param P1 - first point 
  * @param P2 - last point
  * @return double direction result
  */
 public static double getDirection(Point P1, Point P2) {
     if ((P2.getY() - P1.getY()==0))
     if ((P2.getX() - P1.getX()<0))
        return 0.0;
     else return 180.0;
     if((P2.getX() - P1.getX()==0))
         if ((P2.getY() - P1.getY()<0))
            return 270.0;
         else return 90.0;
     //Calculo do Coeficiente Angular da Reta P1P2
     double Direct = Math.atan2(P1.getY() - P2.getY(), P2.getX() - P1.getX());
     if (Direct<0)
     Direct+=Math.toRadians(360);
     return Math.toDegrees(Direct);
 }
    public static double getDirection(Double[] P1, Double[] P2) { 
     Point p1 = new Point(P1[0],P1[1]);
     Point p2 = new Point(P2[0],P2[1]);
     return getDirection(p1,p2);
 }
 
 
    /**
      * Get Point in the direction angle
      * @param P1 - point 2D original
      * @param Angle - angle definied
      * @param P2 - last point 2D definied
      * @return the point in  the direction
      */
    public static Point getPointAtDirection(Point P1, double Angle, Point P2) {
        //reducao da distancia entre P1 e P2 
        double AngP1P2 = Math.atan2((P2.getY() - P1.getY()), P2.getX() - P1.getX());
        double aux = Math.toRadians(Angle)-AngP1P2;
        if (aux<0) 
        aux = Math.toRadians(360)-aux;
        double DistRed = Math.cos(aux) * getRaio(P1,P2);
        return getPointAtDirectionDistance(P1, Angle, DistRed);
             
    }
 
    /**
      * Get Point in the direction angle and distance
      * @param P1 - point 2D original
      * @param Angle - angle definied
      * @param Distance - current distance
      * @return the point in  the direction
      */
    public static Point getPointAtDirectionDistance(Point P1, double Angle, double Distance) {
        double AngRad = Math.toRadians(Angle);
        double Xp = (float) (P1.getX() + Math.cos(AngRad)*Distance);
        double Yp = (float) (P1.getY() - Math.sin(AngRad)*Distance);    
        return  new Point(Xp,Yp);
    }
    public static double[] getPointAtDirectionDistance(double[] P1, double Angle, double Distance) {
       Point p = new Point(P1[0],P1[1]);
        
        Point r = getPointAtDirectionDistance(p,Angle,Distance);
        return new double[]{r.getX(),r.getY()};
    }
    
    
    
    /** Find the Radius of Circunference that  have Center in CENTRO and passes through of Ponto.
     * @param <b>Centro</b> Center of Circunference.
     * @param <b>Ponto</b> Any Point that belongs to Circunference.
     * @return Radius of Circunference. 
     */
    public static double getRaio(Point Centro, Point Ponto) {
        double aux1, aux2;
        aux1 = Math.pow((Centro.getX() - Ponto.getX()), 2);
        aux2 = Math.pow((Centro.getY() - Ponto.getY()), 2);
        double r = Math.sqrt(aux1 + aux2);
        return r;
    }
    
    /**
     * Distance calculate by 2 points
     * @param ponto1 initial coordinate.
     * @param ponto2 final coordinate.
     * @return distance by coordenates.
     */
    public static float getDistance(float[] ponto1, float[] ponto2)
    {
      float vY = Math.abs(ponto1[1] - ponto2[1]);
      float vX = Math.abs(ponto1[0] - ponto2[0]);
      
      float distance = (float)Math.hypot(vX,vY);
      
      return distance;
    }
    
    public static double getRaio(double[] P1, double[] P2) {
        Point p1 = new Point(P1[0],P1[1]);
        Point p2 = new Point(P2[0],P2[1]);
        
        return getRaio(p1,p2);
    }
    
    
    public static double getDistanceD(Double[] ponto1, Double[] ponto2)
    {
      double vY = Math.abs(ponto1[1] - ponto2[1]);
      double vX = Math.abs(ponto1[0] - ponto2[0]);
      
      double distance = (double)Math.hypot(vX,vY);
      
      return distance;
    }

/*
 *  end key events operations
 */

  

  /**
   * Determines whether a point lies in an arc
   * @param arc 6 doubles representing the arc (in JGeometry.computeArc format)
   * @param direction +ve = anticlockwise, -ve = clockwise in a standard Cartesian reference
   * @param x X coordinate of the point (no need to be on the arc, as the angle is all that is considered)
   * @param y Y coordinate of the point (no need to be on the arc, as the angle is all that is considered)
   * @return true if the point is on the arc
   */
  public static boolean ptInArc(double[] arc, double direction, double x,
                                 double y)
  {
    double angle = Math.atan2(y - arc[1], x - arc[0]);
    if (angle < 0.0)
      angle += 2 * Math.PI;
    if (angle == arc[3] || angle == arc[5])
      return true;
    boolean flip = false;
    if (direction > 0.0)
      flip = !flip;
    if (arc[5] > arc[3])
      flip = !flip;
    if (arc[5] > angle && angle > arc[3] ||
        arc[3] > angle && angle > arc[5])
      flip = !flip;
    return flip;
  }
  /**
   * Determines whether a point lies in an arc - but the end point doesn't count 
   * (this is used for polygon inside/outside testing)
   * @param arc 6 doubles representing the arc (in JGeometry.computeArc format)
   * @param direction +ve = anticlockwise, -ve = clockwise in a standard Cartesian reference
   * @param x X coordinate of the point (no need to be on the arc, as the angle is all that is considered)
   * @param y Y coordinate of the point (no need to be on the arc, as the angle is all that is considered)
   * @return true if the point is on the arc
   */
  private static boolean ptInArcExclEnd(double[] arc, double direction, double x,
                                 double y)
  {
    double angle = Math.atan2(y - arc[1], x - arc[0]);
    if (angle < 0.0)
      angle += 2 * Math.PI;
    if (angle == arc[3])
      return true;
    if (angle == arc[5])
      return false;
    boolean flip = false;
    if (direction > 0.0)
      flip = !flip;
    if (arc[5] > arc[3])
      flip = !flip;
    if (arc[5] > angle && angle > arc[3] ||
        arc[3] > angle && angle > arc[5])
      flip = !flip;
      

    return false;
  }
  /**
   * Determines whether an angle (in radians) lies in an arc
   * @param arc 6 doubles representing the arc (in JGeometry.computeArc format)
   * @param direction +ve = anticlockwise, -ve = clockwise in a standard Cartesian reference
   * @param angle the angle to be tested
   * @return true if the angle lies in the arc
   */
  private static boolean angleInArc(double[] arc, double direction,
                                    double angle)
  {
    if (angle < 0.0)
      angle += 2 * Math.PI;
    if (angle == arc[3] || angle == arc[5])
      return true;
    boolean flip = false;
    if (direction > 0.0)
      flip = !flip;
    if (arc[5] > arc[3])
      flip = !flip;
    if (arc[5] > angle && angle > arc[3] ||
        arc[3] > angle && angle > arc[5])
      flip = !flip;
    return flip;
  }
  public static double getLineLength(double coords[])
  {
    double distSq = (coords[2]-coords[0])*(coords[2]-coords[0]) 
      + (coords[3]-coords[1])*(coords[3]-coords[1]);
    return Math.sqrt(distSq);
  }
  
  /**
   * "Fakes" an MBR from a point
   * @param pt the x,y array of the point
   * @return the MBR of this point (in Oracle RTree format)
   */
  public static double[][] MBRfromPoint(double[] pt)
  {
    double mbrd[][] = new double[2][2];
    mbrd[0][0] = mbrd[0][1] = pt[0];
    mbrd[1][0] = mbrd[1][1] = pt[1];
    return mbrd;
  }
  /**
   * determines if a point lies in an MBR
   * @param mbrd the MBR in Oracle RTree format
   * @param pt the point of interest
   * @return
   */
  public static boolean MBRcontainsPoint(double mbrd[][], double[] pt)
  {
    if (pt[0] < mbrd[0][0] || mbrd[0][1] < pt[0] )
      return false;
    if (pt[1] < mbrd[1][0] || mbrd[1][1] < pt[1] )
      return false;
    return true;
  }
  


    /**
     * define if the poligon is clockwise
     * @param polyOrdinates - double[] polygon ordinates
     * @return yes or no
     */
    public static boolean isClockWise(double polyOrdinates[])
    {
        double d = getPolygonArea(polyOrdinates);
        if(d >= 0.0D){
          return true;
        }else{
            return false;
        }
    }

    /**
     * calculate polygon area
     * @param polyOrdinates - double[] polygon ordinates
     * @return yes or no
     */
    private static double getPolygonArea(double polyOrdinates[])
    {
        if(polyOrdinates == null)
            return 0.0D;
        double d = 0.0D;
        double d1 = 0.0D;
        double d2 = 0.0D;
        double d3 = polyOrdinates[0];
        double d4 = polyOrdinates[1];
        for(int i = 2; i < polyOrdinates.length - 1; i += 2)
        {
            double d5 = polyOrdinates[i];
            double d6 = polyOrdinates[i + 1];
            d += d3 * d6;
            d1 += d4 * d5;
            d3 = d5;
            d4 = d6;
        }

        d2 = (d1 - d) / 2D;
        return d2;
    }
    
    
    
  

	

    
    /**
     * method in charge validating the location feature elements
     * @param firstPt - double[] - first point coordenates
     * @param lastPt - double[] - last point coordenates
     * @param originalOrdinates - double[] - polygon ordenates current coordenates
     * @return - double[] - polygon ordenates the move coordenates
     */
    private static double[] location(double[] firstPt, double[] lastPt, double[] originalOrdinates){
        double x1=0, x2=0, y1=0, y2=0;
        if(firstPt!=null && firstPt.length>=1) { 
            x1 = firstPt[0];
            y1 = firstPt[1];
        }
        if(lastPt!=null && lastPt.length>=1) { 
            x2 = lastPt[0];
            y2 = lastPt[1];
        }        
        double deltaX = getDelta(x1,x2);
        double dentaY = getDelta(y1,y2);
        double[] moveOrdinates = new double[originalOrdinates.length];
        
        for (int count = 0; count < originalOrdinates.length; count+=2)  {
          moveOrdinates[count] = originalOrdinates[count] + deltaX;
          moveOrdinates[count+1] = originalOrdinates[count+1] + dentaY; 
        }
        return moveOrdinates;
    }
    
    /**
     * calcule delta value
     * @param val1 - double ordenate
     * @param val2 - double ordenate
     * @return double - delta value
     */
    private static double getDelta(double val1, double val2){
        return val2-val1;
    }
}

