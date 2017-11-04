package com.example.rumi.dys_reader;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Rumi on 10/19/2017.
 */

public class Temp extends AppCompatActivity implements View.OnClickListener{

    String cutLine[];
    TextView textView,line;
    int i=0,j=1;
    String parsedText="";
    PdfReader reader;
    int color_count=1;
    int font_count=1;
    int back_count=1;
    TextToSpeech tts;
    int page_no;
    String full_line[];
    int line_number=0;
    int color_position=0;
    boolean flag=false;
    boolean flag1=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        String path = getIntent().getStringExtra("message");
        Scanner sc=new Scanner(path);
        if(sc.hasNextInt())
        {
            page_no=sc.nextInt();
            j=page_no;

        }
        path=sc.next();
        //Toast.makeText(Temp.this,"page no "+path, Toast.LENGTH_SHORT).show();
        textView=(TextView)findViewById(R.id.textView);
        ImageButton sweep=(ImageButton) findViewById(R.id.sweep);
        ImageButton back=(ImageButton) findViewById(R.id.back);
        ImageButton forword=(ImageButton) findViewById(R.id.forword);
        TextView color=(TextView)findViewById(R.id.color);
        Button background=(Button) findViewById(R.id.background);
        Button font=(Button) findViewById(R.id.font);
        line=(TextView)findViewById(R.id.Line);

        sweep.setOnClickListener(this);
        color.setOnClickListener(this);
        background.setOnClickListener(this);
        font.setOnClickListener(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);//In your xml file, main xml layout android:id="@+id/layout"
        layout.setOnClickListener(this);
        back.setOnClickListener(this);
        forword.setOnClickListener(this);
        line.setOnClickListener(this);


       // Toast.makeText(Temp.this,"hello "+path, Toast.LENGTH_LONG).show();
       // System.out.println("??????? Fuck"+ path);
        try {

            reader = new PdfReader(path);
            int n = reader.getNumberOfPages();
            parsedText   =  PdfTextExtractor.getTextFromPage(reader, j).trim()+"\n";
            String copy=parsedText;
           // cutLine=parsedText.split("\n");
            full_line=copy.split("\n");
            line.setText(full_line[line_number]);
            cutLine=full_line[line_number].split(" ");
            textView.setText(cutLine[i++]);

            Spannable spanText = Spannable.Factory.getInstance().newSpannable(full_line[line_number]);
            spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0, cutLine[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            line.setText(spanText);
           // Toast.makeText(Temp.this, full_line[0], Toast.LENGTH_LONG).show();
            //Toast.makeText(Temp.this, parsedText, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layout ||v.getId() == R.id.forword) {
            if(line_number>=full_line.length){
                j++;
                try {
                    parsedText = PdfTextExtractor.getTextFromPage(reader, j).trim() + "\n";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                i=0;
                full_line=parsedText.split("\n");
                line.setText("");
                line_number=0;
                line.setText(full_line[line_number]);
                cutLine=full_line[line_number].split(" ");
                textView.setText(cutLine[i++]);
                Spannable spanText = Spannable.Factory.getInstance().newSpannable(full_line[line_number]);
                spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0, cutLine[i-1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                line.setText(spanText);
                color_position=0;
            }
            else if(cutLine.length>i)
            {
                if(flag1){
                    i++;
                    flag1=false;
                }
                textView.setText("");
                textView.setText(cutLine[i]);
                color_position+=cutLine[i-1].length()+1;
                Spannable spanText = Spannable.Factory.getInstance().newSpannable(full_line[line_number]);
                spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), color_position, color_position+cutLine[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                line.setText(spanText);
                flag=true;
                flag1=false;
                if(cutLine.length>i)i++;
            }else
            {
                flag=false;
                i=0;
                if(line_number<full_line.length)line_number++;
                if(line_number==full_line.length){
                    Toast.makeText(Temp.this,"The End", Toast.LENGTH_SHORT).show();
                    line_number--;
                }
                line.setText("");
                line.setText(full_line[line_number]);
                cutLine=full_line[line_number].split(" ");
                textView.setText("");
                textView.setText(cutLine[i++]);
                Spannable spanText = Spannable.Factory.getInstance().newSpannable(full_line[line_number]);
                spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0, cutLine[i-1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                line.setText(spanText);
                color_position=0;
            }


            /*if (cutLine.length > i) {
                textView.setText("");
                textView.setText(cutLine[i++]);
            } else {
                j++;
                try {
                    parsedText = PdfTextExtractor.getTextFromPage(reader, j).trim() + "\n";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cutLine = parsedText.split(" |\n");
                textView.setText("");
                textView.setText(cutLine[0]);
                i = 1;
            }*/
        }
        else if (v.getId() == R.id.sweep) {
           /* if (cutLine.length > i) {
                textView.setText("");
                textView.setText(cutLine[i++]);
            } else {
                j++;
                try {
                    parsedText = PdfTextExtractor.getTextFromPage(reader, j).trim() + "\n";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cutLine = parsedText.split(" |\n");
                textView.setText("");
                textView.setText(cutLine[0]);
                i = 1;
            }*/
            tts=new TextToSpeech(Temp.this, new TextToSpeech.OnInitListener() {

                @Override
                public void onInit(int status) {

                    if(status == TextToSpeech.SUCCESS){
                        int result=tts.setLanguage(Locale.UK);
                        if(result==TextToSpeech.LANG_MISSING_DATA ||
                                result==TextToSpeech.LANG_NOT_SUPPORTED){
                            Log.e("error", "This Language is not supported");
                        }
                        else{
                            ConvertTextToSpeech();
                        }
                    }
                    else
                        Log.e("error", "Initilization Failed!");
                }
            });
        }
        else if(v.getId() == R.id.color){
            color_count=color_count%5;
            if(color_count==0) {
                textView.setTextColor(Color.parseColor("#000000"));
            }
            else if(color_count==1) {
                textView.setTextColor(Color.parseColor("#ff69b4"));
            }else  if(color_count==2) {
                textView.setTextColor(Color.parseColor("#93d600"));
            }else  if(color_count==3) {
                textView.setTextColor(Color.parseColor("#9494ec"));
            }else  if(color_count==4) {
                textView.setTextColor(Color.parseColor("#dfffc0"));
            }
            color_count++;

        }else if( v.getId() == R.id.background){
            back_count=back_count % 5;
            if(back_count==0) {
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            }
            else if(back_count==1) {
                getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);
            }else  if(back_count==2) {
                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#6b99ce"));
            }else  if(back_count==3) {
                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
            }else  if(back_count==4) {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }
            back_count++;
        }
        else if(v.getId() == R.id.font){
            font_count=font_count%5;
            if(font_count==0) {
                Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/dyslexic.ttf");
                textView.setTypeface(custom_font);
                line.setTypeface(custom_font);
            }
            else if(font_count==1) {
                Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/MonoRegular.ttf");
                textView.setTypeface(custom_font);
                line.setTypeface(custom_font);
            }else  if(font_count==2) {
                Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DyslexicBold.ttf");
                textView.setTypeface(custom_font);
                line.setTypeface(custom_font);
            }else  if(font_count==3) {
                Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DyslexicItalic.ttf");
                textView.setTypeface(custom_font);
                line.setTypeface(custom_font);
            }else  if(font_count==4) {
                Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Calibri.ttf");
                textView.setTypeface(custom_font);
                line.setTypeface(custom_font);
            }
            font_count++;

        }
        else if( v.getId() == R.id.back){
            flag1=true;
            if(line_number<0){
                if(j>0)j--;
                try {
                    parsedText = PdfTextExtractor.getTextFromPage(reader, j).trim() + "\n";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                i=0;
                full_line=parsedText.split("\n");
                line.setText("");
                line_number=0;
                line.setText(full_line[line_number]);
                cutLine=full_line[line_number].split(" ");
                textView.setText(cutLine[i++]);
                Spannable spanText = Spannable.Factory.getInstance().newSpannable(full_line[line_number]);
                spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0, cutLine[i-1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                line.setText(spanText);
                color_position=0;
            }
            else if(i>0)
            {
                if(flag){
                    i--;
                    flag=false;
                }
                textView.setText("");
                if(i>0)i--;
                textView.setText(cutLine[i]);
                if(color_position>0) {
                    color_position--;
                    color_position -= cutLine[i].length();
                }
                Spannable spanText = Spannable.Factory.getInstance().newSpannable(full_line[line_number]);
                spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), color_position, color_position+cutLine[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                line.setText(spanText);
                flag=false;
            }else {
                i=0;
                if(line_number>0)line_number--;
                if(line_number<0)
                    onClick(v);
                line.setText("");
                line.setText(full_line[line_number]);
                cutLine=full_line[line_number].split(" ");
                textView.setText("");
                textView.setText(cutLine[i++]);
                Spannable spanText = Spannable.Factory.getInstance().newSpannable(full_line[line_number]);
                spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0, cutLine[i-1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                line.setText(spanText);
                color_position=0;

                flag1=false;
            }




          /* if(cutLine[i-1].contains("\n")){
                line_number--;
            }
            if (i > 0) {
                textView.setText("");
                i--;
                textView.setText(cutLine[i]);
            } else {

                if(j>0) {
                    j--;
                    try {
                        parsedText = PdfTextExtractor.getTextFromPage(reader, j).trim() + "\n";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cutLine = parsedText.split(" |\n");
                    textView.setText("");
                    textView.setText(cutLine[0]);
                    i = 1;
                }
            }*/

        }

    }
    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        String text = textView.getText().toString();
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
