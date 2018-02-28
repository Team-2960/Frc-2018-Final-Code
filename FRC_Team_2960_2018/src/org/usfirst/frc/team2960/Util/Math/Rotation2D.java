package org.usfirst.frc.team2960.Util.Math;
import static com.team254.lib.util.Util.epsilonEquals;

import com.team254.lib.util.Interpolable;

import java.text.DecimalFormat;

/**
 * A rotation in a 2d coordinate frame represented a point on the unit circle (cosine and sine).
 *
 * Inspired by Sophus (https://github.com/strasdat/Sophus/tree/master/sophus)
 */
public class Rotation2D implements Interpolable<Rotation2D> {
    protected static final Rotation2D kIdentity = new Rotation2D();

    public static final Rotation2D identity() {
        return kIdentity;
    }

    protected static final double kEpsilon = 1E-9;

    protected double cos_angle_;
    protected double sin_angle_;

    public Rotation2D() {
        this(1, 0, false);
    }

    public Rotation2D(double x, double y, boolean normalize) {
        cos_angle_ = x;
        sin_angle_ = y;
        if (normalize) {
            normalize();
        }
    }

    public Rotation2D(Rotation2D other) {
        cos_angle_ = other.cos_angle_;
        sin_angle_ = other.sin_angle_;
    }

    public Rotation2D(Translation2D direction, boolean normalize) {
        this(direction.x(), direction.y(), normalize);
    }

    public static Rotation2D fromRadians(double angle_radians) {
        return new Rotation2D(Math.cos(angle_radians), Math.sin(angle_radians), false);
    }

    public static Rotation2D fromDegrees(double angle_degrees) {
        return fromRadians(Math.toRadians(angle_degrees));
    }

    /**
     * From trig, we know that sin^2 + cos^2 == 1, but as we do math on this object we might accumulate rounding errors.
     * Normalizing forces us to re-scale the sin and cos to reset rounding errors.
     */
    public void normalize() {
        double magnitude = Math.hypot(cos_angle_, sin_angle_);
        if (magnitude > kEpsilon) {
            sin_angle_ /= magnitude;
            cos_angle_ /= magnitude;
        } else {
            sin_angle_ = 0;
            cos_angle_ = 1;
        }
    }

    public double cos() {
        return cos_angle_;
    }

    public double sin() {
        return sin_angle_;
    }

    public double tan() {
        if (Math.abs(cos_angle_) < kEpsilon) {
            if (sin_angle_ >= 0.0) {
                return Double.POSITIVE_INFINITY;
            } else {
                return Double.NEGATIVE_INFINITY;
            }
        }
        return sin_angle_ / cos_angle_;
    }

    public double getRadians() {
        return Math.atan2(sin_angle_, cos_angle_);
    }

    public double getDegrees() {
        return Math.toDegrees(getRadians());
    }

    /**
     * We can rotate this Rotation2d by adding together the effects of it and another rotation.
     *
     * @param other
     *            The other rotation. See: https://en.wikipedia.org/wiki/Rotation_matrix
     * @return This rotation rotated by other.
     */
    public Rotation2D rotateBy(Rotation2D other) {
        return new Rotation2D(cos_angle_ * other.cos_angle_ - sin_angle_ * other.sin_angle_,
                cos_angle_ * other.sin_angle_ + sin_angle_ * other.cos_angle_, true);
    }

    public Rotation2D normal() {
        return new Rotation2D(-sin_angle_, cos_angle_, false);
    }

    /**
     * The inverse of a Rotation2d "undoes" the effect of this rotation.
     *
     * @return The opposite of this rotation.
     */
    public Rotation2D inverse() {
        return new Rotation2D(cos_angle_, -sin_angle_, false);
    }

    public boolean isParallel(Rotation2D other) {
        return epsilonEquals(Translation2D.cross(toTranslation(), other.toTranslation()), 0.0, kEpsilon);
    }

    public Translation2D toTranslation() {
        return new Translation2D(cos_angle_, sin_angle_);
    }

    @Override
    public Rotation2D interpolate(Rotation2D other, double x) {
        if (x <= 0) {
            return new Rotation2D(this);
        } else if (x >= 1) {
            return new Rotation2D(other);
        }
        double angle_diff = inverse().rotateBy(other).getRadians();
        return this.rotateBy(Rotation2D.fromRadians(angle_diff * x));
    }

    @Override
    public String toString() {
        final DecimalFormat fmt = new DecimalFormat("#0.000");
        return "(" + fmt.format(getDegrees()) + " deg)";
    }
}
