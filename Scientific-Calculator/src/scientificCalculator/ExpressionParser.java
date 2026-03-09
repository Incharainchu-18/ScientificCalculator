package scientificCalculator;

public class ExpressionParser {

    public static double evaluate(String expression, double x){

        switch(expression){

            case "x":
                return x;

            case "x*x":
                return x*x;

            case "x*x*x":
                return x*x*x;

            case "Math.sin(x)":
                return Math.sin(x);

            case "Math.cos(x)":
                return Math.cos(x);

            case "Math.tan(x)":
                return Math.tan(x);

            case "Math.log(x)":
                return Math.log(x);

            default:
                return 0;
        }
    }
}