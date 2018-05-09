package task.nlyh.carousell.redditclone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class TopicUtils {

    /**
     * This function sort the topics list based on upvotes, descending
     * Purpose: Allow easier unit testing due to modularization of methods
     * @param topicArrayList - ArrayList that holds all the topics
     * */
    protected static void sortTopicsList (ArrayList<Topic> topicArrayList) {
        Collections.sort(topicArrayList, new Comparator<Topic>() {
            @Override
            public int compare(Topic t1, Topic t2) {
                return t2.getUpvoteCount() - t1.getUpvoteCount();
            }
        });
    }

    /**
     * This function returns the top x topics
     * @param topicArrayList - ArrayList that holds all the topics
     * @param maxSize - Maximum size of the processed ArrayList
     * */
    protected static ArrayList<Topic> subList_TopicsList (ArrayList<Topic> topicArrayList, int maxSize) {
        if (topicArrayList.size() < maxSize) {
            return topicArrayList;
        }

        return new ArrayList<>(topicArrayList.subList(0, maxSize));
    }

    /**
     * This function returns the top x topics
     * @param maxSize - Maximum size of the dummy data in ArrayList
     * Purpose: Populate Dummy Data
     * */
    protected static ArrayList<Topic> createDummyTopicsList (int maxSize) {
        ArrayList<Topic> topicArrayList = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i != maxSize; i++) {
            int randomUpvotes = rand.nextInt(100);
            int randomDownvotes = rand.nextInt(10);
            topicArrayList.add(new Topic(
                    "This is a test topic to showcase top 20 functionality.",
                    randomUpvotes,
                    randomDownvotes));
        }
        return topicArrayList;
    }
}
