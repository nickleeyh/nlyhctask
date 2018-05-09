package task.nlyh.carousell.redditclone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class TopicRecyclerAdapter extends RecyclerView.Adapter<TopicRecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<Topic> topicsList;

    public TopicRecyclerAdapter(ArrayList<Topic> arrayList) {
        topicsList = arrayList;
    }

    @NonNull
    @Override
    public TopicRecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_topic, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicRecyclerAdapter.RecyclerViewHolder holder, int position) {
        final Topic topic = topicsList.get(position);
        holder.topic_content.setText(topic.getContent());

        holder.tv_upvote_count.setText(String.valueOf(topic.getUpvoteCount()));
        holder.button_upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the topic cardview on user's upvoting
                topic.incrementUpvoteCount(1);
                holder.tv_upvote_count.setText(String.valueOf(topic.getUpvoteCount()));
                EventBus.getDefault().post(new UpdateUiEvent(topicsList));
            }
        });

        holder.tv_downvote_count.setText(String.valueOf(topic.getDownvoteCount()));
        holder.button_downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the topic cardview on user's downvoting
                topic.incrementDownvoteCount(1);
                holder.tv_downvote_count.setText(String.valueOf(topic.getDownvoteCount()));
                EventBus.getDefault().post(new UpdateUiEvent(topicsList));
            }
        });

        // additional textview specifically for this task to allow ease of knowing how many cards are there
        holder.tv_card_number.setText("Card Number: " + (position+1));
    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }

    /**
     * This function initialize the views of the topic cardview
     * */
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        Button button_upvote, button_downvote;
        TextView topic_content;
        TextView tv_upvote_count, tv_downvote_count;
        TextView tv_card_number; // additional textview specifically for this task to allow ease of knowing how many cards are there

        public RecyclerViewHolder(View view) {
            super(view);
            button_upvote = view.findViewById(R.id.button_topic_upvote);
            button_downvote = view.findViewById(R.id.button_topic_downvote);
            topic_content = view.findViewById(R.id.textview_topic_content);
            tv_upvote_count = view.findViewById(R.id.textview_upvote_count);
            tv_downvote_count = view.findViewById(R.id.textview_downvote_count);
            tv_card_number = view.findViewById(R.id.textview_cardNumber);
        }
    }
}
