package com.example.ourgame.Statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ourgame.Languages.LanguageFactory;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.ScreenLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderBoardActivity extends AppCompatActivity {

    DataWriter dataWriter;
    ScreenLoader screenLoader;
    Language language;
    TextView firstText, secondText, thirdText, forthText, fifthText, personalRankText;
    TextView firstPoints, secondPoints, thirdPoints, forthPoints, fifthPoints;
    TextView valueTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        firstText = findViewById(R.id.firstUserText);
        secondText = findViewById(R.id.secondUserText);
        thirdText = findViewById(R.id.thirdUserText);
        forthText = findViewById(R.id.forthUserText);
        fifthText = findViewById(R.id.fifthUserText);
        personalRankText = findViewById(R.id.personalRankValue);

        firstPoints = findViewById(R.id.firstValueText);
        secondPoints = findViewById(R.id.secondPointsText);
        thirdPoints = findViewById(R.id.thirdValueText);
        forthPoints = findViewById(R.id.forthValueText);
        fifthPoints = findViewById(R.id.fifthValueText);

        valueTitle = findViewById(R.id.valueTitle);

        dataWriter = new DataWriter(this);
        screenLoader = new ScreenLoader(this);

        setLanguage();

        ConstraintLayout constraintLayout = findViewById(R.id.leaderboardLayout);
        constraintLayout.setBackgroundResource(dataWriter.getThemeData());

        //Display ranking based on points data by default
        Map<String, Integer> pointsData = dataWriter.getPointsData();
        List<Map.Entry<String, Integer>> data = new ArrayList<>(pointsData.entrySet());
        displayLeaderBoard(new SortNumberStrategy(data));
    }

    private void setLanguage() {
        LanguageFactory text = new LanguageFactory(dataWriter.getLanguage(), this);
        language = text.getTextSetter();

        TextView title = findViewById(R.id.titleText);
        TextView name = findViewById(R.id.userTitle);
        TextView personalRankLabel = findViewById(R.id.personalRankLabel);
        TextView rankByLabel = findViewById(R.id.rankByLabel);
        Button pointsButton = findViewById(R.id.pointsButton);
        Button rankingButton = findViewById(R.id.rankingButton);
        Button playTimeButton = findViewById(R.id.playTimeButton);
        Button backButton = findViewById(R.id.backButton);

        name.setText(language.leaderBoardUser());
        title.setText(language.getMainLeaderBoard());
        personalRankLabel.setText(language.leaderBoardYourRank());
        valueTitle.setText(language.statPoints());
        rankByLabel.setText(language.rankBy());
        pointsButton.setText(language.points());
        rankingButton.setText(language.ranking());
        playTimeButton.setText(language.playtime());
        backButton.setText(language.back());
    }

    /**
     * Method to rank the leader board by the points each user has
     *
     * @param view the button object that was tapped
     */
    public void rankPoints(View view) {

        //Need to set the text above points to "Points"
        Map<String, Integer> pointsData = dataWriter.getPointsData();
        List<Map.Entry<String, Integer>> data = new ArrayList<>(pointsData.entrySet());
        displayLeaderBoard(new SortNumberStrategy(data));

        valueTitle.setText(language.statPoints());
    }

    /**
     * Method to rank the leader board by total play time
     *
     * @param view the button object that was tapped
     */
    public void rankPlayTime(View view) {

        //Need to set the text above playtime to "Play Time (minutes)"
        Map<String, Integer> playData = dataWriter.getPlayTimeData();
        List<Map.Entry<String, Integer>> data = new ArrayList<>(playData.entrySet());
        displayLeaderBoard(new SortNumberStrategy(data));

        valueTitle.setText(language.statPlaytime());
    }

    /**
     * Method to rank the leader board by ranking
     *
     * @param view the button object that was tapped
     */
    public void rankRanking(View view) {

        //Need to set the text above ranking to "Ranking"
        Map<String, String> rankingData = dataWriter.getRankingData();
        List<Map.Entry<String, String>> data = new ArrayList<>(rankingData.entrySet());
        displayLeaderBoard(new SortRankingStrategy(data));

        valueTitle.setText(language.statRank());
    }

    public void onBackPressed(View view) {
        screenLoader.loadMainMenu();
    }

    /**
     * Method that displays the leader board by diplaying the ranking of the top 5 users based on
     * either points, playtime or ranking. Sets and changes the text displayed.
     *
     * @param sortMethod the SortStrategy interface that is used to sort the leader board based on
     *                   points, playtime or ranking
     */
    private void displayLeaderBoard(SortStrategy sortMethod) {

        List<String> userNames = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        List<Map.Entry<String, Object>> entries = sortMethod.getData();
        sortMethod.sort();

        //after sorting map, place user names and values into arraylists
        for (Map.Entry<String, Object> e : entries) {
            userNames.add(e.getKey());
            values.add(e.getValue());
        }

        if (userNames.size() >= 1) {
            firstText.setText(userNames.get(0));
            firstPoints.setText(values.get(0).toString());
        }
        if (userNames.size() >= 2) {
            secondText.setText(userNames.get(1));
            secondPoints.setText(values.get(1).toString());
        }
        if (userNames.size() >= 3) {
            thirdText.setText(userNames.get(2));
            thirdPoints.setText(values.get(2).toString());
        }
        if (userNames.size() >= 4) {
            forthText.setText(userNames.get(3));
            forthPoints.setText(values.get(3).toString());
        }
        if (userNames.size() >= 5) {
            fifthText.setText(userNames.get(4));
            fifthPoints.setText(values.get(4).toString());
        }

        int rank = userNames.indexOf(dataWriter.getCurrentUser()) + 1;
        String rankString = "" + rank;
        personalRankText.setText(rankString);
    }
}
