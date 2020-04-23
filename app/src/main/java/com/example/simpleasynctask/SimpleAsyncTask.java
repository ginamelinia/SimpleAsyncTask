package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;

    // Before the tasks execution
    protected void onPreExecute(){
        mTextView.get().setText("\nStarting task....");
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;

            // Sleep for the random amount of time
            try {
                int Count = 0;
                while (Count < 100){
                    Thread.sleep(s);
                    publishProgress(Count);
                    if (isCancelled()) break;
                    Count++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    // After each task done
    protected void onProgressUpdate(Integer... progress){
        mTextView.get().setText("\nCompleted...." + progress[0] + "%");
    }

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }
}
