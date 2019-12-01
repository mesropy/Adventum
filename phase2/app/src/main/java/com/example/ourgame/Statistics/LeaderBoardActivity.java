package com.example.ourgame.Statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ourgame.Languages.LanguageTextSetter;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.Menus.MainActivity;
import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Themes.Theme;
import com.example.ourgame.Themes.ThemeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderBoardActivity extends AppCompatActivity {

    DataWriter dataWriter;
    Language language;
    TextView firstText, secondText, thirdText, fourthText, fifthText, personal;
    TextView firstPoints, secondPoints, thirdPoints, fourthPoints, fifthPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        firstText = findViewById(R.id.first);
        secondText = findViewById(R.id.second);
        thirdText = findViewById(R.id.third);
        fourthText = findViewById(R.id.fourth);
        fifthText = findViewById(R.id.fifth);
        personal = findViewById(R.id.personal);

        firstPoints = findViewById(R.id.firstPoints);
        secondPoints = findViewById(R.id.secondPoints);
        thirdPoints = findViewById(R.id.thirdPoints);
        fourthPoints = findViewById(R.id.fourthPoints);
        fifthPoints = findViewById(R.id.fifthPoints);
        dataWriter = new DataWriter(this);

        LanguageTextSetter text = new LanguageTextSetter(dataWriter.getLanguage(), this);
        language = text.getTextSetter();

        TextView title = findViewById(R.id.title);
        TextView points = findViewById(R.id.valueTitle);
        TextView name = findViewById(R.id.userTitle);
        points.setText(language.statPoints());
        name.setText(language.leaderboardUser());
        title.setText(language.getMainLeaderBoard());

        ConstraintLayout constraintLayout = findViewById(R.id.leaderboardLayout);
        ThemeBuilder themeBuilder = new ThemeBuilder(dataWriter.getThemeData());
        Theme theme = themeBuilder.getTheme();
        constraintLayout.setBackgroundResource(theme.hangmanActivityLayout());

        //Display ranking based on points data by default
        Map<String, Integer> pointsData = dataWriter.getPointsData();
        List<Map.Entry<String, Integer>> data = new ArrayList<>(pointsData.entrySet());
        displayLeaderBoard(new SortNumberStrategy(data));
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
    }

    public void onBackPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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
            firstText.setText(getString(R.string.rank_text, "1. ", userNames.get(0)));
            firstPoints.setText(getString(R.string.points_text, values.get(0)));
        }
        if (userNames.size() >= 2) {
            secondText.setText(getString(R.string.rank_text, "2. ", userNames.get(1)));
            secondPoints.setText(getString(R.string.points_text, values.get(1)));
        }
        if (userNames.size() >= 3) {
            thirdText.setText(getString(R.string.rank_text, "3. ", userNames.get(2)));
            thirdPoints.setText(getString(R.string.points_text, values.get(2)));
        }
        if (userNames.size() >= 4) {
            fourthText.setText(getString(R.string.rank_text, "4. ", userNames.get(3)));
            fourthPoints.setText(getString(R.string.points_text, values.get(3)));
        }
        if (userNames.size() >= 5) {
            fifthText.setText(getString(R.string.rank_text, "5. ", userNames.get(4)));
            fifthPoints.setText(getString(R.string.points_text, values.get(4)));
        }

        String string = language.leaderboardYourRank();
        int rank = userNames.indexOf(dataWriter.getCurrentUser()) + 1;
        String rankS = String.valueOf(rank);
        string = string + rankS;
        personal.setText(string);
    }
}
