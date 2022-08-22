package com.revature.buyNlarge.models;

public enum Condition {
    NULL,
    DAMAGED{
        @Override
        public String toString(){
            return "Damaged";
        }
    },
    WORN{
        @Override
        public String toString(){
            return "Worn";
        }
    },
    FINE{
        @Override
        public String toString(){
            return "Fine";
        }
    },
    GOOD{
        @Override
        public String toString(){
            return "Good";
        }
    },
    GREAT{
        @Override
        public String toString(){
            return "Great";
        }
    },
    PRISTINE{
        @Override
        public String toString(){
            return "Pristine";
        }
    },
    COUNT
}
