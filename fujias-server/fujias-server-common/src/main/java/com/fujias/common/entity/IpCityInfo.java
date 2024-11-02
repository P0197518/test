package com.fujias.common.entity;

/**
 * IpCityInfo
 * 
 * @author fujias
 *
 */
public class IpCityInfo {
    private String code;
    private IpCityInfoDetail data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public IpCityInfoDetail getData() {
        return data;
    }

    public void setData(IpCityInfoDetail data) {
        this.data = data;
    }

    /**
     * IpCityInfoDetail
     * 
     * @author fujias
     *
     */
    public class IpCityInfoDetail {
        private String ip;
        private String country;
        private String region;
        private String city;
        private String isp;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }

        public String getCityInfo() {
            return this.country + " " + this.region + " " + this.city;
        }
    }
}
