package com.example.rumi.dys_reader;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Rumi on 10/11/2017.
 */

public class PDFSHOWER extends AppCompatActivity {
    PDFDoc pdfDoc;
    ArrayList<PDFDoc> pdfDocs=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shower);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        final GridView gv= (GridView) findViewById(R.id.gv);
        try {
            gv.setAdapter(new CustomAdapter(PDFSHOWER.this,getPDFs()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<PDFDoc> getPDFs() throws FileNotFoundException {

        //TARGET FOLDER
       File downloadsFolder= Environment.getExternalStorageDirectory();
      /*  System.out.println("Path "+downloadsFolder.getAbsolutePath());
        if (!downloadsFolder.exists()) {
            System.out.println("Directory not created");
        }
        System.out.println(downloadsFolder.exists());

        if(downloadsFolder.exists())
        {
            //GET ALL FILES IN DOWNLOAD FOLDER
            File[] files=downloadsFolder.listFiles();

            //LOOP THRU THOSE FILES GETTING NAME AND URI
            for (int i=0;i<files.length;i++)
            {
                File file=files[i];
                if(file.getPath().endsWith("pdf"))
                {
                    pdfDoc=new PDFDoc();
                    pdfDoc.setName(file.getName());
                    pdfDoc.setPath(file.getAbsolutePath());
                    pdfDocs.add(pdfDoc);
                }
            }
        }else{
            ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
            File mydir = contextWrapper.getDir("mydir", Context.MODE_PRIVATE); //Creating an internal dir;
            File fileWithinMyDir = new File(mydir, "myfile"); //Getting a file within the dir.
            FileOutputStream out = new FileOutputStream(fileWithinMyDir);
            Toast.makeText(PDFSHOWER.this,"Error DownloadFolder",Toast.LENGTH_SHORT).show();
        }*/
        Search_Dir(downloadsFolder);
        return pdfDocs;
    }
    public void Search_Dir(File dir) {
        String pdfPattern = ".pdf";

        File FileList[] = dir.listFiles();

        if (FileList != null) {
            for (int i=0;i<FileList.length;i++)
            {
                File file=FileList[i];
                if (FileList[i].isDirectory()) {
                    Search_Dir(FileList[i]);
                }
                else {
                    if(file.getPath().endsWith("pdf"))
                    {
                        pdfDoc=new PDFDoc();
                        pdfDoc.setName(file.getName());
                        pdfDoc.setPath(file.getAbsolutePath());
                        pdfDocs.add(pdfDoc);
                    }
                }
            }
        }
    }
}

