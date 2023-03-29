import javax.print.DocFlavor;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BigInt {
    private ArrayList<Character> _number = new ArrayList<Character>();

    public BigInt(ArrayList<Character> number) {
        _number = number;
    }

    public BigInt() {
        _number = null;
    }

    public BigInt(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (i == 0 && (number.charAt(i) == '+' || number.charAt(i) == '-')) {
                _number.add(number.charAt(i));
            } else if (i == 0) {
                //throw exception
            } else {
                if (number.charAt(i) >= '0' && number.charAt(i) <= '9') {
                    _number.add(number.charAt(i));
                } else {
                    //throw exception
                }
            }
        }
    }

    public BigInt plus(BigInt other)
    {
        ArrayList<Character> number = new ArrayList<Character>();
        int len = _number.size() - 1;
        int len2 = other._number.size() - 1;
        int c1;
        int c2;
        int sum;
        int rest = 0;
        if (_number.get(0) != '-' && other._number.get(0) == '-')
        {
            other._number.set(0, '+');
            return this.minus(other);
        }
        else if (_number.get(0) == '-' && other._number.get(0) != '-')
        {
            _number.set(0, '+');
            return other.minus(this);
        }
        if (len == len2)
        {
            for (int i = len;i>0;i--)
            {
                c1 = Character.getNumericValue(_number.get(i));
                c2 = Character.getNumericValue(other._number.get(i));
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
            number.add(_number.get(0));
        }
        else if (len < len2) {
            for (int i = len; i > 0; i--) {
                c1 = Character.getNumericValue(_number.get(i));
                c2 = Character.getNumericValue(other._number.get(len2));
                sum = c1 + c2 + rest;
                if (sum > 9) {
                    rest = 1;
                    number.add((char) ((sum - 10) + '0'));
                } else {
                    rest = 0;
                    number.add((char) (sum + '0'));
                }
                len2--;
            }
            for (int j = len2; j > 0; j--) {
                sum = Character.getNumericValue(other._number.get(j)) + rest;
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
            number.add(_number.get(0));
        }
         else {
            for (int i = len2; i > 0; i--) {
                c1 = Character.getNumericValue(_number.get(len));
                c2 = Character.getNumericValue(other._number.get(i));
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
                sum = Character.getNumericValue(_number.get(j)) + rest;
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
            number.add(_number.get(0));
        }
        Collections.reverse(number);
        BigInt newBigInt = new BigInt(number);
        return newBigInt;
    }

    public BigInt minus(BigInt other)
    {
        ArrayList<Character> number = new ArrayList<Character>();
        int len = _number.size()-1;
        int len2 = other._number.size()-1;
        int sum;
        boolean negativ = false;
        int c1;
        int c2;
        int c3;
        int bigger = this.compareTo(other);
        int saveLen;
        if (other._number.get(0) == '-' && _number.get(0) == '+') {//send to plus
        other._number.set(0,'+');
        return this.plus(other);
        }
        else if (other._number.get(0) == '+' && _number.get(0) == '-')
        {
            other._number.set(0,'-');
            return this.plus(other);
        }
        else if (other._number.get(0) == '+' && _number.get(0) == '+') {
            if (bigger == 1) {
                for (int i = len2; i > 0; i--) {
                    c1 = Character.getNumericValue(_number.get(len));
                    c2 = Character.getNumericValue(other._number.get(i));
                    if (c1 < c2) {
                        saveLen = len;
                        while (_number.get(saveLen - 1) == '0' && saveLen - 1 > 0) {
                            _number.set(saveLen - 1, '9');
                            saveLen--;
                        }
                        c3 = Character.getNumericValue(_number.get(saveLen - 1)) - 1;
                        _number.set(saveLen - 1, (char) (c3 + '0'));
                        c1 += 10;
                    }
                    sum = c1 - c2;
                    number.add((char) (sum + '0'));
                    len--;
                }
                for (int i = len; i > 0; i--) {
                    number.add(_number.get(i));
                }
                number.add(_number.get(0));
            }
            else if (bigger == -1) {
                for (int i = len; i > 0; i--) {
                    c1 = Character.getNumericValue(_number.get(i));
                    c2 = Character.getNumericValue(other._number.get(len2));
                    if (c1 > c2) {
                        saveLen = len2;
                        while (other._number.get(saveLen - 1) == '0' && saveLen - 1 > 0) {
                            other._number.set(saveLen - 1, '9');
                            saveLen--;
                        }
                        c3 = Character.getNumericValue(other._number.get(saveLen - 1)) - 1;
                        other._number.set(saveLen - 1, (char) (c3 + '0'));
                        c2 += 10;
                    }
                    sum = c2 - c1;
                    number.add((char) (sum + '0'));
                    len2--;
                }
                for (int i = len2; i > 0; i--) {
                    number.add(other._number.get(i));
                }
                number.add(other._number.get(0));
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
                other._number.set(0,'+');
                _number.set(0,'+');
                return this.minus(other);
            }
            else if (bigger == -1)//return to the minus3 class and hange everthing to plus
            {
                for (int i = len2; i > 0; i--) {
                    c1 = Character.getNumericValue(_number.get(len));
                    c2 = Character.getNumericValue(other._number.get(i));
                    if (c1 < c2) {
                        saveLen = len;
                        while (_number.get(saveLen - 1) == '0' && saveLen - 1 > 0) {
                            _number.set(saveLen - 1, '9');
                            saveLen--;
                        }
                        c3 = Character.getNumericValue(_number.get(saveLen - 1)) - 1;
                        _number.set(saveLen - 1, (char) (c3 + '0'));
                        c1 += 10;
                    }
                    sum = c1 - c2;
                    number.add((char) (sum + '0'));
                    len--;
                }
                for (int i = len; i > 0; i--) {
                    number.add(_number.get(i));
                }
                number.add(_number.get(0));
            }
            else
            {number.add('0');
                number.add('+');}

        }
        Collections.reverse(number);
        BigInt newBigInt = new BigInt(number);
        return newBigInt;
    }

    public BigInt multiply(BigInt other)
    {
        ArrayList<Character> number = new ArrayList<Character>();
        ArrayList<Character> sum = new ArrayList<Character>();
        sum.add('+');
        sum.add('0');
        int len = _number.size()-1;
        int len2 = other._number.size()-1;
        int tenJumps = 10;
        int unit;
        int tens;
        int num1;
        int num2;
        int countZero1 = 0;
        int countZero2 = 0;
        BigInt newBigInt = new BigInt(sum);
        BigInt otherNewBigInt = new BigInt(number);
        if (len>=len2)
        {
            for (int i = len2;i>0;i--)
            {
                number.add('+');
                for (int c = countZero1;c>0;c--)
                {
                    number.add('0');
                }
                for (int j=len;j>0;j--)
                {
                    for (int c = countZero2;c>0;c--)
                    {
                        number.add('0');
                    }
                    num1 = Character.getNumericValue(_number.get(j));
                    num2 = Character.getNumericValue(other._number.get(len2));
                    unit = num1%10;
                    tens = num2/10;
                    number.add((char) (unit + '0'));
                    number.add((char) (tens + '0'));
                    Collections.reverse(number);
                    countZero2++;
                }
                newBigInt.plus(otherNewBigInt);
                number.clear();
                countZero2++;
            }



        }
        return  newBigInt;
    }
    public boolean equals(BigInt other)
    {
        if ((other instanceof BigInt )&& this.compareTo(other) == 0)
            return true;
        else
            return false;
    }

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

    public String toString()
    {
        System.out.println(_number);
        return "";
    }

}