package org.usfirst.frc.team2960.Util.Math;
/**
 * A movement along an arc at constant curvature and velocity. We can use ideas from "differential calculus" to create
 * new RigidTransform2d's from a Twist2d and visa versa.
 *
 * A Twist can be used to represent a difference between two poses, a velocity, an acceleration, etc.
 */
public class Twist2D {
    protected static final Twist2D kIdentity = new Twist2D(0.0, 0.0, 0.0);

    public static final Twist2D identity() {
        return kIdentity;
    }

    public final double dx;
    public final double dy;
    public final double dtheta; // Radians!

    public Twist2D(double dx, double dy, double dtheta) {
        this.dx = dx;
        this.dy = dy;
        this.dtheta = dtheta;
    }

    public Twist2D scaled(double scale) {
        return new Twist2D(dx * scale, dy * scale, dtheta * scale);
    }
}
