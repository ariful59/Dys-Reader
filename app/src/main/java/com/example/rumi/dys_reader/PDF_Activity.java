package com.example.rumi.dys_reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.ScrollBar;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;

/**
 * Created by Rumi on 10/17/2017.
 */

public class PDF_Activity extends AppCompatActivity implements View.OnClickListener, OnPageChangeListener {


    String path="";
    int page_no=-1;
    PDFView pdfView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        //PDFVIEW SHALL DISPLAY OUR PDFS
        pdfView= (PDFView) findViewById(R.id.pdfView);
        //SCROLLBAR TO ENABLE SCROLLING
        ScrollBar scrollBar = (ScrollBar) findViewById(R.id.scrollBar);
        pdfView.setScrollBar(scrollBar);
        //VERTICAL SCROLLING
        scrollBar.setHorizontal(false);
        //SACRIFICE MEMORY FOR QUALITY
        //pdfView.useBestQuality(true)

        //UNPACK OUR DATA FROM INTENT
        Intent i=this.getIntent();
        path=i.getExtras().getString("PATH");

        //GET THE PDF FILE
        final File file=new File(path);

        if(file.canRead())
        {
            //LOAD IT

           pdfView.fromFile(file).defaultPage(1).onPageChange(this).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(PDF_Activity.this, String.valueOf(nbPages), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(PDF_Activity.this, file.getName(), Toast.LENGTH_LONG).show();

                }
            })
            .load();


        }
        TextView sweep=(TextView)findViewById(R.id.sweep);
        sweep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sweep) {
            Intent intent = new Intent(this, Temp.class);

            //Toast.makeText(PDF_Activity.this,"page No "+page_no, Toast.LENGTH_LONG).show();
            intent.putExtra("message",page_no+" " + path);
            startActivity(intent);

        }

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        page_no=page;
    }
}
