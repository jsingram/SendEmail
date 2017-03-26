# SendEmail
This is a simple Android Studio Project showing how to send an email in Android.

# Main Breakdown

When sending an email in Android, the easiest way is via an Intent.

First, you must creat an Intent.

Intent SendEmailIntent;

To send an email, use the newly created Intent like this:

SendEmailIntent = new Intent(Intent.ACTION_SEND); <--- Here we are describing the type of Intent we are using.
                SendEmailIntent.setType("message/rfc822"); <--- This line helps the Android system decide what apps can send this email.
                SendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient's email address}); <----- Add email address here.
                SendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "email subject"); <---- This is where you will include a subject.
                SendEmailIntent.putExtra(Intent.EXTRA_TEXT, "email body"); <---- This is where you would include your email's body of text.

                try {
                    startActivity(Intent.createChooser(SendEmailIntent, "Send email...")); <---- Here I'm giving a title to the Chooser.
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_LONG).show();
                }
                
 That is how you send an email in Android. Happy coding!
