package dk.gjesse.r8test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private static class InnerHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("inHandlerInnerClass");
            CrashUtil.getNull().getClass();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.inHandlerAnnonymous);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("inHandlerAnnonymous");
                CrashUtil.getNull().getClass();
            }
        });
        final Button button2 = (Button) findViewById(R.id.inHandlerInnerClass);
        button2.setOnClickListener(new InnerHandler());
        final Button button3 = (Button) findViewById(R.id.inHandlerLambda);
        button3.setOnClickListener(this::onClick);
    }

    protected void onClick(View view) {
        System.out.println("inHandlerLambda");
        CrashUtil.getNull().getClass();
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
}
