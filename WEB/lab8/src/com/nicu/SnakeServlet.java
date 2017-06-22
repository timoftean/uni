package com.nicu;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
/**
 * Created by Nicu on 30/05/2017.
 */

public class SnakeServlet extends HttpServlet {

    public void writeSnake(PrintWriter printWriter,Time time) {
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
        printWriter.println("<script src=\"lab9.js\"></script>");
        printWriter.println("<link rel=\"stylesheet\" href=\"style.css\">");
        printWriter.println("</head>");
        printWriter.println("<body>");
        printWriter.println("<div class=\"container\">");
        printWriter.println("<header class=\"title\">");
        printWriter.println("<h2>Snake Game</h2>");
        printWriter.println("<h4 id=\"score time\">start time: "+time+" </h3>");
        printWriter.println("</header>");
        printWriter.println("</div>");
        printWriter.println("<div class=\"container\">");
        printWriter.println("<section class=\"overlay\">");
        printWriter.println(" <div class=\"gameOverGrid\">");
        printWriter.println("  <h3 id=\"gameOver\">You lose!</h3>");
        printWriter.println("</div>");
        printWriter.println("<button class=\"gameOverGrid btn\">Play</button>");
        printWriter.println(" </section>");
        printWriter.println("<section id=\"gameBoard\"></section>");
        printWriter.println("</div>");
        printWriter.println("</body>");
        printWriter.println("</html>\n");

    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        Database db = new Database();
        db.connect();
        db.setStartTime();
        System.out.println("GET");
        writeSnake(res.getWriter(),db.getStartTime());
    }
    public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Database db = new Database();
        db.connect();
        db.setEndTime();
        res.getWriter().println(db.getTime());

    }
}

