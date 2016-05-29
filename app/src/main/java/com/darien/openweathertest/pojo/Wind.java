
package com.darien.openweathertest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class Wind {

    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private double deg;

    /**
     * 
     * @return
     *     The speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * 
     * @param speed
     *     The speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * 
     * @return
     *     The deg
     */
    public double getDeg() {
        return deg;
    }

    /**
     * 
     * @param deg
     *     The deg
     */
    public void setDeg(double deg) {
        this.deg = deg;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(speed).append(deg).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Wind)) {
            return false;
        }
        Wind rhs = ((Wind) other);
        return new EqualsBuilder().append(speed, rhs.speed).append(deg, rhs.deg).isEquals();
    }

}
