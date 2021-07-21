package com.example.motoheal;

import android.content.Context;
import android.graphics.Paint;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.text.Layout;
import android.text.style.ParagraphStyle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TransactionAdapter1 extends RecyclerView.Adapter<TransactionAdapter1.ViewHolder> {

    Context context;
    ArrayList<Transaction> cashPayments;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

    public TransactionAdapter1(Context context, ArrayList<Transaction> cashPayments) {
        this.context = context;
        this.cashPayments = cashPayments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cash_user_dialog1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Transaction temp = cashPayments.get(position);

        firebaseDatabase.getReference().child("Partners").child("partners").child(cashPayments.get(position).getSentTo()).child("Business Details").child("shopName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstname = snapshot.getValue().toString();
                holder.name.setText(firstname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        holder.amount.setText(temp.getTotalPrice());
        holder.date.setText(temp.getDate());

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String filepath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
                //File file=new File(filepath,System.currentTimeMillis()+".pdf");

                String da=new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
                String path= context.getExternalFilesDir(null).toString()+"/"+da+".pdf";
                //String path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/"+da+".pdf";

                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat s=new SimpleDateFormat("dd-MMM-yyyy");
                String datetime=s.format(calendar.getTime());

                File file=new File(path);

                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Document document=new Document(PageSize.A4);
                try {
                    PdfWriter.getInstance(document,new FileOutputStream(file));
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                document.open();
                Font myfont=new Font(Font.FontFamily.HELVETICA,22,Font.BOLD);
                Font myfont1=new Font(Font.FontFamily.HELVETICA,15,Font.ITALIC);
                Font myfont2=new Font(Font.FontFamily.HELVETICA,20,Font.UNDERLINE);
                Font myfont3=new Font(Font.FontFamily.HELVETICA,30,Font.BOLD);
                Font myfont4=new Font(Font.FontFamily.HELVETICA,20,Font.BOLD);
                Font myfont5=new Font(Font.FontFamily.HELVETICA,15,Font.UNDERLINE);

                int z=Integer.parseInt(temp.getTotalPrice())-Integer.parseInt(temp.getBasePrice());


                Paragraph paragraph=new Paragraph();


                paragraph.add(new Paragraph("                        Moto Heal",myfont3));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("                                Transaction Invoice ",myfont4));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("Order Details",myfont2));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("     Transaction Id              :               "+temp.getTransactionId(),myfont1));
                paragraph.add(new Paragraph("     Mode Of Transaction    :               "+temp.getMode(),myfont1));
                paragraph.add(new Paragraph("     Date of Transaction      :               "+temp.getDate(),myfont1));
                paragraph.add(new Paragraph("     Service Provider            :               "+temp.getSentTo(),myfont1));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("Transaction Details",myfont2));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("     Base Price                 :               "+temp.getBasePrice(),myfont1));
                paragraph.add(new Paragraph("     Travel Fair               :               "+temp.getTravelFair(),myfont1));
                paragraph.add(new Paragraph("     GST(18%)                 :               "+z,myfont5));
                paragraph.add(new Paragraph("     Total Amount         :               "+temp.getTotalPrice(),myfont1));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("\n"));
                paragraph.add(new Paragraph("                                                         Thank You For Choosing Moto Heal",myfont3));

                try {
                    document.add(paragraph);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                document.close();

                Toast.makeText(context, "Pdf Downloaded successfully at"+path, Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return cashPayments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,date,amount;
        Button download;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.cashcustname);
            amount=itemView.findViewById(R.id.amount);
            date=itemView.findViewById(R.id.date1);
            download=itemView.findViewById(R.id.download);
        }
    }
}
