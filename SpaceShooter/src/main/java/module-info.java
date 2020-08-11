/**
 * @author Ed Eden-Rump
 * @created 27/07/2020 - 07:34
 * @project EdenCoding Space Shooter
 **/
module SimpleSpaceShooter {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.edencoding.controllers to javafx.fxml;

    exports com.edencoding;
}