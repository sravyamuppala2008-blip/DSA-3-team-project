package edu.railsync;

import java.util.List;

/** Small, fictional records used by the RailSync classroom demonstration. */
public final class RailwayData {
    private RailwayData() {}

    public static final List<String> TRAINS = List.of(
        "12627 Karnataka Express: Bengaluru to New Delhi",
        "12007 Shatabdi Express: Chennai to Mysuru",
        "12727 Godavari Express: Visakhapatnam to Hyderabad",
        "12839 Howrah Mail: Chennai to Howrah",
        "22691 Rajdhani Express: Bengaluru to Hazrat Nizamuddin"
    );

    public static final List<String> STATIONS = List.of(
        "Vijayawada Junction", "Chennai Central", "Bengaluru City",
        "Visakhapatnam Junction", "New Delhi"
    );

    public static final String ALERT_TEXT =
        "SERVICE ALERT: Train 12627 delayed at Vijayawada. " +
        "Platform change for train 12007. SERVICE ALERT: Track maintenance near Chennai.";

    public static final String DOCUMENT_TEXT =
        "railway service schedule railway station service update railway service";
}
