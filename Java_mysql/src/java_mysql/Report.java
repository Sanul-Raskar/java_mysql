/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mysql;

import com.itextpdf.text.Font;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanul
 */
public class Report {

    Connection con = null;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public Report() {
        con = Java_mysql.createConnection();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void generateReport() {
        Document document = new Document();
        PdfPTable my_report_table = new PdfPTable(7);
        PdfPCell table_cell;
        Paragraph preface = new Paragraph();

        String query = "select * from students,branch where students.branch_id = branch.branch_id";

        try {
            PdfWriter.getInstance(document, new FileOutputStream("report.pdf"));
            document.open();

            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Sample PDF Report", catFont));

            addEmptyLine(preface, 1);
            document.add(preface);

            PreparedStatement psmt = con.prepareStatement(query);
            System.out.println(psmt);
            ResultSet rs = psmt.executeQuery();

            table_cell = new PdfPCell(new Phrase("Student ID"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Name"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Email"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Mobile"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Gender"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("branch_id"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Branch Name"));
            my_report_table.addCell(table_cell);

            while (rs.next()) {

                int studid = rs.getInt("stud_id");
                String stud_id = Integer.toString(studid);
                table_cell = new PdfPCell(new Phrase(stud_id));
                my_report_table.addCell(table_cell);

                String name = rs.getString("name");
                table_cell = new PdfPCell(new Phrase(name));
                my_report_table.addCell(table_cell);

                String email = rs.getString("email");
                table_cell = new PdfPCell(new Phrase(email));
                my_report_table.addCell(table_cell);

                String mobile = rs.getString("mobile");
                table_cell = new PdfPCell(new Phrase(mobile));
                my_report_table.addCell(table_cell);

                String gender = rs.getString("gender");
                table_cell = new PdfPCell(new Phrase(gender));
                my_report_table.addCell(table_cell);

                int branchid = rs.getInt("branch_id");
                String branch_id = Integer.toString(branchid);
                table_cell = new PdfPCell(new Phrase(branch_id));
                my_report_table.addCell(table_cell);

                String branch_name = rs.getString("branch_name");
                table_cell = new PdfPCell(new Phrase(branch_name));
                my_report_table.addCell(table_cell);

            }
            document.add(my_report_table);

        } catch (FileNotFoundException | DocumentException | SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }

        document.close();

    }

    public static void main(String[] args) {
        Report obj = new Report();
        obj.generateReport();
    }
}
