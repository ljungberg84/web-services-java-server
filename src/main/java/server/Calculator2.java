package server;

import api.HTTPModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Calculator2 extends HTTPModule {

    private String answerType;
    double output;

    @Override
    public ResponseObject get(RequestObject request, ResponseObject response){
        System.out.println("i calculator");
        Map<String, String> p = request.getParams();
        char operator = p.get("operator").charAt(0);
        p.remove("operator");
        List<String> nums = new ArrayList<>(p.values());
        calculate(nums, operator);

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<style>\n" +
                "\t@import url(https://fonts.googleapis.com/css?family=Montserrat);");
        htmlBuilder.append("body {\n" +
                "  overflow: hidden;\n" +
                "  margin: 0px;\n" +
                "}\n" +
                "\t\t.background {\n" +
                "\t\t\t  background-size: cover;\n" +
                "\t\t\t  background-repeat: no-repeat;\n" +
                "\t\t\t  background-position: center center;\n" +
                "\t\t\t  overflow: hidden;\n" +
                "\t\t\t  position: fixed;\n" +
                "\t\t\t  width: 100%;\n" +
                "\t\t\t  background-image: url(/files/images/number.jpg);\n" +
                "\n" +
                "}\n" +
                "\n" +
                ".content-wrapper {\n" +
                "  height: 100vh;\n" +
                "  display: flex;\n" +
                "  justify-content: center;\n" +
                "  text-align: center;\n" +
                "  flex-flow: column nowrap;\n" +
                "  color: #fff;\n" +
                "  font-family: Montserrat;\n" +
                "  text-transform: uppercase;\n" +
                "}\n" +
                "\n" +
                ".content-title {\n" +
                "  font-size: 12vh;\n" +
                "  line-height: 1.4;\n" +
                "}\n" +
                "\n" +
                ".calculator {\n" +
                "\tbackground: #292f36;\n" +
                "\tpadding: 10px 20px;\n" +
                "\tmargin: 0 auto;\n" +
                "\tcolor: #5cc8ff;\n" +
                "\tfont-weight: 600;\n" +
                "}\n" +
                "\n" +
                ".output {\n" +
                "\tcolor: #c41c4f;\n" +
                "}");
        htmlBuilder.append("</style>");
        htmlBuilder.append("<div class=\"container\">");
        htmlBuilder.append("<section class=\"background\">");
        htmlBuilder.append("<div class=\"content-wrapper\">");
        htmlBuilder.append(" <h1 class=\"content-title\">Calculator</h1>");


        htmlBuilder.append("<div class=\"calculator\">" + "<p>" + answerType + " output: " + " <span class=\"output\">" + output + "</span></p>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("</div>");
        htmlBuilder.append(" </section>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        response = new ResponseObject();
        response.setData(htmlBuilder.toString().getBytes());
        response.setContentType("text/html");
        response.setContentLength(htmlBuilder.length());

        return response;




    }
//    public ResponseObject handleRequest(String request, Map<String, String> params) {
//
//        //char operator = params.get("operator").charAt(0);
//        //params.remove("operator");
//
//        //List<String> nums = new ArrayList<String>(params.values());
//        //calculate(nums, operator);
//
//        StringBuilder htmlBuilder = new StringBuilder();
//        htmlBuilder.append("<!DOCTYPE html>");
//        htmlBuilder.append("<html>");
//        htmlBuilder.append("<body>");
//        htmlBuilder.append("<h1>Calculator</>");
//        htmlBuilder.append("<h2>"+ answerType + " output: " + output + "</h2>");
//        htmlBuilder.append("</body>");
//        htmlBuilder.append("</html>");
//
//        ResponseObject response = new ResponseObject();
//        response.setData(htmlBuilder.toString().getBytes());
//        response.setContentType("text/html");
//        response.setContentLength(htmlBuilder.length());
//
//        return response;
//    }


    private void calculate(List<String> nums, char operator){
        output = Double.parseDouble(nums.get(0));
        nums.remove(0);

        for (String num: nums) {
            double tempNum;
            try{

                switch (operator) {
                    case '*':
                        System.out.println("multiplication");
                        output = output * Double.parseDouble(num);
                        answerType = "Product";
                        break;
                    case '+':
                        output = output + Double.parseDouble(num);
                        answerType = "Sum";
                        break;
                    case '-':
                        output = output - Double.parseDouble(num);
                        answerType = "Difference";
                        break;

                    default:
                        break;

                }

            }catch (Exception e) {
                System.out.println("error parsing to int: " + e.getMessage());
            }
        }
    }
}