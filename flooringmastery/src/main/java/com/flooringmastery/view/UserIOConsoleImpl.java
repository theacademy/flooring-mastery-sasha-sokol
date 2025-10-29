package com.flooringmastery.view;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class UserIOConsoleImpl implements UserIO {

    private PrintStream out = System.out;
    private Scanner in;

    public UserIOConsoleImpl() {
        in = new Scanner(System.in);
        in.useDelimiter(System.lineSeparator());
    }

    

    @Override
    public void print(String message) {
        out.println(message);
    }

    @Override
    public String readString(String prompt) {
        out.print(prompt + "  ::  ");
        return in.next();
    }

    @Override
    public int readInt(String prompt) {
        boolean isValid = false;
        int result = -1;

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = Integer.parseInt(in.next());
                isValid = true;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean isValid = false;
        int result = -1;

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = Integer.parseInt(in.next());
                isValid = min <= result && result <= max;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public double readDouble(String prompt) {
        boolean isValid = false;
        double result = -1.0;

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = Double.parseDouble(in.next());
                isValid = true;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean isValid = false;
        double result = -1.0;

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = Double.parseDouble(in.next());
                isValid = min <= result && result <= max;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public float readFloat(String prompt) {
        boolean isValid = false;
        float result = -1;

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = Float.parseFloat(in.next());
                isValid = true;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean isValid = false;
        float result = -1;

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = Float.parseFloat(in.next());
                isValid = min <= result && result <= max;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public long readLong(String prompt) {
        boolean isValid = false;
        long result = -1;

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = Long.parseLong(in.next());
                isValid = true;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean isValid = false;
        long result = -1;

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = Long.parseLong(in.next());
                isValid = min <= result && result <= max;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public BigDecimal readDecimal(String prompt) {
        boolean isValid = false;
        BigDecimal result = new BigDecimal(-1);

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = new BigDecimal(in.next());
                isValid = true;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public BigDecimal readDecimal(String prompt, BigDecimal min, BigDecimal max) {
        boolean isValid = false;
        BigDecimal result = new BigDecimal(-1);

        do { 
            out.print(prompt + "  ::  ");
            try {
                result = new BigDecimal(in.next());
                isValid = result.compareTo(min) >= 0 && result.compareTo(max) <= 0;
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

}
