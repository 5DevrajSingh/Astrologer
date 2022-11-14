package com.ksbm_astroexpert.Constant;


/**
 * Created by Sudesh Sr. Android Develoer
 */
public enum BaseEnvironment {


    /*


Your merchant key is: hgApA2Your merchant salt is: BGyiPa0ZYour merchant Id is: 5189212
     */

    PRODUCTION {
        @Override
        public String merchant_Key() {
            return "hgApA2";
        }

        @Override
        public String merchant_ID() {
            return "5189212";
        }

        @Override
        public String furl() {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php";
        }

        @Override
        public String surl() {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php";
        }

        @Override
        public boolean debug() {
            return false;
        }
    };

    public abstract String merchant_Key();

    public abstract String merchant_ID();

    public abstract String furl();

    public abstract String surl();

    public abstract boolean debug();

}
