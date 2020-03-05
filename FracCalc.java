
public class FracCalc {
    public static void main(String[] args) 
    {
        // TODO: Call produceAnswer with an equation
        //String input = ("");
        //System.out.println(produceAnswer(input));
        //System.out.println(produceOperand(input));
    }

    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        //find spaces
        int indexSpace1 = input.indexOf(" ");
        String frac1 = input.substring(0, indexSpace1);
        String operator = input.substring(indexSpace1 + 1, indexSpace1 + 2);
        String frac2 = input.substring(indexSpace1 + 3);

        int whole1 = Integer.parseInt (produceWhole (frac1)); //parsing whole # string from frac 1 to int
        int num1 = Integer.parseInt (produceNum (frac1)); //parsing num string from frac 1 to int
        int denom1 = Integer.parseInt (produceDenom (frac1));  //parsing denom string from frac 1 to int

        int whole2 = Integer.parseInt (produceWhole (frac2));
        int num2 = Integer.parseInt (produceNum (frac2));
        int denom2 = Integer.parseInt (produceDenom (frac2));

        //calculations
        int numeratorAnswer = 0;
        int denominatorAnswer = 0;
        String sum = "";
        int lcm = 0;
        int impropernum1 = 0;
        int impropernum2 = 0;
        int remain = 0;
        int wholesum = 0;
        int gcd = 0;
        int numProduct = 0;
        int denomProduct = 0;
        if (frac1.indexOf("/") != -1 ){
            if (frac1.indexOf("_") != -1) {
                if (frac1.indexOf("-") != -1) {
                    impropernum1 = whole1*denom1- num1;
                }
                else {
                    impropernum1 = whole1*denom1 + num1;
                }
            }
            else {
                impropernum1 = num1;
            }
        }
        else {
            impropernum1 = whole1; //num1
        }

        if (frac2.indexOf("/") != -1 ){ // if there is a fraction
            if (frac2.indexOf("_") != -1) {
                if (frac2.indexOf("-") != -1) {
                    impropernum2 = whole2 *denom2 - num2;
                }
                else {
                    impropernum2 = whole2*denom2 + num2;
                }
            }
            else {
                impropernum2 = num2;
            }
        }
        else { //no fraction
            impropernum2 = whole2; //num2
        }        

        if (operator.equals("+")) {
            if (denom1 == denom2) {
                numeratorAnswer = impropernum1 + impropernum2;
                if (Math.abs(numeratorAnswer) > denom1 && denom1 != 1) {
                    wholesum = numeratorAnswer / denom1;
                    remain = numeratorAnswer % denom1;
                    sum = wholesum + "_" + Math.abs(remain) + "/" + denom1;
                }
                else if (Math.abs(numeratorAnswer) < denom1 ) {
                    gcd = gcd(numeratorAnswer, denom1);
                    sum = numeratorAnswer/gcd + "/" +denom1/gcd;
                }              
                else if (denom1 == 1 && denom2 == 1) {
                    sum = Integer.toString(numeratorAnswer);
                }
                else if (numeratorAnswer == denom1) {
                    sum = "1";
                }

                else {
                    sum = numeratorAnswer + "/" + denom1;
                }
            }
            else if (denom1 != denom2) {
                lcm = lcm(denom1, denom2);
                gcd = gcd(denom1, denom2);
                numeratorAnswer = impropernum1 * (lcm/denom1) + impropernum2 * (lcm/denom2);
                if (numeratorAnswer == lcm) {
                    sum = "1";
                }
                else if (Math.abs(numeratorAnswer) > lcm) {
                    wholesum = numeratorAnswer / lcm;
                    remain = numeratorAnswer % lcm;

                    if (lcm % numeratorAnswer != 0) {
                        gcd = gcd(numeratorAnswer, lcm);
                        sum = wholesum + "_" + Math.abs(remain/gcd) + "/" + Math.abs(lcm/gcd);
                    }
                    else {
                        gcd = gcd(numeratorAnswer, lcm);
                        sum = wholesum + "_" + Math.abs(remain/gcd) + "/" + Math.abs(lcm/gcd);                                                
                    }
                }
                else if (Math.abs(numeratorAnswer) < lcm) {
                    if (lcm % numeratorAnswer != 0) {
                        gcd = gcd(numeratorAnswer, denom1);
                        sum = wholesum + "_" + Math.abs(remain/gcd) + "/" + Math.abs(lcm/gcd);
                    }
                    else {
                        sum = numeratorAnswer + "/" + lcm;                        
                    }
                }
            }
        }

        if (operator.equals("-")) { 
            lcm = lcm (denom1, denom2); 
            int impro1 = impropernum1 * (lcm/denom1);
            int impro2 = impropernum2 * (lcm/denom2);

            numeratorAnswer = (impro1 - impro2);
            if (numeratorAnswer == 0) {
                sum = Integer.toString(0);
            }
            else if (numeratorAnswer == denom1) {
                sum = "1";
            }
            else if (denom1 == 1 && denom2 == 1) {
                sum = Integer.toString(numeratorAnswer);
            }            
            else if (denom1 != denom2) {             
                if (Math.abs(numeratorAnswer) > lcm) {
                    remain = (Math.abs(numeratorAnswer) % lcm);
                    wholesum = (numeratorAnswer / lcm);
                    sum = wholesum + "_" + remain + "/" + lcm ;
                }
                else {
                    sum = numeratorAnswer + "/" + lcm;
                }
            }
            else if (denom1 == denom2) {
                if (Math.abs(numeratorAnswer) > lcm) {
                    remain = (Math.abs(numeratorAnswer) % lcm);
                    wholesum = (numeratorAnswer / lcm);
                    sum = wholesum + "_" + remain + "/" + lcm; 
                }
                else if (Math.abs(numeratorAnswer) < lcm) {
                    gcd = gcd (numeratorAnswer, lcm);
                    sum = numeratorAnswer/gcd + "/" + lcm/gcd;
                }
                else {
                    numeratorAnswer = impropernum1 + impropernum2;
                    sum = Integer.toString(numeratorAnswer) + "/" + denom1; 
                }
            }
        }

        if (operator.equals("*")) {
            if (impropernum1 == 0 || impropernum2 == 0) { //if no fraction part
                sum = Integer.toString(whole1 * whole2);
            }
            else {
                numProduct = impropernum1 * impropernum2;
                denomProduct = denom1 * denom2;
                if (numProduct == denomProduct) {
                    sum = "1";
                }
                else if (Math.abs(numProduct) > denomProduct) {
                    remain = (Math.abs(numProduct) % denomProduct);
                    wholesum = numProduct / denomProduct;
                    gcd = gcd(numProduct, denomProduct);

                    if (numProduct % denomProduct == 0) { // if denom can be divided by num
                        sum = Integer.toString(numProduct / denomProduct);
                    }  
                    else if (gcd > 1 && numProduct % denomProduct != 0) {//if they have a common factor
                        sum = wholesum + "_" + (remain/gcd) + "/" + (denomProduct/gcd);
                        //return "true";
                    }
                    else { //if denomProduct / numProduct = whole
                        if (remain < denomProduct && remain != 0) { 
                            gcd = gcd(remain, denomProduct);
                            if (gcd > 1) {
                                sum = wholesum + "_" + remain/gcd + "/" + denomProduct/gcd;
                                //return "true";
                            }
                            else {
                                sum = wholesum + "_" + remain + "/" + denomProduct;                                
                            }
                        }
                        else if (remain == 0) {
                            sum = Integer.toString(wholesum);
                        }
                    }
                }
                else if (numProduct < denomProduct) {
                    gcd = gcd(numProduct, denomProduct);
                    sum = (numProduct/gcd) + "/" + (denomProduct/gcd);
                }
            }
        }

        if (operator.equals("/")) { // multiply by inverse: denom/num
            numeratorAnswer = impropernum1 * denom2;
            denominatorAnswer = impropernum2 * denom1;
            remain = numeratorAnswer % denominatorAnswer;
            wholesum = numeratorAnswer / denominatorAnswer;
            gcd = gcd(numeratorAnswer, denominatorAnswer);
            if (impropernum1 == 0 || numeratorAnswer == 0) {
                sum = "0";
            }
            else if (impropernum2 == 0) {
                sum = "undefined";
            }
            else if (Math.abs(denominatorAnswer) == 1) {
                if (denominatorAnswer == 1 ) {
                    sum = Integer.toString(numeratorAnswer);
                }
                else if (denominatorAnswer == -1) {
                    sum = Integer.toString(numeratorAnswer * -1);
                }
            }
            else {
                if (Math.abs(numeratorAnswer) > Math.abs(denominatorAnswer) && denominatorAnswer != 1) {
                    if (gcd > 1){
                        if (remain == 0 ) {
                            sum = Integer.toString(wholesum);
                        }
                        sum = wholesum + "_" + Math.abs(remain/gcd) + "/" + Math.abs(denominatorAnswer/gcd);
                    }
                    else {
                        gcd = gcd(remain, denominatorAnswer);
                        sum = wholesum + "_" + Math.abs(remain/gcd) + "/" + Math.abs(denominatorAnswer/gcd);
                    }
                }
                else if (Math.abs(numeratorAnswer) == Math.abs(denominatorAnswer)) {
                    sum = "1";
                }
                else {
                    if (remain == 0 ) {
                        sum = Integer.toString(wholesum);
                    }
                    else {
                        sum = numeratorAnswer/gcd + "/" + Math.abs(denominatorAnswer/gcd);                         
                    }
                }
            }
        }
        //return Integer.toString(remain);
        return sum;
    }
    // TODO: Fill in the space below with any helper methods that you think you will need
    public static String produceOperand (String operand) {
        String whole;
        String num;
        String denom;

        int indexUnderscore = operand.indexOf("_");
        int indexDivision = operand.indexOf("/");
        if (indexUnderscore == -1) { //if there's no underscore so no whole number
            if (indexDivision == -1) { //if there's no fraction at all
                whole = operand.substring (0); //whole is just the entire operand
                num = "0";
                denom = "1";
            }
            else { //if there is a fraction
                whole = "0"; //whole is not existant
                num = operand.substring (0, indexDivision);
                denom = operand.substring (indexDivision + 1);
            }
        }
        else { //if there is an underscore
            whole = operand.substring (0, indexUnderscore); //whole is number before underscore
            num = operand.substring (indexUnderscore + 1, indexDivision);
            denom = operand.substring (indexDivision + 1);
        }

        return("w" + whole + "n" + num + "d" +denom);
    }

    public static String produceWhole (String input) { // separating whole string
        String produceWhole = produceOperand(input);
        int indexWhole = produceWhole.indexOf ("w");
        int indexNum = produceWhole.indexOf("n");
        String whole = produceWhole.substring (indexWhole + 1, indexNum);

        return whole;
    }

    public static String produceNum (String input) { // separating numerator string
        String produceNum = produceOperand(input);
        int indexNum = produceNum.indexOf("n");
        int indexDenom = produceNum.indexOf("d");
        String num = produceNum.substring (indexNum + 1, indexDenom);

        return num;
    }

    public static String produceDenom (String input) { //separating denominator string
        String produceDenom = produceOperand(input);
        int indexDenom = produceDenom.indexOf("d");
        String denom = produceDenom.substring (indexDenom + 1);

        return denom;
    }

    public static int lcm(int a, int b) 
    { 
        return (a*b)/gcd(a, b); 
    } 

    public static int gcd(int p, int q) {   
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
}
