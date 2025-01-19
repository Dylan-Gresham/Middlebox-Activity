package org.beacon;

import java.util.Random;

public class Reflection {
    private final int ip;
    private int responseSize;
    private int routers;
    private ReflectionEnum attackType;

    public Reflection(int ip, int typeOrdinal) {
        this.ip = ip;
        setTypeProperties(typeOrdinal);
    }

    public ReflectionEnum getReflectionType() {
        return this.attackType;
    }

    public int getResponseSize() {
        return this.responseSize;
    }

    public int getIPAddress() {
        return this.ip;
    }

    private void setTypeProperties(int typeOrdinal) {
        Random rand = new Random();
        switch (typeOrdinal) {
            case 0:
                attackType = ReflectionEnum.destination;
                this.responseSize = 50;
                break;
            case 1:
                attackType = ReflectionEnum.middlebox;
                this.responseSize = rand.nextInt(50) + 100;
                break;
            case 2:
                attackType = ReflectionEnum.destinationMiddlebox;
                this.responseSize = rand.nextInt(100) + 320;
                break;
            case 3:
                attackType = ReflectionEnum.routerLoop;
                this.responseSize = rand.nextInt(500) + 10000;
                break;
            case 4:
                attackType = ReflectionEnum.victimSustained;
                this.responseSize = rand.nextInt(5000) + 100000;
                break;
        }
    }

    //used for middlebox and middlebox + destination reflection so that middlebox + destination reflection is equivolent to middlebox's random
    public void setResponseSize(int type, int rand) {
        //destination + middlebox
        if (type == 2) {
            this.responseSize = rand + 100 + 50;
        }
        //middlebox
        else if (type == 1) {
            this.responseSize = rand + 100;
        }
    }

    public void setRandRouters() {
        Random rand = new Random();
        routers = rand.nextInt(4) + 2;
    }

    public int getRouters() {
        return routers;
    }
}
