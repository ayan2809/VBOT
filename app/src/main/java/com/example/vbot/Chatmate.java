package com.example.vbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.vbot.R.id.userInput;

public class Chatmate extends AppCompatActivity {

    EditText userInput;
    RecyclerView recyclerView;
    List<ResponseMesssage> responseMessagesList;
    MessageAdapter messageAdapter;

    public boolean isVisible(){
        LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
        int positionOfLastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        int itemCount = recyclerView.getAdapter().getItemCount();
        return positionOfLastVisibleItem>=itemCount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatmate);

        userInput=findViewById(R.id.userInput);
        recyclerView=findViewById(R.id.conversation);
        responseMessagesList=new ArrayList<>();
        messageAdapter=new MessageAdapter(responseMessagesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(messageAdapter);

        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEND){
                    ResponseMesssage message = new ResponseMesssage(userInput.getText().toString(),true);
                    responseMessagesList.add(message);
                    ResponseMesssage message2 = new ResponseMesssage(userInput.getText().toString(),false);
                    responseMessagesList.add(message2);
                    messageAdapter.notifyDataSetChanged();

                    if(!isVisible()){
                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount()-1);

                    }
                }
                return false;
            }
        });


    }


}