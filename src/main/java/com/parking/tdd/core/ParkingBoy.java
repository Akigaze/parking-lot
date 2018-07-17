package com.parking.tdd.core;

import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.core.exception.NotExitParkingLotException;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList=parkingLotList;
    }

    public ParkingCard park(Car car) {
        for (ParkingLot lot:parkingLotList){
            if (!lot.isFull()){
                return lot.park(car);
            }
        }
        throw new AllParkingLotsFullException();
    }

    public Car unPark(ParkingCard card){
        for (ParkingLot lot:parkingLotList){
            if(lot.containsParkingCard(card)){
                return lot.unpark(card);
            }
        }
        throw new InvalidParkingCardException();
    }
    public boolean isAllParkingLotsFull(){
        for (ParkingLot lot:parkingLotList){
            if (!lot.isFull()){
                return false;
            }
        }
        return true;
    }

    public String getParkingLotsInfo() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("|停车场ID|名称|车位|已停车辆|剩余车位|\n");
        buffer.append("======================================\n");
        parkingLotList.forEach(lot->{
            String id=lot.getId();
            String name=lot.getName();
            int capacity=lot.getCapacity();
            int carNum=lot.getCarNum();
            int restSites=capacity-carNum;
            String item=String.format("|%s|%s|%d(个)|%d(辆)|%d(个)|\n",id,name,capacity,carNum,restSites);
            buffer.append(item);
        });
        buffer.append("\n");
        buffer.append(String.format("总车位：%d(个)\n",countSiteNum()));
        buffer.append(String.format("停车总量：%d（辆）\n",countCarNum()));
        buffer.append(String.format("总剩余车位：%d（个）",countSiteNum()-countCarNum()));

        return buffer.toString();
    }

    private int countCarNum() {
        return parkingLotList.stream().map(lot->lot.getCarNum()).reduce(0,(total,current)->total+current);
    }
    private int countSiteNum() {
        return parkingLotList.stream().map(lot->lot.getCapacity()).reduce(0,(total,current)->total+current);
    }

    public void buildParkingLot(String name, int capacity) {
        parkingLotList.add(new ParkingLot(parkingLotList.size()+1,name,capacity));
    }

    public boolean deleteParkingLot(ParkingLot lot) {
        if (!parkingLotList.contains(lot)){
            throw new NotExitParkingLotException();
        }else {
            if (!lot.hasCar()){
                return parkingLotList.remove(lot);
            }
            return false;
        }
    }
}
