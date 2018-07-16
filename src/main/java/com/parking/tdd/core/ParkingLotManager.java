package com.parking.tdd.core;

import com.parking.tdd.core.exception.DeleteParkingLotWithCarException;

import java.util.List;

public class ParkingLotManager {
    private List<ParkingLot> lotList;
    private ParkingBoy boy;

    public ParkingLotManager(ParkingBoy boy, List<ParkingLot> lotList) {
        this.boy=boy;
        this.lotList=lotList;
    }

    public String getParkingLotsInfo() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("|停车场ID|名称|车位|已停车辆|剩余车位|\n");
        buffer.append("======================================\n");
        lotList.forEach(lot->{
            String id=lot.getId();
            String name=lot.getName();
            int capacity=lot.getCapacity();
            int carNum=lot.countCarNum();
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
        return lotList.stream().map(lot->lot.countCarNum()).reduce(0,(total,current)->total+current);
    }
    private int countSiteNum() {
        return lotList.stream().map(lot->lot.getCapacity()).reduce(0,(total,current)->total+current);
    }
    public int getParkingLotNum(){
        return lotList.size();
    }

    public void buildParkingLot(String name, int capacity) {
        lotList.add(new ParkingLot(getParkingLotNum(),name,capacity));
    }

    public void deleteParkingLotById(int lodId) throws DeleteParkingLotWithCarException {
        if (countCarNum()>0){
            throw new DeleteParkingLotWithCarException();
        }
        lotList.remove(ParkingLot.getInstance(lodId));

    }

}
