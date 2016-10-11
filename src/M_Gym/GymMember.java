/*
 This class is the default object to be used in this application 
 */
package M_Gym;

import java.util.*;
import java.text.*;

/**
 * 
 * @author Michael Liu & Maria Hoang
 */

public class GymMember {

    private int id;
    private String name;
    private String address;
    private String accType;
    private double balance;
    private String sDate;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private Date today = new Date();

    /**
     *
     * @param id
     * @param name
     * @param address
     * @param accType
     * @param balance
     * @param sDate
     */
    public GymMember(int id, String name, String address, String accType, double balance, String sDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.accType = accType;
        this.balance = balance;
        this.sDate = sDate;
    }

    //Getters
    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAccType() {
        return accType;
    }

    public double getBalance() {
        return balance;
    }

    public String getsDate() {
        return sDate;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    /**
     *
     * @return balance and see if it is overdue
     */
    public String overdue() {
        if (dateFormat.format(today).compareTo(sDate) < 0) {
            return "Overdue";
        } else {
            return "Paid";
        }
    }

    /**
     * 
     * @return the member record
     */
    @Override
    public String toString() {
        String info = String.format("%5s%20s%20s%12s%14s%16s\n",
                id, name, address, accType, balance, sDate, this.overdue());
        return info;
    }
}
