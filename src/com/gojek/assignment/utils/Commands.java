package com.gojek.assignment.utils;

public enum Commands {
		CREATE("create_parking_lot"),
	    PARK("park"),
	    LEAVE("leave"),
	    STATUS("status"),
	    FETCH_CARE_FROM_COLOR("registration_numbers_for_cars_with_colour"),
	    FETCH_SLOT_FROM_COLOR("slot_numbers_for_cars_with_colour"),
	    FETCH_SLOT_FROM_REG_NO("slot_number_for_registration_number");

	    private final String name;

	    Commands(String s) {
	        name = s;
	    }

	    public static Commands commandsName(String commands){
	        for(Commands c : values()){
	            if( c.toString().equals(commands)){
	                return c;
	            }
	        }
	        return null;
	    }

	    public String toString() {
	        return name;
	    }
}
