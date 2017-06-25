package com.sun.bean;

import java.util.List;

/**
 * Created by sun on 2017/6/23.
 */

public class GameBetBean {

    /**
     * status : 0
     * data : {"roomId":"13423256","bet":[0,50,0]}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * roomId : 13423256
         * bet : [0,50,0]
         */

        private String roomId;
        private List<Integer> bet;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public List<Integer> getBet() {
            return bet;
        }

        public void setBet(List<Integer> bet) {
            this.bet = bet;
        }
    }
}
