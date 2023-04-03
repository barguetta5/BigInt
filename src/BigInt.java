import javax.print.DocFlavor;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/*
* in that class we can made huge numbers and use them with a lot of operators
* */
public class BigInt {
    private ArrayList<Character> _number = new ArrayList<Character>();
    /*
     * At this constructor we set Big number with Array list  * */
    public BigInt(ArrayList<Character> number) {
        _number = number;
    }
    /*
     * At this constructor we set Big number another big number  * */
    public BigInt(BigInt other) {
         ArrayList<Character> number = new ArrayList<Character>();
         for (int i = 0;i<other._number.size();i++)
             number.add(other._number.get(i));

        _number = number;

    }
    /*
     * At this constructor we set Big number with String  * */
    public BigInt(String number) {
        boolean use = false;
        try {
                int x = Integer.parseInt(number);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("the string not a number ");
        }
        for (int i = 0; i < number.length(); i++) {

            if (i==0&&(number.charAt(i) != '+' && number.charAt(i) != '-')&&!use) {
                _number.add('+');
                use = true;
            }
            else if (i == 0 && (number.charAt(i) == '+' || number.charAt(i) == '-')) {
                _number.add(number.charAt(i));
                continue;
            }
            _number.add(number.charAt(i));
        }
    }
    /*
     * In this method I wanted to do plus but first check who is the bigger number   * */
    public BigInt plus(BigInt other)
    {

        if (this.compareTo(other)==-1)
            return other.plusOp(this);

        else if (this.compareTo(other)== 1)
            return this.plusOp(other);

        else
            return this.plusOp(other);
    }
    /*
     * In this method I did the plus action
     * * */

    public BigInt plusOp(BigInt other)
    {
        BigInt copy1 = new BigInt(this);
        BigInt copy2 = new BigInt(other);
        ArrayList<Character> number = new ArrayList<Character>();
        int len = copy1._number.size() - 1;
        int len2 = copy2._number.size() - 1;
        int c1;
        int c2;
        int sum;
        int rest = 0;
        if (copy1._number.get(0) != '-' && copy2._number.get(0) == '-')
        {
            copy2._number.set(0, '+');
            return copy1.minus(copy2);
        }
        else if (copy1._number.get(0) == '-' && copy2._number.get(0) != '-')
        {
            copy1._number.set(0, '+');
            return copy2.minus(copy1);
        }
        if (len == len2)
        {
            for (int i = len;i>0;i--)
            {
                c1 = Character.getNumericValue(copy1._number.get(i));
                c2 = Character.getNumericValue(copy2._number.get(i));
                sum = c1+c2+rest;
                if(sum>9)
                {
                    number.add((char)((sum-10)+'0'));
                    rest = 1;
                }
                else
                {
                    number.add((char)((sum)+'0'));
                    rest = 0;
                }
            }
            if (rest==1)
                number.add('1');
            number.add(copy1._number.get(0));
        }
        else {
            for (int i = len2; i > 0; i--) {
                c1 = Character.getNumericValue(copy1._number.get(len));
                c2 = Character.getNumericValue(copy2._number.get(i));
                sum = c1 + c2 + rest;
                if (sum > 9) {
                    rest = 1;
                    number.add((char) ((sum - 10) + '0'));
                } else {
                    rest = 0;
                    number.add((char) (sum + '0'));
                }
                len--;
            }
            for (int j = len; j > 0; j--) {
                sum = Character.getNumericValue(copy1._number.get(j)) + rest;
                if (sum > 9) {
                    rest = 1;
                    number.add((char) ((sum - 10) + '0'));
                } else {
                    rest = 0;
                    number.add((char) (sum + '0'));
                }

            }
            if (rest==1)
                number.add('1');
            number.add(copy1._number.get(0));
        }
        Collections.reverse(number);
        BigInt newBigInt = new BigInt(number);
        return newBigInt;
    }
    /*
     * In this method I did the minus action
     * * */
    public BigInt minus(BigInt other)
    {
        ArrayList<Character> number = new ArrayList<Character>();
        BigInt copy1 = new BigInt(this);
        BigInt copy2 = new BigInt(other);
        int len = copy1._number.size()-1;
        int len2 = copy2._number.size()-1;
        int sum;
        boolean negativ = false;
        int c1;
        int c2;
        int c3;
        int bigger = copy1.compareTo(copy2);
        int saveLen;
        if (copy2._number.get(0) == '-' && copy1._number.get(0) == '+') {//send to plus
            copy2._number.set(0,'+');
        return copy1.plus(copy2);
        }
        else if (copy2._number.get(0) == '+' && copy1._number.get(0) == '-')
        {
            copy2._number.set(0,'-');
            return copy1.plus(copy2);
        }
        else if (copy2._number.get(0) == '+' && copy1._number.get(0) == '+') {
            if (bigger == 1) {
                for (int i = len2; i > 0; i--) {
                    c1 = Character.getNumericValue(copy1._number.get(len));
                    c2 = Character.getNumericValue(copy2._number.get(i));
                    if (c1 < c2) {
                        saveLen = len;
                        while (copy1._number.get(saveLen - 1) == '0' && saveLen - 1 > 0) {
                            copy1._number.set(saveLen - 1, '9');
                            saveLen--;
                        }
                        c3 = Character.getNumericValue(copy1._number.get(saveLen - 1)) - 1;
                        copy1._number.set(saveLen - 1, (char) (c3 + '0'));
                        c1 += 10;
                    }
                    sum = c1 - c2;
                    number.add((char) (sum + '0'));
                    len--;
                }
                for (int i = len; i > 0; i--) {
                    number.add(copy1._number.get(i));
                }
                number.add(copy1._number.get(0));
            }
            else if (bigger == -1) {
                for (int i = len; i > 0; i--) {
                    c1 = Character.getNumericValue(copy1._number.get(i));
                    c2 = Character.getNumericValue(copy2._number.get(len2));
                    if (c1 > c2) {
                        saveLen = len2;
                        while (copy2._number.get(saveLen - 1) == '0' && saveLen - 1 > 0) {
                            copy2._number.set(saveLen - 1, '9');
                            saveLen--;
                        }
                        c3 = Character.getNumericValue(copy2._number.get(saveLen - 1)) - 1;
                        copy2._number.set(saveLen - 1, (char) (c3 + '0'));
                        c2 += 10;
                    }
                    sum = c2 - c1;
                    number.add((char) (sum + '0'));
                    len2--;
                }
                for (int i = len2; i > 0; i--) {
                    number.add(copy2._number.get(i));
                }
                number.add(copy2._number.get(0));
            }
            else
            {number.add('0');
                number.add('+');
                }
        }
        else
        {
            if (bigger == 1)//small plus normal calculat
            {
                copy2._number.set(0,'+');
                copy1._number.set(0,'+');
                return this.minus(other);
            }
            else if (bigger == -1)//return to the minus3 class and hange everthing to plus
            {
                for (int i = len2; i > 0; i--) {
                    c1 = Character.getNumericValue(copy1._number.get(len));
                    c2 = Character.getNumericValue(copy2._number.get(i));
                    if (c1 < c2) {
                        saveLen = len;
                        while (copy1._number.get(saveLen - 1) == '0' && saveLen - 1 > 0) {
                            copy1._number.set(saveLen - 1, '9');
                            saveLen--;
                        }
                        c3 = Character.getNumericValue(copy1._number.get(saveLen - 1)) - 1;
                        copy1._number.set(saveLen - 1, (char) (c3 + '0'));
                        c1 += 10;
                    }
                    sum = c1 - c2;
                    number.add((char) (sum + '0'));
                    len--;
                }
                for (int i = len; i > 0; i--) {
                    number.add(copy1._number.get(i));
                }
                number.add(copy1._number.get(0));
            }
            else
            {number.add('0');
                number.add('+');}

        }
        Collections.reverse(number);
        BigInt newBigInt = new BigInt(number);
        return newBigInt;
    }
    /*
     * In this method I did the multiplication action
     * * */
    public BigInt multiply(BigInt other)
    {
        BigInt copy1 = new BigInt(this);
        BigInt copy2 = new BigInt(other);
        ArrayList<Character> number = new ArrayList<Character>();
        ArrayList<Character> sum = new ArrayList<Character>();
        sum.add('+');
        sum.add('0');
        int len = copy1._number.size()-1;
        int len2 = copy2._number.size()-1;
        int unit;
        int tens;
        int countZero1 = 0;
        int countZero2 = 0;
        int result;
        BigInt newBigInt = new BigInt(sum);
        BigInt otherNewBigInt = new BigInt(number);
        if (len>=len2)
        {
            for (int i = len2;i>0;i--)
            {
                countZero2 = countZero1;
                for (int c = countZero1;c>0;c--)//adding zeros to the first multy number
                {
                    number.add('0');
                }
                for (int j=len;j>0;j--)
                {
                    for (int c = countZero2;c>0&&j<len2;c--)
                    {
                        number.add('0');
                    }
                    result = Character.getNumericValue(copy1._number.get(j)) *
                            Character.getNumericValue(copy2._number.get(i));
                    unit = result%10;
                    tens = result/10;
                    if (result>9)
                        number.add((char) (tens + '0'));
                    number.add((char) (unit + '0'));
                    number.add('+');
                    Collections.reverse(number);

                    newBigInt = newBigInt.plus(otherNewBigInt);
                    number.clear();
                    countZero2++;
                }
                countZero1++;
            }
            if (copy1._number.get(0)!= copy2._number.get(0))
            {
                newBigInt._number.set(0,'-');
            }
        }
        return  newBigInt;
    }
    /*
     * In this method I did the divide action
     * * */
    public BigInt divide(BigInt other)
    {
        BigInt copy1 = new BigInt(this);
        BigInt copy2 = new BigInt(other);
        ArrayList<Character> number = new ArrayList<Character>();
        ArrayList<Character> result = new ArrayList<Character>();
        number.add('+');
        number.add('0');
        int count = 0;
        String num;
        BigInt newBigInt = new BigInt(other);
        int n;
        boolean isMinus = false;
        if (copy1._number.get(0)!= copy2._number.get(0)) {
            isMinus = true;
        }
        copy1._number.set(0,'+');
        copy2._number.set(0,'+');
            try
        {
            String numberString = "";
            for (int i = 0;i<copy2._number.size();i++)
                numberString+=copy2._number.get(i);
            n = Integer.parseInt(numberString);
            int check = 10/n;
        }
        catch (ArithmeticException e){
            System.out.println("can not divide by zero");
        }
        while (this.compareTo(newBigInt) == 1||this.compareTo(newBigInt) == 0)
        {
            newBigInt = other.plus(newBigInt);
            count++;

        }
        num = Integer.toString(count);

        for (int i = num.length()-1;i>=0;i--)
        {
            result.add(num.charAt(i));
        }
        if (isMinus)
            result.add('-');
        else
            result.add('+');

        Collections.reverse(result);
        newBigInt._number = result;
        return  newBigInt;

    }
    /*
     * In this method I checked if the objects are the same
     * * */
    public boolean equals(BigInt other)
    {
        if ((other instanceof BigInt )&& this.compareTo(other) == 0)
            return true;
        else
            return false;
    }
    /*
     * In this method I find out the what is the ration between two parameters
     * * */
    public int compareTo(BigInt other)
    {
        int len1 = _number.size();
        int len2 = other._number.size();

        if (_number.get(0)=='+'&&other._number.get(0)=='+')
        {
            if (len1>len2)
                return 1;
            else if (len1<len2)
                return -1;
            else
            {
                for (int i = 1;i<len1;i++)
                {
                    if (_number.get(i)>other._number.get(i))
                    {
                        return 1;
                    }
                    else if (_number.get(i)<other._number.get(i))
                    {
                        return -1;
                    }
                }
                return 0;
            }
        }
        if (_number.get(0)=='-'&&other._number.get(0)=='-')
        {
            if (len1>len2)
                return -1;
            else if (len1<len2)
                return 1;
            else
            {
                for (int i = 1;i<len1;i++)
                {
                    if (_number.get(i)>other._number.get(i))
                    {
                        return -1;
                    }
                    else if (_number.get(i)<other._number.get(i))
                    {
                        return 1;
                    }
                }
                return 0;
            }
        }
        else if (_number.get(0)=='-'&&other._number.get(0)=='+')
        {
            return -1;
        }
        else return 1;

    }
    /*
     *Print method
     * * */
    public String toString()
    {
        System.out.println(_number);
        return "";
    }

}
