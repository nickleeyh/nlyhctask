package task.nlyh.carousell.redditclone;

import java.util.ArrayList;

public class UpdateUiEvent {

    ArrayList<Topic> topicsList;

    public UpdateUiEvent(ArrayList<Topic> topicsList) {
        this.topicsList = topicsList;
    }

    public ArrayList<Topic> getTopicsList() {
        return topicsList;
    }

}
