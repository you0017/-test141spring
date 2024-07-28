package com.yc.bean1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.util.Objects;

public class Address {
    private static Logger log = Logger.getLogger(Address.class);
    private String province;
    private String city;
    private String district;

    public Address() {
    }

    public Address(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        log.info("province:"+province);
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        log.info("city:"+city);
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        log.info("district:"+district);
        this.district = district;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(province, address.province) && Objects.equals(city, address.city) && Objects.equals(district, address.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, district);
    }
}
