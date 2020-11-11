package com.example.mapchat;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.Var;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

public class Post extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String Message = "ie.ul.myfirstapp.EXTRA_MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void post(View view){
        CollectionReference Posts = db.collection("Posts");





        EditText editText = (EditText) findViewById(R.id.Message);
        String PostMessage = editText.getText().toString();
        Map<String,Object> Posts1 = new HashMap<>();
        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ts=Long.toString(System.currentTimeMillis());

        String name = "";
        for(int i =0; i<13;i++) {
            name+=(9- (Character.getNumericValue(ts.charAt(i))));

        }
        // Date date=new Date(ts.getTime());
        //DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        //String Time = df.format(ts);//I know this can be broken by changing device time but servertimestamp doesnt work and I don't really have a different solution rn
        // System.out.println(timestamp);







        Posts1.put(name,PostMessage);

        Posts.document(name).set(Posts1);

        Intent intent = new Intent(this,Reading.class);

        startActivity(intent);



        /*EditText editText = (EditText) findViewById(R.id.Message);
        String message = editText.getText().toString();
        Intent intent = new Intent(this,Reading.class);
        intent.putExtra(Message,message);
        startActivity(intent); */
    }
}