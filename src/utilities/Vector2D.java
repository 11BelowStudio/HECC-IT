package utilities;

import java.awt.*;
import java.util.Vector;

//le ce218 sample code has arrived (template provided by Dr Dimitri Ognibene)

//methods implemented and class enhanced by me

//will be implemented later on in OH-HECC (mainly for positions of the passages in the overlay)

/**
 * A 2D Cartesian vector.
 * Also somewhat compatible with java.awt.Point.
 *
 * Based on some sample code provided by Dr. Dimitri Ognibene as part of the
 * CE218 Computer Games Programming module I took last year.
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
     * constructor for a vector with same coords as a Point
     *
     * @param p the point that's being turned into a Vector2D
     */
    public Vector2D(Point p) {
        this.x = p.x;
        this.y = p.y;
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
     * Resets x and y to 0
     * @return this vector after being reset
     */
    public Vector2D reset(){
        this.x = 0;
        this.y = 0;
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

    /**
     * String version of this vector.
     * @return a string showing this vector in the form (x, y)
     */
    public String toString() {
        return "("+x + ", "  + y+")";
    }

    /**
     * @return the magnitude (= "length") of this vector
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
     * Signed angle between this vector and another vector in range [-PI,PI]
     * @param other the other vector
     * @return angle between this vector and the other. Signed angle.
     */
    public double angle(Vector2D other) {
        //finding difference between the angles
        double result = other.angle() - this.angle();
        //wrapping the result around if it's outside range [-PI,PI] to keep it in range
        if (result < -Math.PI){
            result += 2*Math.PI;
            //2pi added if it's below -pi
        } else if (result > Math.PI){
            result -= 2* Math.PI;
            //2pi removed if it's above pi
        }
        return result;
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
     * add vectors to each other, without modifying the originals.
     * @param v1 the first argument vector
     * @param v2 the second argument vector
     * @return a copy of v1 with v2 added to it.
     */
    public static Vector2D add(Vector2D v1, Vector2D v2){
        final Vector2D result = new Vector2D(v1);
        return result.add(v2);
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
     * weighted add (adds v but multiplied by the factor)
     * @param v the vector that's being added to this
     * @param fac the 'weight' of the add (1.0: adds v as-is. 0.5: adds half of v. 2.0: adds 2*v)
     * @return this.
     */
    public Vector2D addScaled(Vector2D v, double fac) {
        this.x += (v.x*fac);
        this.y += (v.y*fac);
        return this;
    }
    /**
     * weighted add (v1 + (v2 multiplied by fac))
     * @param v1 the first vector
     * @param v2 the second vector
     * @param fac weight for the second vector
     * @return modified copy of v1
     */
    public static Vector2D addScaled(Vector2D v1, Vector2D v2, double fac){
        final Vector2D result = new Vector2D(v1);
        return result.addScaled(v2,fac);
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

    /**#
     * subtract argument vector (subtracts v1 from v2)
     * @param v1
     * @param v2
     * @return
     */
    public static Vector2D subtract(Vector2D v1, Vector2D v2){
        final Vector2D result = new Vector2D(v1);
        return result.subtract(v2);
    }

    /**
     * subtract values from coordinates (this - new Vector2(x,y))
     * @param x
     * @param y
     * @return
     */
    public Vector2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }
    /**
     * subtract values from coordinates (v - new Vector2(x,y))
     * @param v
     * @param x
     * @param y
     * @return
     */
    public static Vector2D subtract(Vector2D v, double x, double y){
        final Vector2D result = new Vector2D(v);
        return result.subtract(x,y);
    }

    /**
     * multiply x and y by factor
     * @param fac
     * @return
     */
    public Vector2D mult(double fac) {
        this.x = x*fac;
        this.y = y*fac;
        return this;
    }

    /**
     * multiply x by factor
     * @param xFac
     * @return
     */
    public Vector2D multX(double xFac){
        this.x *= xFac;
        return this;
    }

    /**
     * multiply v by factor
     * @param v
     * @param fac
     * @return
     */
    public static Vector2D mult(Vector2D v, double fac){
        final Vector2D result = new Vector2D(v);
        return result.mult(fac);
    }

    /**
     * rotate by angle given in radians
     * (basically scalar rotation)
     * @param angle
     * @return
     */
    public Vector2D rotate(double angle) {
        final double tempX = x;
        final double tempY = y;
        x = (tempX * Math.cos(angle)) - (tempY * Math.sin(angle));
        y = (tempX * Math.sin(angle)) + (tempY * Math.cos(angle));
        return this;
    }

    /**
     * rotate v by angle given in radians
     * (basically scalar rotation)
     * @param v
     * @param angle
     * @return
     */
    public static Vector2D rotate(Vector2D v, double angle){
        final Vector2D result = new Vector2D(v);
        return result.rotate(angle);
    }

    /**
     * "dot product" ("scalar product") with argument vector
     * @param v
     * @return
     */
    public double dot(Vector2D v) { return ((this.x*v.x)+(this.y*v.y)); }

    /**
     * distance to argument vector
     *  Euclidean distance formula (which, for 2d planes, is pretty much pythagoras' theorem)
     * @param v
     * @return
     */
    public double dist(Vector2D v) { return (Math.hypot((x-v.x),(y-v.y))); }

    /**
     * distance to argument vector in x axis
     * @param v
     * @return
     */
    public double xDist(Vector2D v) {
        double result = x - v.x;
        if (result < 0){
            result *= -1;
        }
        return result;
    }

    /**
     * distance to argument vector in x axis, but wrapping around the given wrapBound
     * @param v
     * @param wrapBound
     * @return
     */
    public double xDist(Vector2D v, double wrapBound){
        return (xDist(v) + wrapBound) % wrapBound;
    }

    /**
     * distance to argument vector in y axis
     * @param v
     * @return
     */
    public double yDist(Vector2D v) {
        double result = y - v.y;
        if (result < 0){
            result *= -1;
        }
        return result;
    }
    /**
     * distance to argument vector in y axis, but wrapping around the given wrapBound
     * @param v
     * @param wrapBound
     * @return
     */
    public double yDist(Vector2D v, double wrapBound){
        return (yDist(v) + wrapBound) % wrapBound;
    }

    /**
     * normalise vector so that magnitude becomes 1
     *  (basically divides x and y by mag so mag effectively becomes 1
     *  unless the magnitude is 0 at which case it can't really do anything)
     * @return
     */
    public Vector2D normalise() {
        final double currentMag = this.mag();
        if (currentMag != 0) {
            this.x = x / currentMag;
            this.y = y / currentMag;
        }
        return this;
    }

    /**
     * normalise vector so that magnitude becomes 1
     *  (basically divides x and y by mag so mag effectively becomes 1
     *   unless the magnitude is 0 at which case it can't really do anything)
     * @param v
     * @return
     */
    public static Vector2D normalise(Vector2D v){
        final Vector2D result = new Vector2D(v);
        return result.normalise();
    }

    /**
     * setting magnitude of this vector
     * @param newMag
     * @return
     */
    public Vector2D setMag(double newMag) {
        this.set(polar(this.angle(),newMag));
        return this;
    }

    /**
     * getting a vector thats the same as the  argument but with the new magnitude
     * @param v
     * @param newMag
     * @return
     */
    public static Vector2D setMag(Vector2D v, double newMag){ return polar(v.angle(),newMag); }

    /**
     * wrap-around operation, assumes w> 0 and h>0
     * @param w
     * @param h
     * @return
     */
    public Vector2D wrap(double w, double h) {
        this.wrapX(w);
        this.wrapY(h);
        return this;
    }

    /**
     * wrap-around operation, assumes w> 0 and h>0
     * @param v
     * @param w
     * @param h
     * @return
     */
    public static Vector2D wrap(Vector2D v, double w, double h){
        final Vector2D result = new Vector2D(v);
        return result.wrap(w,h);
    }

    /**
     * wraparound in x axis only
     * @param w
     * @return
     */
    public Vector2D wrapX(double w){
        this.x = (x+w) % w;
        //pretty much gets the remainder of x plus width over width (ensures x is under width)
        return  this;
    }

    /**
     * wraparound in y axis only
     * @param h
     * @return
     */
    public Vector2D wrapY(double h){
        this.y = (y+h) % h;
        //ditto but for y and height instead
        return this;
    }

    /**
     * Limits the magnitude to the specified maxMag
     * @param maxMag
     * @return
     */
    public Vector2D capMag(double maxMag){
        if (maxMag < 0){
            maxMag *= -1;
            //ensuring a positive maximum mag is used
        }
        if (this.mag() > maxMag){
            this.setMag(maxMag);
            //mag is capped at maximum if it's bigger than it
        }
        return this;
    }

    /**
     * Capping the x and y values of this vector2D
     * @param maxX
     * @param maxY
     * @return
     */
    public Vector2D capXY(double maxX, double maxY){
        //capping the X and Y values of a Vector2D
        //arguments must be positive. if x and y are negative, they will be compared against the inverse maxX/maxY.
        capX(maxX);
        capY(maxY);
        return this;
    }

    /**
     * cap x and y values of this vector2D to same value
     * @param maxXY caps x and y
     * @return
     */
    public Vector2D capXY(double maxXY){
        return capXY(maxXY,maxXY);
    }

    /**
     * Capping x axis of vector
     * @param maxX
     * @return
     */
    public Vector2D capX(double maxX){
        //capping the X value of a Vector2D.
        //if x is negative, it's compared to the inverse of maxX.
        if (x > 0){
            if (x > maxX){
                x = maxX;
            }
        } else{
            if (x < -maxX){
                x = -maxX;
            }
        }
        return this;
    }

    /**
     * Capping y axis of vector
     * @param maxY maximum y value
     * @return itself
     */
    public Vector2D capY(double maxY){
        //capping the Y value of a Vector2D.
        //if y is negative, it's compared to the inverse of maxY.
        if (y > 0){
            if (y > maxY){
                y = maxY;
            }
        } else{
            if (y < -maxY){
                y = -maxY;
            }
        }
        return this;
    }

    /**
     * construct vector with given polar coordinates (magnitude + angle)
     * @param angle
     * @param mag
     * @return
     */
    public static Vector2D polar(double angle, double mag) {
        return new Vector2D(mag*Math.cos(angle),mag*Math.sin(angle));
    }

    /**
     * like polar for situations where a random angle is needed instead of a given angle
     * @param mag
     * @return
     */
    public static Vector2D polarWithRandomAngle(double mag){
        return Vector2D.polar(Math.random()*(2*Math.PI),mag);
    }

    /**
     * rotation needed to get from direction this vector is pointing to in order for it to point to targetVector instead
     * @param targetVector
     * @return
     */
    public double getAngleTo(Vector2D targetVector){
        final double xAngle = targetVector.x - x;
        final double yAngle = targetVector.y - y;
        return Math.atan2(yAngle,xAngle);
    }

    /**
     * get vector from this vector's coordinates to the target vector's coordinates
     * @param targetVector
     * @return
     */
    public Vector2D getVectorTo(Vector2D targetVector){ return polar(getAngleTo(targetVector),dist(targetVector)); }

    /**
     * gets vector from this vector to the targetPoint
     * @param targetPoint
     * @return
     */
    public Vector2D getVectorTo(Point targetPoint){ return this.getVectorTo(new Vector2D(targetPoint));}


    /**
     * vector between fromThis co-ordinates to toThis co-ordinates
     * @param fromThis
     * @param toThis
     * @return
     */
    public static Vector2D getVectorTo(Vector2D fromThis, Vector2D toThis){ return fromThis.getVectorTo(toThis); }

    /**
     * gets vector from origin vector to point p
     * @param origin
     * @param p
     * @return
     */
    public static Vector2D getVectorTo(Vector2D origin, Point p){ return origin.getVectorTo(p); }

    /**
     * Angle from this vector to argument vector, but wrapping around given width/height
     * @param v
     * @param w
     * @param h
     * @return
     */
    public double getAngleTo(Vector2D v, double w, double h){
        double xAngle = v.x - x;
        double yAngle = v.y - y;
        final double w2 = w/2;
        final double h2 = h/2;
        if (xAngle > w2){
            xAngle =- w2;
        } else if(xAngle < -w2){
            xAngle += w2;
        }

        if (yAngle > h2){
            yAngle =- h2;
        } else if(yAngle < -h2){
            yAngle += h2;
        }
        return Math.atan2(yAngle,xAngle);

    }

    /**
     * get the vector from this to toThis, wrapping around by w and h
     * @param toThis
     * @param w
     * @param h
     * @return
     */
    public Vector2D getVectorTo(Vector2D toThis, double w, double h){ return polar(getAngleTo(toThis,w,h),toThis.dist(this)); }

    /**
     * get the vector from fromThis to toThis, wrapping around by w and
     * @param fromThis
     * @param toThis
     * @param w
     * @param h
     * @return
     */
    public static Vector2D getVectorTo(Vector2D fromThis, Vector2D toThis, double w, double h){ return fromThis.getVectorTo(toThis,w,h); }



    /**
     * projection of this vector in some direction
     * @param d
     * @return
     */
    public Vector2D proj(Vector2D d){
        final Vector2D result = new Vector2D(d);
        result.mult(this.dot(d));
        return result;
    }

    /**
     * random vector going in some random direction from the origin, with random magnitude in given range
     * @param origin
     * @param minDist
     * @param rangeDist
     * @return
     */
    public static Vector2D randomVectorFromOrigin(Vector2D origin, double minDist, double rangeDist){
        final Vector2D fromOrigin = polar(Math.toRadians(Math.random()*360),(Math.random()*rangeDist)+minDist);
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
     * random vector pointing to pointToThis (at a random angle), with given magnitude
     * @param pointToThis
     * @param mag
     * @return
     */
    public static Vector2D randomVectorPointingTo(Vector2D pointToThis, double mag){
        final Vector2D vectorFromOrigin = polar(Math.toRadians(Math.random()*360),mag);
        vectorFromOrigin.add(pointToThis);
        final double xAngle = pointToThis.x - vectorFromOrigin.x;
        final double yAngle = pointToThis.y - vectorFromOrigin.y;
        return polar(Math.atan2(yAngle,xAngle),mag);
    }

    /**
     * makes a random vector with x between -maxX to maxX and y between -maxY to maxY
     * @param maxX
     * @param maxY
     * @return
     */
    public static Vector2D getRandomVector(double maxX, double maxY){
        return new Vector2D(((Math.random()*2)-1) * maxX,((Math.random()*2)-1) *maxY);
    }

    /**
     * inverts this vector
     */
    public Vector2D inverse(){
        x *= -1;
        y *= -1;
        return this;
    }

    /**
     * inverts X of this vector
     * @return
     */
    public Vector2D invertX(){
        x *= -1;
        return this;
    }

    /**
     * inverts Y of this vector
     * @return
     */
    public Vector2D invertY(){
        y *= -1;
        return this;
    }

    /**
     * Gets inverse of Vector v
     * @param v
     * @return
     */
    public static Vector2D inverse(Vector2D v){
        final Vector2D result = new Vector2D(v);
        return result.inverse();
    }

    /**
     * gets this vector as a point
     * @return
     */
    public Point toPoint(){ return new Point((int)x,(int)y); }



    //and now, collision stuff

    /**
     * A collision vector for a collision between this and the other vector
     * @param other the other vector
     * @return a collision vector for a collision between this and the other vector
     */
    public Vector2D getCollisionVector(Vector2D other){
        return new Vector2D(other).subtract(this).normalise();
    }
    public static Vector2D getCollisionVector(Vector2D v1, Vector2D v2){ return v1.getCollisionVector(v2); }

    @SuppressWarnings("SuspiciousNameCombination")
    public Vector2D getTangent(){ return new Vector2D(-y,x); }
    @SuppressWarnings("SuspiciousNameCombination")
    public static Vector2D getTangent(Vector2D collision){ return new Vector2D(-collision.y, collision.x); }

    public static Vector2D getCollisionVelocity(Vector2D thisV, Vector2D otherV, Vector2D collision, Vector2D tangent){
        return new Vector2D((thisV.proj(tangent)).add(otherV.proj(collision)));
    }

    public Vector2D getCollisionVelocity(Vector2D thisP, Vector2D otherP, Vector2D otherV){
        final Vector2D coll = thisP.getCollisionVector(otherP);
        return this.set((this.proj(coll.getTangent())).add(otherV.proj(coll)));
    }

    /**
     * returns true if x and y are 0
     * @return true if x and y are 0
     */
    public boolean isZero(){
        return (x == 0 && y == 0);
    }


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
     * returns true if x is between minX and maxX (inclusive) and y is within minY and maxY (inclusive)
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     * @return
     */
    public boolean isInBounds(double minX, double maxX, double minY, double maxY){
        return ((x >= minX) && (x <= maxX) && (y >= minY) && (y <= maxY));
    }
}