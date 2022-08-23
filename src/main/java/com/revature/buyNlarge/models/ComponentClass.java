package com.revature.buyNlarge.models;

public enum ComponentClass {
    NULL,
    ENGINE{
        @Override
        public String toString(){
            return "Engine";
        }
    },
    HULL{
        @Override
        public String toString(){
            return "Hull";
        }
    },
    WEAPON{
        @Override
        public String toString(){
            return "Weapon";
        }
    },
    AUXILIARY{
        @Override
        public String toString(){
            return "Auxiliary";
        }
    },
    CABIN{
        @Override
        public String toString(){
            return "Cabin";
        }
    },
    BAY{
        @Override
        public String toString(){
            return "Bay";
        }
    },
    MISC{
        @Override
        public String toString(){
            return "Misc";
        }
    },
    COUNT
}
