# Minimal reproduction of gomobile network issue

A repo reproducing the bug whereupon TCP sockets cannot be opened from Go when the android app is bound to a wifi via NetworkRequest.

Only reproducible on Android 10+ (API level 29+).

Short structure description:

- `gotcp` is a minimal module that only tries opening a TCP socket via `net.Dial`.
- `app` holds a minimal Android app that uses that module via gomobile and incorporates the wifi connect logic to showcase the issue.

## Start example

1. Bind the android lib

    ```sh
    cd gotcp
    go mod download
    gomobile bind -target=android -o gotcp.aar
    ```

1. Change the `SSID` and `WIFI_PASS` variables in `MainActivity.java` to a wifi you have access to.

    ```java
    public final static String SSID = "<Wifi SSID>";
    public final static String WIFI_PASS = "<Wifi Pass>";
    ```

1. Start android app

    From Android Studio or install it via gradle, then start it manually.

    ```sh
    ./gradlew installDebug
    ```

## Test cases

1. Make sure that cellular data is disabled.
1. Press `Do dial` when connected to wifi via system settings and observe the logs.

    Should be as follows after the 10s timeout

    ```text
    E/GoLog: time="2022-05-04T07:47:19Z" level=info msg="Entered DoDial"
    E/GoLog: time="2022-05-04T07:47:26Z" level=info msg="Error is dial tcp 192.168.0.1:80: connect: connection timed out"
    ```

1. Now press `Connect to wifi` and bind the app to the wifi programatically
1. Press `Do dial` again and check the logs.

    Should be as follows, immediately

    ```text
    E/GoLog: time="2022-05-04T07:49:11Z" level=info msg="Entered DoDial"
    E/GoLog: time="2022-05-04T07:49:11Z" level=info msg="Error is dial tcp 192.168.0.1:80: connect: network is unreachable"
    ```
