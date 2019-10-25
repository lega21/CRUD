package com.example.regiones.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class RegionesModel {

        @SerializedName("idregion")
        @Expose
        private int id;

        @SerializedName("region")
        @Expose
        private String region;

        public RegionesModel() {
        }

        public RegionesModel(int id, String region) {
            this.id = id;
            this.region = region;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

    @Override
    public String toString() {
        return "RegionesModel{" +
                "id=" + id +
                ", region='" + region + '\'' +
                '}';
    }
}

