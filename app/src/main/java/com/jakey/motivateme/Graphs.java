package com.jakey.motivateme;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jakey.motivateme.R;
import com.jakey.motivateme.models.DailyLog;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Graphs extends ActionBarActivity {

    ArrayList<DailyLog> dailyLogs = (ArrayList<DailyLog>) DailyLog.listAll(DailyLog.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);


        System.out.println(dailyLogs.get(0).getDiet());

        GraphView wGraph = (GraphView) findViewById(R.id.weightGraph);
        GraphView dWGraph = (GraphView) findViewById(R.id.dietWorkGraph);


/*<Weight Graph>*/
            if(dailyLogs.size()<2){
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                        new DataPoint(0, dailyLogs.get(0).getWeight()),
                        new DataPoint(1, dailyLogs.get(0).getWeight())

                });
                wGraph.addSeries(series);
                series.setColor(Color.RED);
                wGraph.setTitle("Weight");

            }
            else if(dailyLogs.size()<14){
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(getWeight1());
                wGraph.addSeries(series);
                series.setColor(Color.RED);
                wGraph.setTitle("Weight");

            }
            else{
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(getWeight2());
                wGraph.addSeries(series);
                series.setColor(Color.RED);
                wGraph.setTitle("Weight");

            }

        /*</Weight Graph>*/

        /*<dWGraph>*/
        if(dailyLogs.size()>=7) {
            LineGraphSeries<DataPoint> series1 = new LineGraphSeries<DataPoint>(getDiet1());
            dWGraph.addSeries(series1);
            series1.setColor(Color.GREEN);

            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(getWorkout1());
            dWGraph.addSeries(series2);
            series2.setColor(Color.YELLOW);

            series1.setTitle("Healthy Diet %");
            series2.setTitle("Workout %");

        }

        dWGraph.setTitle("Diet and Workout % Per Week");
        dWGraph.getLegendRenderer().setVisible(true);
        dWGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);

        /*</dWGraph>*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graphs, menu);
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



    private DataPoint[] getWeight1(){
        DataPoint [] values =  new DataPoint [dailyLogs.size()];

        for(int i=0;i<dailyLogs.size(); i++){
            DataPoint v = new DataPoint(i,dailyLogs.get(i).getWeight());
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] getWeight2(){
        DataPoint [] values =  new DataPoint [dailyLogs.size()];
        int numOfPoints = dailyLogs.size()/7;
        int pointCount = 0;
        while(pointCount<numOfPoints){
            for(int i=0;i<dailyLogs.size(); i+=7){
                DataPoint v = new DataPoint(pointCount+1,dailyLogs.get(i).getWeight());
                values[i] = v;
            }
            pointCount++;
        }
        return values;
    }

    private DataPoint [] getDiet1(){
        DataPoint [] values = new DataPoint [dailyLogs.size()];
        int numOfPoints = dailyLogs.size()/7;
        int pointCount = 0;
        while(pointCount<numOfPoints){
            for(int i=0;i<dailyLogs.size(); i+=7){
                int healthCount = 0;
                for(int j=0; j<7; j++){
                    if(dailyLogs.get(j).getDiet() == "Healthy"){
                        healthCount++;
                    }
                }
                healthCount = (healthCount/7)*100;
                DataPoint v = new DataPoint(pointCount+1, healthCount);
                values[i] = v;
            }
            pointCount++;
        }
        return values;
    }

    private DataPoint [] getWorkout1(){
        DataPoint [] values = new DataPoint [dailyLogs.size()];
        int numOfPoints = dailyLogs.size()/7;
        int pointCount = 0;
        while(pointCount<numOfPoints){
            for(int i=0;i<dailyLogs.size(); i+=7){
                int healthCount = 0;
                for(int j=0; j<7; j++){
                    if(dailyLogs.get(j).getWorkout()){
                        healthCount++;
                    }
                }
                healthCount = (healthCount/7)*100;
                DataPoint v = new DataPoint(pointCount+1, healthCount);
                values[i] = v;
            }
            pointCount++;
        }
        return values;
    }

    public void toMain(View view){

        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }
}
