package com.example.rumi.dys_reader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by Oclemy on 7/28/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<PDFDoc> pdfDocs;

    public CustomAdapter(Context c, ArrayList<PDFDoc> pdfDocs) {
        this.c = c;
        this.pdfDocs = pdfDocs;
    }

    @Override
    public int getCount() {
        return pdfDocs.size();
    }

    @Override
    public Object getItem(int i) {
        return pdfDocs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            //INFLATE CUSTOM LAYOUT
            view= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        final PDFDoc pdfDoc= (PDFDoc) this.getItem(i);

        TextView nameTxt= (TextView) view.findViewById(R.id.textView);
        ImageView img= (ImageView) view.findViewById(R.id.pdfImage);

        //BIND DATA
        System.out.println(pdfDoc.getName());
        nameTxt.setText(pdfDoc.getName());

        img.setImageResource(R.drawable.pdf_icon);
        ////adding pdf thumbnail


       /* FileInputStream is = null;
        try {
            is = new FileInputStream(pdfDoc.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("file input stream error");
        }
        byte[] bytes;
        long length = pdfDoc.getName().length();
        bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        try {
            while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } catch (IOException e) {
            System.out.println("read error");
        }
        //ByteBuffer buffer = ByteBuffer.wrap(bytes);
        ByteBuffer buffer=ByteBuffer.wrap(bytes);
        String data = Base64.encodeToString(bytes, Base64.DEFAULT);
        PDFFile pdf_file = null;
        try {
            pdf_file = new PDFFile(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PDFPage page = pdf_file.getPage(2, true);

        RectF rect = new RectF(0, 0, (int) page.getBBox().width(),
                (int) page.getBBox().height());

        Bitmap image = page.getImage((int)rect.width(), (int)rect.height(), rect);
        //FileOutputStream os = new FileOutputStream("/storage/extSdCard/pdf.jpg");
        image.compress(Bitmap.CompressFormat.JPEG, 80, os);



*/
        //VIEW ITEM CLICK
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openPDFView(pdfDoc.getPath());
            }
        });
        return view;
    }

    //OPEN PDF VIEW
    private void openPDFView(String path)
    {
        Intent i=new Intent(c,PDF_Activity.class);
        i.putExtra("PATH",path);
        c.startActivity(i);
    }
}
