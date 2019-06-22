package com.gojek.assignment;

import com.gojek.assignment.model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private int totalSlots = 0;

    private List<Integer> availableSlots;

    private Map<Integer, Car> carSlot;

    private Map<String, Integer> registrationNoCarSlot;

    private Map<String, List<String>> colorCarMap;

    private static ParkingLot parkingLot;

    private ParkingLot(int totalSlots) {
        this.totalSlots = totalSlots;
        availableSlots = new ArrayList<Integer>();

        for (int i = 1; i <= totalSlots; i++) {
            availableSlots.add(i);
        }

        carSlot = new HashMap<Integer, Car>();
        registrationNoCarSlot = new HashMap<String, Integer>();
        colorCarMap = new HashMap<String, List<String>>();
        System.out.println("Created parking lot with " + totalSlots + " slots");
    }

    public static ParkingLot createParkingLot(int totalSlots) {
        if(parkingLot != null) {
            return parkingLot;
        } else {
            parkingLot = new ParkingLot(totalSlots);
            return parkingLot;
        }

    }

    public void parkCar(Car car) {
        if (totalSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
            return;
        } else if (carSlot.size() == totalSlots) {
            System.out.println("Sorry, parking lot is full");
            return;
        } else {
            Collections.sort(availableSlots);
            int slot = availableSlots.get(0);
            carSlot.put(slot, car);
            registrationNoCarSlot.put(car.getRegistrationNo(), slot);
            if (colorCarMap.containsKey(car.getColor())) {
                List<String> regNoList = colorCarMap.get(car.getColor());
                colorCarMap.remove(car.getColor());
                regNoList.add(car.getRegistrationNo());
                colorCarMap.put(car.getColor(), regNoList);
            } else {
                LinkedList<String> regNoList = new LinkedList<String>();
                regNoList.add(car.getRegistrationNo());
                colorCarMap.put(car.getColor(), regNoList);
            }
            System.out.println("Allocated slot number: " + slot);
            availableSlots.remove(0);
        }
    }

    public void freeSlot(Integer slotNo) {
        if (totalSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (carSlot.size() > 0) {
            Car freeCarSlot = carSlot.get(slotNo);
            if (freeCarSlot != null) {
                carSlot.remove(slotNo);
                registrationNoCarSlot.remove(freeCarSlot.getRegistrationNo());
                List<String> regNoList = colorCarMap.get(freeCarSlot.getColor());
                if (regNoList.contains(freeCarSlot.getRegistrationNo())) {
                    regNoList.remove(freeCarSlot.getRegistrationNo());
                }
                availableSlots.add(slotNo);
                System.out.println("Slot number " + slotNo + " is free");
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }

    public void getStatus() {
        if (totalSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (carSlot.size() > 0) {
            System.out.println("Slot No.\t\tRegistration No.\t\tColor");
            Car car;
            for (int i = 1; i <= totalSlots; i++) {
                if (carSlot.containsKey(i)) {
                    car = carSlot.get(i);
                    System.out.println(i + "\t \t \t " + car.getRegistrationNo() + "\t \t \t" + car.getColor());
                }
            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }

    public void getRegistrationNumbersFromColor(String color) {
        if (totalSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (colorCarMap.containsKey(color)) {
            List<String> regNoList = colorCarMap.get(color);
            for (int i = 0; i < regNoList.size(); i++) {
                if (!(i == regNoList.size() - 1)) {
                    System.out.print(regNoList.get(i) + " ,");
                } else {
                    System.out.print(regNoList.get(i));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found");
        }
    }
    

    
    public void getSlotNumberFromRegNo(String regNo) {
        if (totalSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (registrationNoCarSlot.containsKey(regNo)) {
            System.out.println(registrationNoCarSlot.get(regNo));
        } else {
            System.out.println("Not found");
        }
    }

    public void getSlotNumbersFromColor(String color) {
        if (totalSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (colorCarMap.containsKey(color)) {
            List<String> regNoList = colorCarMap.get(color);
            List<Integer> slotList = new ArrayList<Integer>();
            for (int i = 0; i < regNoList.size(); i++) {
                slotList.add(registrationNoCarSlot.get(regNoList.get(i)));
            }
            Collections.sort(slotList);
            for (int j = 0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + " ,");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found");
        }
    }

}