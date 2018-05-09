package task.nlyh.carousell.redditclone;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog_post_topic;
    private ArrayList<Topic> topicsList = new ArrayList<Topic>();

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog_PostTopic();
            }
        });

        recyclerView = findViewById(R.id.recycle_view);
        update_ui();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This function is to update the homescreen UI's recycler view
     * */
    private void update_ui () {
        TopicUtils.sortTopicsList(topicsList);
        adapter = new TopicRecyclerAdapter(topicsList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent (UpdateUiEvent event) {
        topicsList = event.getTopicsList();
        update_ui();
    }

    /**
     * This function is to show a dialog to get user input for a Topic
     * */
    private void showDialog_PostTopic() {
        // Check to ensure no dialog is already showing
        if (dialog_post_topic != null && dialog_post_topic.isShowing()) {
            return;
        }

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View mView = layoutInflater.inflate(R.layout.dialog_post_topic, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(mView);

        final EditText editText_topic_content = mView.findViewById(R.id.dialog_edittext);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Topic newTopic = new Topic(editText_topic_content.getText().toString());
                        topicsList.add(newTopic);
                        update_ui();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        dialog_post_topic = alertDialogBuilder.create();
        dialog_post_topic.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
