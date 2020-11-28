package com.example.mapchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mapchat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.instance;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.getId;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.xml.validation.Validator;


public class Reading extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String user = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
    public static String Message = "ie.ul.myfirstapp.EXTRA_MESSAGE";
    public static ArrayList<String> values = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        update();

        setContentView(R.layout.activity_reading);

    }

    public void onClickPost(View view){


        Intent intent = new Intent(this,Post.class);

        startActivity(intent);
        //finish();
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(this,Profile.class);

        startActivity(intent);
    }

    public void update() {
        db = FirebaseFirestore.getInstance();
        values.clear();
        db.collection("Posts")
                //.orderBy("Posts", Query.Direction.DESCENDING)

                .get()



                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        System.out.println("1");
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot value : task.getResult()) {
                                System.out.println("2");
                                Message = value.getData().toString();
                                cleanUp();
                                values.add(Message);
                            }
                            System.out.println(values);

                            TextView welcome =findViewById(R.id.welcome);
                            welcome.setText("Welcome "+user+"!");

                            TextView profileClick =findViewById(R.id.profileClick);
                            profileClick.setText(user);

                            TextView textView = findViewById(R.id.textView2);
                            textView.setText(values.get(0));

                            TextView textView1 = findViewById(R.id.textView3);
                            textView1.setText(values.get(1));

                            TextView textView2 = findViewById(R.id.textView4);
                            textView2.setText(values.get(2));

                            TextView textView3 = findViewById(R.id.textView5);
                            textView3.setText(values.get(3));

                            TextView textView4 = findViewById(R.id.textView6);
                            textView4.setText(values.get(4));
                        }
                        else{
                            Log.w("tag", "Error getting Posts.",task.getException()) ;
                        }
                    }
                });



    }

    public void cleanUp(){
        String redo = "";
        char b = '=';
        char c = '}';
        boolean print = false;
        for(int i=0;i<Message.length();i++) {
            if(Message.charAt(i)==c ) {
                print = false;
            }

            if (print == true) {
                redo+=(Message.charAt(i));
            }

            if(Message.charAt(i)==b) {
                print = true;
            }
        }
        Message = redo;
    }





}