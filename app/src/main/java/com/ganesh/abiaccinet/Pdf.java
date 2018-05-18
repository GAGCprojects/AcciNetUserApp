package com.ganesh.abiaccinet;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by User on 30-03-2018.
 */

public class Pdf {
    String id, category, landmark, desc, nationalHighway, location, email, status, date, time, areaT, accTypeT, vehicleT, roadTypeT, constructionT;
    Context context;
    public Pdf(Context context,String id, String category, String landmark, String desc, String nationalHighway, String location, String email, String status, String date, String time, String areaT, String accTypeT, String vehicleT, String roadTypeT, String constructionT) {
        this.id = id;
        this.category = category;
        this.landmark = landmark;
        this.desc = desc;
        this.nationalHighway = nationalHighway;
        this.location = location;
        this.email = email;
        this.status = status;
        this.date = date;
        this.time = time;
        this.areaT = areaT;
        this.accTypeT = accTypeT;
        this.vehicleT = vehicleT;
        this.roadTypeT = roadTypeT;
        this.constructionT = constructionT;
        this.context = context;
    }

    public void createPdf() {
        String data = "ACCIDENT\n"+"\nLandmark : "+landmark+"\nDescription : "+desc+"\nNational Highway : "+nationalHighway+"\nLocation : "+location+"\nEmail : "+email+"\nStatus : "+status+"\nDate :"+date+"\nTime : "+time+"\nArea Type :"+areaT+"\n Accident Type : "+accTypeT+"\nVehicle Type : "+vehicleT+"\nRoad Type : "+roadTypeT+"\n Construction : "+constructionT;
     //   String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Myapp/";
       String path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String extstoragedir = Environment.getExternalStorageDirectory().toString()+"/Myapp";

        try {
            String fl = path+"/myapplication.pdf";
            File file= new File(fl);
            file.createNewFile();
//            FileOutputStream fOut = new FileOutputStream(file);
//            Log.e("DATA","PDF_CREATED");
//
//            PdfDocument document = new PdfDocument();
//            PdfDocument.PageInfo pageInfo = new
//                    PdfDocument.PageInfo.Builder(500, 500, 1).create();
//            PdfDocument.Page page = document.startPage(pageInfo);
//            Canvas canvas = page.getCanvas();
//            Paint paint = new Paint();
//            canvas.drawText(data, 0, 0, paint);
//
//            document.finishPage(page);
//            document.writeTo(fOut);
//            document.close();
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            //file = Environment.getExternalStorageDirectory().getPath() + "/Hello.pdf"
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
            Paragraph p = new Paragraph(data);
            document.add(p);
            document.close();
            viewPdf();
        }catch (Exception e){
Log.e("ERROR",e.getMessage());
        }
    }

    private void viewPdf() {
        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + "Myapp" + "/" + "myapplication.pdf");
        Uri path = Uri.fromFile(pdfFile);

        // Setting the intent for pdf reader
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast.makeText(context,"inView",Toast.LENGTH_LONG).show();
        try {
            context.startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Can't read pdf file", Toast.LENGTH_SHORT).show();
        }
    }
}