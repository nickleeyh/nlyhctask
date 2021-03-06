package task.nlyh.carousell.redditclone;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainActivityTest {
    ArrayList<Topic> topicsList = new ArrayList<>();

    @Test
    public void testSortTopicsList() {
        topicsList.add(new Topic("Content1", 10,3));
        topicsList.add(new Topic("Content2", 999,2));
        topicsList.add(new Topic("Content3", 20,1));
        TopicUtils.sortTopicsList(topicsList);

        // Expected Outcome: Sorted based on upvotes
        String [] expectedOutcome = {"Content2", "Content3", "Content1"};
        for (int i = 0; i != topicsList.size(); i++) {
            assertEquals(topicsList.get(i).getContent(), expectedOutcome[i]);
        }
    }

    @Test
    public void test_subList_TopicsList() {
        for (int i = 0; i != 25; i++) {
            topicsList.add(new Topic("Content", 0,0));
        }
        TopicUtils.sortTopicsList(topicsList);

        // Expected Outcome: Return only an ArrayList of size 20
        int expectedOutcome = 20;
        assertEquals(expectedOutcome, TopicUtils.subList_TopicsList(topicsList, expectedOutcome).size());
    }
}