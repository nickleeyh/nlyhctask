package task.nlyh.carousell.redditclone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TopicUtils {

    /**
     * This function sort the topics list based on upvotes, descending
     * Purpose: Allow easier unit testing due to modularization of methods
     * @param topicsList - ArrayList that holds all the topics
     * */
    protected static void sortTopicsList (ArrayList<Topic> topicsList) {
        Collections.sort(topicsList, new Comparator<Topic>() {
            @Override
            public int compare(Topic t1, Topic t2) {
                return t2.getUpvoteCount() - t1.getUpvoteCount();
            }
        });
    }

    /**
     * This function returns the top x topics
     * @param topicsList - ArrayList that holds all the topics
     * @param maxSize - Maximum size of the processed ArrayList
     * */
    protected static ArrayList<Topic> subList_TopicsList (ArrayList<Topic> topicsList, int maxSize) {
        if (topicsList.size() < maxSize) {
            return topicsList;
        }

        return new ArrayList<>(topicsList.subList(0, maxSize));
    }
}
