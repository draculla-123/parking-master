package com.gojek.assignment.abstractProcessor;
import com.gojek.assignment.ParkingLot;
import com.gojek.assignment.model.Car;
import com.gojek.assignment.utils.Commands;

public abstract class CommandsProcessor {

    ParkingLot parkingLot;
    Commands command;

    public void validateAndProcess(String inputString) throws Exception {
        String[] inputStrArr = inputString.split(" ");
        if(inputStrArr[0].equals("exit")) {
            System.exit(0);
        }
        command = Commands.commandsName(inputStrArr[0]);

        if(command == null) {
            System.out.println("Empty or Invalid command");
            return;
        }

        switch(command) {
            case CREATE:
                int noOfPrakingSlots = Integer.parseInt(inputStrArr[1]);
                parkingLot = ParkingLot.createParkingLot(noOfPrakingSlots);
                break;
            case PARK:
                String regNo = inputStrArr[1];
                String color = inputStrArr[2];
                parkingLot.parkCar(new Car(regNo, color));
                break;
            case LEAVE:
                int slotNo = Integer.parseInt(inputStrArr[1]);
                parkingLot.freeSlot(slotNo);
                break;
            case STATUS:
                parkingLot.getStatus();
                break;
            case FETCH_CARE_FROM_COLOR:
                parkingLot.getRegistrationNumbersFromColor(inputStrArr[1]);
                break;
            case FETCH_SLOT_FROM_COLOR:
                parkingLot.getSlotNumbersFromColor(inputStrArr[1]);
                break;
            case FETCH_SLOT_FROM_REG_NO:
                parkingLot.getSlotNumberFromRegNo(inputStrArr[1]);
                break;
        }
    }

    public abstract void process() throws Exception;
}