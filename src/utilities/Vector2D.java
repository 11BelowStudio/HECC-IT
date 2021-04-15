package utilities;

import java.awt.*;

/**
 * A 2D Cartesian vector. <br>
 * Also somewhat compatible with java.awt.Point.
 * <br><br>
 * Based on some sample code provided by Dr. Dimitri Ognibene as part of the
 * CE218 Computer Games Programming module I took last year.
 * <br><br>
 * This version of the Vector2D has been signficantly trimmed down for sake of performance and
 * the sanity of everyone reading the documentation.
 * <br><br>
 * The pre-trim version can be seen here:
 * <a href="https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/blob/2023c8cc0d74d6c3f30b769a469a4f97061fe317/src/utilities/Vector2D.java">
 *     https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/blob/2023c8cc0d74d6c3f30b769a469a4f97061fe317/src/utilities/Vector2D.java
 *     </a>
 *
 * @author Rachel Lowe
 */
public final class Vector2D {

    /**
     * x position of the vector.
     */
    public double x;

    /**
     * y position of vector.
     */
    public double y;

    /**
     * constructor for zero vector
     */
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * constructor for vector with given coordinates
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor for vector with given int coordinates
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * constructor that copies the argument vector
     * @param v the argument vector.
     */
    public Vector2D(Vector2D v) {
        final double tempX = v.x;
        final double tempY = v.y;
        this.x = tempX;
        this.y = tempY;
    }

    /**
     * Creates a Vector2D from a dimension
     * @param d the dimension that we're turning into a Vector2D (width = x, height = y)
     */
    public Vector2D(Dimension d){
        this.x = d.getWidth();
        this.y = d.getHeight();
    }

    /**
     * sets the coordinates of this vector
     * @param x new x pos
     * @param y new y pos
     * @return this vector
     */
    public Vector2D set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * set coordinates based on argument vector
     * @param v the vector to copy.
     * @return this
     */
    public Vector2D set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    /**
     * set coordinates based on argument point
     * @param p the point to copy.
     * @return this
     */
    public Vector2D set(Point p){
        this.x = p.x;
        this.y = p.y;
        return this;
    }



    /**
     * String version of this vector.
     * @return a string showing this vector in the form (x, y)
     */
    public String toString() {
        return "("+x + ", "  + y+")";
    }

    /**
     * obtains the magnitude of this vector
     * @return the magnitude (= "length" = "hypotenuse") of this vector
     */
    public double mag() {
        return (Math.hypot(x,y));
        //hypotenuse of triangle basically
    }

    /**
     * angle between vector and horizontal axis (polar x=1,y=0) in radians in range [-PI,PI]
     * @return angle from horizontal in radians.
     */
    public double angle() {
        return Math.atan2(y,x);
    }

    /**
     * add argument vector
     * @param v other vector to add to this one.
     * @return this vector.
     */
    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }


    /**
     * add coordinate values to vector
     * @param x x value to add to vector.
     * @param y y value to add to vector
     * @return this modified vector.
     */
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * add coordinate values to vector
     * @param v the vector that's having the stuff added to a copy of it
     * @param x x value to add to vector
     * @param y y value to add to vector
     * @return a copy of v but with (x,y) added to it
     */
    public static Vector2D add(Vector2D v, double x, double y){
        final Vector2D result = new Vector2D(v);
        return result.add(x,y);
    }


    /**
     * subtract argument vector (subtracts that vector from this)
     * @param v the other vector
     * @return this minus v
     */
    public Vector2D subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * subtract argument vector (subtracts v1 from v2) but not modifying original
     * @param v1 the left hand side (will be copied)
     * @param v2 the right hand side
     * @return (copy of v1) - v2
     */
    public static Vector2D subtract(Vector2D v1, Vector2D v2){
        final Vector2D result = new Vector2D(v1);
        return result.subtract(v2);
    }



    /**
     * multiply x and y by factor
     * @param fac what to multiply x and y by
     * @return this but scaled by fac
     */
    public Vector2D mult(double fac) {
        this.x = x*fac;
        this.y = y*fac;
        return this;
    }




    /**
     * construct vector with given polar coordinates (magnitude + angle)
     * @param angle the angle of the vector (radians)
     * @param mag the magnitude of the vector
     * @return that polar vector
     */
    public static Vector2D polar(double angle, double mag) {
        return new Vector2D(mag*Math.cos(angle),mag*Math.sin(angle));
    }



    /**
     * random vector which has been offset from the origin in a random direction by a random distance
     * @param origin the origin vector that this vector is offset from
     * @param minDist the minimum magnitude for this random vector
     * @param rangeDist the range of magnitudes for this random vector
     * @return that random vector
     */
    public static Vector2D randomVectorFromOrigin(Vector2D origin, double minDist, double rangeDist){
        final Vector2D fromOrigin = getRandomPolarVector(minDist, rangeDist);
        fromOrigin.add(origin);
        return fromOrigin;
    }

    /**
     * Random polar vector in some arbitrary length at an arbitrary angle from (0,0)
     * @param minDist minimum length for this vector
     * @param rangeDist range of lengths
     * @return this random polar vector
     */
    public static Vector2D getRandomPolarVector(double minDist, double rangeDist){
        return polar(Math.toRadians(Math.random()*360),(Math.random()*rangeDist)+minDist);
    }


    /**
     * inverts this vector
     * @return this vector (which has been inverted)
     */
    public Vector2D inverse(){
        x *= -1;
        y *= -1;
        return this;
    }



    /**
     * returns true if x and y are 0
     * @return true if x and y are 0
     */
    public boolean isZero(){ return (x == 0 && y == 0); }


    /**
     * Given two vectors (max xy and min xy), this method will ensure that this vector is within those bounds.
     * @param minimumXY the lower bounds of x and y
     * @param maximumXY the upper bounds of x and y
     * @return this vector, but in the bounds of the given minimumXY and maximumXY
     */
    public Vector2D ensureThisIsInBounds(Vector2D minimumXY, Vector2D maximumXY){

        if(x > maximumXY.x){
            x = maximumXY.x;
        } else if (x < minimumXY.x){
            x = minimumXY.x;
        }

        if (y > maximumXY.y){
            y = maximumXY.y;
        } else if (y < minimumXY.y){
            y = minimumXY.y;
        }

        return this;
    }

    /**
     * compare for equality (note Object type argument)
     * @param o the other object
     * @return true if the other object is a Vector2D which has x and y equal to this Vector2D
     */
    public boolean equals(Object o) {
        if (o instanceof Vector2D){
            //if the other object is a Vector2D, compares x and y of this Vector2D and that Vector2D
            final Vector2D v =(Vector2D) o;
            return (this.x == v.x && this.y == v.y);
        }
        return false;

    }



}