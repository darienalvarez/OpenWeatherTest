
package com.darien.openweathertest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Sys {

    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private int sunrise;
    @SerializedName("sunset")
    @Expose
    private int sunset;

    /**
     * 
     * @return
     *     The type
     */
    public int getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The message
     */
    public double getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(double message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The sunrise
     */
    public int getSunrise() {
        return sunrise;
    }

    /**
     * 
     * @param sunrise
     *     The sunrise
     */
    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * 
     * @return
     *     The sunset
     */
    public int getSunset() {
        return sunset;
    }

    /**
     * 
     * @param sunset
     *     The sunset
     */
    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(id).append(message).append(country).append(sunrise).append(sunset).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Sys)) {
            return false;
        }
        Sys rhs = ((Sys) other);
        return new EqualsBuilder().append(type, rhs.type).append(id, rhs.id).append(message, rhs.message).append(country, rhs.country).append(sunrise, rhs.sunrise).append(sunset, rhs.sunset).isEquals();
    }

}
