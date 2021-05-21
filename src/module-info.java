module SMSFx {
    requires javafx.controls;
    requires javafx.fxml;

    opens sms.views.main;
    opens sms;
    opens sms.data;
}