package net.ingramintegrations.sendemail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Creating edit texts
    EditText recipient, subject, body;
    // Creating strings to store the emaill address we are sending to as
    // the subject and the body.
    String emailAddress, emailSubject, emailBody;
    // Creating a button that will send our email when we click it.
    Button sendEmailButton;
    // Creating an Intent that we will use to send the email message.
    Intent SendEmailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set our layout
        setContentView(R.layout.activity_main);

        // Hooking up our UI to the java variables we just created.
        recipient = (EditText) findViewById(R.id.act_main_et_email_address);
        subject = (EditText) findViewById(R.id.act_main_et_email_subject);
        body = (EditText) findViewById(R.id.act_main_et_email_body);
        sendEmailButton = (Button) findViewById(R.id.act_main_bt_send_email_button);

        // OnclickListener for our button "sendEmailButton"
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text values from our EditTexts
                emailAddress = recipient.getText().toString();
                emailSubject = subject.getText().toString();
                emailBody = body.getText().toString();

                // Setup the action of our Intent
                // Check this documentation if you need more detail:
                // https://developer.android.com/reference/android/content/Intent.html#ACTION_SEND
                SendEmailIntent = new Intent(Intent.ACTION_SEND);
                // Here we set our message type. This will help the Android system choose
                // what apps to show as possible receivers of the Intent
                SendEmailIntent.setType("message/rfc822");
                // Here we add the recipients email address to the Intent. Our email address is
                // stored in the String "emailAddress"
                SendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
                // Here we add our email subject in the String "emailSubject"
                SendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                // Finally, we add our message body to the email in the String "emailBody"
                SendEmailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
                // Now we use a Try/Catch statement to send the email.
                try {
                    // Make sure to use Intent.createChooser() here so that the user can pick
                    // what email app they would like ot use to send the email message.
                    // "Send email..." is the title that appears when the chooser shows up.
                    // If there is no application that can use this Intent, the system will notify
                    // the user.
                    startActivity(Intent.createChooser(SendEmailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    // If something goes wrong we display a Toast.
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
