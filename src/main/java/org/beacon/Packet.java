package org.beacon;

public class Packet {
    private String httpHeader;
    private final int MIN_SIZE = 160;
    private int declaredTTL;
    private int numFlags;
    private int bitSize;
    private int padSize;
    private int optionalSize;
    private boolean isURG, isACK, isPSH, isRST, isSYN, isFIN;
    private final String sourceIP;
    private String destinationIP;

    //constructors
    public Packet() {
        this.httpHeader = "www.example.com";
        this.declaredTTL = 255;
        this.isURG = this.isACK = this.isPSH = this.isSYN = this.isFIN = false;
        this.isRST = true;
        this.numFlags = 1;
        this.optionalSize = 0;
        this.bitSize = MIN_SIZE;
        this.updatePadSize();
        this.sourceIP = "192.168.1.32";
        this.destinationIP = "183.76.15.0";
    }

    public int getMaxDataSize() {
        int MAX_SIZE = 4288;
        return MAX_SIZE - this.MIN_SIZE - this.optionalSize - this.padSize;
    }

    public void setOptionalSize(int optionalSize) {
        if (optionalSize < 0 || optionalSize > 40) {
            System.out.println("Packet: Invalid optional size declared. Optional size unchanged.");
        } else {
            this.optionalSize = optionalSize;
            updatePadSize();
            this.bitSize = this.MIN_SIZE + this.padSize + optionalSize;
        }
    }

    private void updatePadSize() {
        this.padSize = (this.MIN_SIZE + this.optionalSize) % 32;
    }

    public void toggleFlag(int flag) {
        if (flag > 5 || flag < 0) {
            System.out.println("Packet: Invalid flag selection.");
            return;
        }

        switch (flag) {
            case (0):
                this.isURG = !this.isURG;
                if (this.isURG) {
                    this.numFlags++;
                } else {
                    this.numFlags--;
                }
                System.out.println("URG flag: " + this.isURG);
                break;
            case (1):
                this.isACK = !this.isACK;
                if (this.isACK) {
                    this.numFlags++;
                } else {
                    this.numFlags--;
                }
                System.out.println("ACK flag: " + this.isACK);
                break;
            case (2):
                this.isPSH = !this.isPSH;
                if (this.isPSH) {
                    this.numFlags++;
                } else {
                    this.numFlags--;
                }
                System.out.println("PSH flag: " + this.isPSH);
                break;
            case (3):
                this.isRST = !this.isRST;
                if (this.isRST) {
                    this.numFlags++;
                } else {
                    this.numFlags--;
                }
                System.out.println("RST flag: " + this.isRST);
                break;
            case (4):
                this.isSYN = !this.isSYN;
                if (this.isSYN) {
                    this.numFlags++;
                } else {
                    this.numFlags--;
                }
                System.out.println("SYN flag: " + this.isSYN);
                break;
            case (5):
                this.isFIN = !this.isFIN;
                if (this.isFIN) {
                    this.numFlags++;
                } else {
                    this.numFlags--;
                }
                System.out.println("FIN flag: " + this.isFIN);
                break;
        }
    }

    public String toString() {
        String retVal = "TTL: " + this.declaredTTL
                + " | Source: " + this.sourceIP
                + " | HTTP Header: " + this.httpHeader
                + " | Destination: " + this.destinationIP
                + " | Size: " + this.bitSize
                + " | Flags: " + this.numFlags + " [ ";

        if (isURG) {
            retVal += "URG ";
        }
        if (isSYN) {
            retVal += "SYN ";
        }
        if (isPSH) {
            retVal += "PSH ";
        }
        if (isACK) {
            retVal += "ACK ";
        }
        if (isRST) {
            retVal += "RST ";
        }
        if (isFIN) {
            retVal += "FIN ";
        }
        retVal += "]";
        return retVal;
    }

    //getters
    public void setHeader(String newHeader) {
        this.httpHeader = newHeader;
    }

    public String getHTTPHeader() {
        return this.httpHeader;
    }

    public int getTTL() {
        return this.declaredTTL;
    }

    //flag booleans
    public boolean getURG() {
        return this.isURG;
    }

    public boolean getACK() {
        return this.isACK;
    }

    public boolean getPSH() {
        return this.isPSH;
    }

    public boolean getRST() {
        return this.isRST;
    }

    public boolean getSYN() {
        return this.isSYN;
    }

    public boolean getFIN() {
        return this.isFIN;
    }

    public String getFlagStatus() {
        String retVal = "[ ";
        if (isURG) {
            retVal += "URG(" + getURG() + ")";
        }
        if (isSYN) {
            retVal += "SYN(" + getSYN() + ")";
        }
        if (isPSH) {
            retVal += "PSH(" + getPSH() + ")";
        }
        if (isACK) {
            retVal += "ACK(" + getACK() + ")";
        }
        if (isRST) {
            retVal += "RST(" + getRST() + ")";
        }
        if (isFIN) {
            retVal += "FIN(" + getFIN() + ")";
        }
        retVal += " ]";
        return retVal;
    }

    public void setTTL(int declaredTTL) {
        this.declaredTTL = declaredTTL;
    }

    public void setDestinationIP(String destinationIP) {
        this.destinationIP = "183.76.15." + destinationIP;
    }
}
